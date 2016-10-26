package com.deere.democlient.brokers;

import com.deere.api.axiom.generated.v3.FieldOperation;
import com.deere.democlient.apis.AbstractApiBase;
import com.deere.democlient.exceptions.UnavailableRelException;
import com.deere.democlient.exceptions.UnsupportedShapefileExportException;
import com.deere.democlient.util.FileWriter;
import com.deere.democlient.util.LinkUtility;
import com.deere.rest.HttpHeader;
import com.deere.rest.HttpStatus;
import com.deere.rest.RestRequest;
import com.deere.rest.RestResponse;

import java.io.IOException;
import java.io.InputStream;

public class ShapefileDownloadBroker extends AbstractApiBase {

    public void download(FieldOperation fieldOperation, String outputPath, String outputFilename) throws IOException, UnavailableRelException, UnsupportedShapefileExportException {
        String presignedUrl = getDownloadUrl(fieldOperation);
        InputStream inputStream = retrieveFileContent(presignedUrl);
        FileWriter.writeToFile(inputStream, outputPath, outputFilename);
    }

    public void download(String asyncShapefileUrl, String outputPath, String outputFilename) throws IOException, UnsupportedShapefileExportException {
        String presignedUrl = getDownloadUrl(asyncShapefileUrl);
        InputStream inputStream = retrieveFileContent(presignedUrl);
        FileWriter.writeToFile(inputStream, outputPath, outputFilename);
    }

    public String getDownloadUrl(FieldOperation fieldOperation) throws UnsupportedShapefileExportException, UnavailableRelException {
        String uri = LinkUtility.getUriForRel(fieldOperation, "asyncShapefileDownload");
        return getDownloadUrl(uri);
    }

    public String getDownloadUrl(String asyncShapefileUrl) throws UnsupportedShapefileExportException {
        return getDownloadUrl(asyncShapefileUrl, new ExponentialBackoffCalculator());
    }

    private String getDownloadUrl(String asyncShapefileUrl, ExponentialBackoffCalculator intervalCalc) throws UnsupportedShapefileExportException {
        final RestRequest request = oauthRequestTo(asyncShapefileUrl)
                .method("GET")
                .addHeader(new HttpHeader("Accept", "application/vnd.deere.axiom.v3+json"))
                .build();

        final RestResponse response = request.fetchResponse();

        if (response.getResponseCode() == HttpStatus.ACCEPTED) {
            sleep(intervalCalc.getNextIntervalInMillis());
            return getDownloadUrl(asyncShapefileUrl, intervalCalc);
        } else if (response.getResponseCode() == HttpStatus.TEMPORARY_REDIRECT) {
            return response.getHeaderFields().valueOf("Location");
        } else if (response.getResponseCode() == HttpStatus.NOT_ACCEPTABLE) {
            throw new UnsupportedShapefileExportException();
        } else {
            throw new RuntimeException("Unexpected response code while retrieving shapefile pre-signed URL");
        }
    }

    public InputStream retrieveFileContent(String presignedUrl) {
        final RestRequest request = oauthRequestTo(presignedUrl)
                .method("GET")
                .addHeader(new HttpHeader("Accept", "application/vnd.deere.axiom.v3+json"))
                .build();

        final RestResponse response = request.fetchResponseAnonymously();
        System.out.println(response.getResponseCode().value());

        if (response.getResponseCode() != HttpStatus.OK) {
            throw new RuntimeException("Error retrieving file content");
        }

        return response.getBody();
    }

    private void sleep(long millis) {
        System.out.println("Not yet ready. Sleeping for " + millis + " millis");
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class ExponentialBackoffCalculator {
        private int iteration;

        public ExponentialBackoffCalculator() {
            iteration = 0;
        }

        public long getNextIntervalInMillis() {
            return (long)Math.pow(2,iteration++)*1000;
        }
    }
}
