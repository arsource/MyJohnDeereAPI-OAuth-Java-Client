package com.deere.democlient.brokers;

import com.deere.api.axiom.generated.v3.File;
import com.deere.democlient.apis.AbstractApiBase;
import com.deere.democlient.util.FileWriter;
import com.deere.rest.HttpHeader;
import com.deere.rest.HttpStatus;
import com.deere.rest.RestRequest;
import com.deere.rest.RestResponse;

import java.io.IOException;
import java.util.List;

public class FileDownloadBroker extends AbstractApiBase {
    public void downloadFile(String fileUri, String outputDirectory) {
        File file = retrieveMetadataForFile(fileUri);
        downloadFile(file, outputDirectory);
    }

    public void downloadFile(File file, String outputDirectory) {
        String fileUri = linksFrom(file).get("self").getUri();
        String filename = file.getId() + "_" + file.getName(); //include the id, because file name is not unique
        downloadFileContent(fileUri, outputDirectory, filename);
    }

    public void downloadFiles(List<File> files, String outputDirectory) {
        for (File file : files) {
            downloadFile(file, outputDirectory);
        }
    }

    private File retrieveMetadataForFile(String fileUri) {
        final RestRequest fileDetails = oauthRequestTo(fileUri)
                .method("GET")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .build();

        final RestResponse fileDetailsResponse = fileDetails.fetchResponse();
        return read(fileDetailsResponse).as(File.class);
    }

    private void downloadFileContent(String fileUri, String outputPath, String outputFilename) {
        final RestRequest fileDetails = oauthRequestTo(fileUri)
                .method("GET")
                .addHeader(new HttpHeader("Accept", "application/octet-stream"))
                .build();

        final RestResponse fileDetailsResponse = fileDetails.fetchResponse();

        if (fileDetailsResponse.getResponseCode() != HttpStatus.OK) {
            System.err.println("Error retrieving file content: " + fileUri);
            return;
        }

        try {
            FileWriter.writeToFile(fileDetailsResponse.getBody(), outputPath, outputFilename);
        } catch (IOException e) {
            System.err.println("Error writing file content: " + fileUri);
        }
    }
}
