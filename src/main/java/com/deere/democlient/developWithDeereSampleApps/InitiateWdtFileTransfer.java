package com.deere.democlient.developWithDeereSampleApps;

import com.deere.democlient.apis.AbstractApiBase;
import com.deere.democlient.brokers.WdtFileTransferBroker;

/**
 * Created by mn56246 on 11/3/2015.
 */
public class InitiateWdtFileTransfer extends AbstractApiBase {
    public static void main(String[] args) {
        String uri = baseUri + "organizations/149058/fileTransfers";
        String fileUri = baseUri + "files/1353736076";
        String machineUri = baseUri + "machines/246087";
        WdtFileTransferBroker wdtFileTransferBroker = new WdtFileTransferBroker();
        String location = wdtFileTransferBroker.transferFile(uri, fileUri, machineUri);
        System.out.println("New file transfer uri: " + location);

        sleep(5);
        String status = wdtFileTransferBroker.getFileTransfer(location).getStatus();
        System.out.println("Status: " + status);
    }

    private static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {}
    }
}
