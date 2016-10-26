package com.deere.democlient.apis;

import com.deere.democlient.brokers.ShapefileDownloadBroker;
import com.deere.democlient.exceptions.UnsupportedShapefileExportException;

import java.io.IOException;
import java.util.UUID;

public class AsyncShapefileDownload {
    private static final String DOWNLOAD_DIRECTORY = "FileDownloads";

    public static void main(String [] args) {
        //NOTE: You must replace the below URI with one that you have access to
        String fieldOperationUri = "https://sandboxapi.deere.com/platform/fieldOps/MjI0NzY0XzU3MDQyMjNmMmE1YjBlMDUwOGJjYjY5MA";

        ShapefileDownloadBroker shapefileDownloadBroker = new ShapefileDownloadBroker();
        try {
            shapefileDownloadBroker.download(fieldOperationUri, DOWNLOAD_DIRECTORY, UUID.randomUUID().toString() + ".zip");
        } catch (IOException|UnsupportedShapefileExportException e) {
            System.err.println("Shapefile download failed");
            System.err.println(e.getMessage());
        }
    }

}
