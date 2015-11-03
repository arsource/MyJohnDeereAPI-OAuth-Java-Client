package com.deere.democlient.developWithDeereSampleApps;

import com.deere.api.axiom.generated.v3.File;
import com.deere.api.pagination.CollectionPage;
import com.deere.democlient.apis.AbstractApiBase;
import com.deere.democlient.brokers.FileDownloadBroker;
import com.deere.rest.HttpHeader;
import com.deere.rest.HttpStatus;
import com.deere.rest.RestRequest;
import com.deere.rest.RestResponse;
import org.codehaus.jackson.type.TypeReference;

/**
 * Created by mn56246 on 11/3/2015.
 */
public class FileListMonitor extends AbstractApiBase implements Runnable {
    private static final String X_DEERE_SIGNATURE = "X-Deere-Signature";
    private static String DEFAULT_OUTPUT_PATH = "FileDownloads";
    private String eTag;
    private String uri;

    public FileListMonitor(String eTag, String uri) {
        this.eTag = eTag;
        this.uri = uri;
    }

    public void run() {
        while (true) {
            System.out.println("Checking for file list changes");
            RestResponse response = getFileListChanges(uri, eTag);
            if (response.getResponseCode() == HttpStatus.OK) {
                handleFileListChanges(response);
                eTag = response.getHeaderFields().valueOf(X_DEERE_SIGNATURE);
            } else if (response.getResponseCode() == HttpStatus.NOT_MODIFIED) {
                System.out.println("No changes since previous call");
            }

            System.out.println("File list monitoring going to sleep");
            sleep(5);
        }
    }

    private void handleFileListChanges(RestResponse response) {
        CollectionPage<File> files = read(response).as(new TypeReference<CollectionPage<File>>() {});

        for (File file : files) {
            if (linksFrom(file).get("delete") != null) {
                handleDeletedFile(file);
            } else {
                handleNewOrUpdatedFile(file);
            }
        }
    }

    private void handleDeletedFile(File file) {
        System.out.println("Found deleted file: " + linksFrom(file).get("delete").getUri());
    }

    private void handleNewOrUpdatedFile(File file) {
        System.out.println("Found new or updated file: " + linksFrom(file).get("self").getUri() +
                " of type=" + file.getType() + " and status=" + file.getStatus() +
                " and new=" + file.getNew() + " and modified=" + file.getModifiedTime());
        downloadFile(file);
    }

    private void downloadFile(File file) {
        FileDownloadBroker fileDownloadBroker = new FileDownloadBroker();
        fileDownloadBroker.downloadFile(file, DEFAULT_OUTPUT_PATH);
    }

    private RestResponse getFileListChanges(String uri, String eTag) {
        final RestRequest request = oauthRequestTo(uri)
                .method("GET")
                .addHeader(new HttpHeader("Accept", V3_ACCEPTABLE_TYPE))
                .addHeader(new HttpHeader(X_DEERE_SIGNATURE, eTag))
                .build();
        return request.fetchResponse();
    }

    private static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {}
    }
}
