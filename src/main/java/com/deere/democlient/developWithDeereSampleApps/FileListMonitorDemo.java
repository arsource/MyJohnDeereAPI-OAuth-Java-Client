package com.deere.democlient.developWithDeereSampleApps;

import com.deere.api.axiom.generated.v3.File;
import com.deere.democlient.apis.AbstractApiBase;
import com.deere.democlient.brokers.FileUploadBroker;
import com.deere.democlient.brokers.ResourceDeletionBroker;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by mn56246 on 11/3/2015.
 */
public class FileListMonitorDemo {

    private static final String LOCAL_TEST_FILE_RELATIVE_PATH = "src\\main\\resources\\wdtTestFile.zip";

    public static void main(String[] args) throws IOException {
//        System.out.println("Enter the files uri that you want to monitor. The org must have the Data Usage Agreement accepted:");
        String uri = AbstractApiBase.baseUri + "organizations/263766/files";//sandbox example for DwD file interactions class: File Interactions org
//        String uri = baseUri + "organizations/263769/files";//sandbox example for DwD file interactions class: Grower Org from Partnerships class

        String eTag = "";
        FileListMonitor fileListMonitor = new FileListMonitor(eTag, uri);
        new Thread(fileListMonitor).start();

        sleep(15);

        System.out.println("Uploading a new file");
        FileUploadBroker fileUploadBroker = new FileUploadBroker();
        File fileMetaData = FileUploadBroker.constructFileMetaDataObject(UUID.randomUUID().toString());
        java.io.File file = new java.io.File(LOCAL_TEST_FILE_RELATIVE_PATH);
        String fileLocation = fileUploadBroker.postFileDetailsAndUploadContent(uri, fileMetaData, file);

        //Note: The file list monitoring thread may detect this new file multiple times due to file updates
        // (such as changes to the new file flag, file type detection, or file data processing).

        sleep(15);

        System.out.println("Deleting a file");
        ResourceDeletionBroker deletionBroker = new ResourceDeletionBroker();
        deletionBroker.deleteResource(fileLocation);
    }

    private static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {}
    }
}
