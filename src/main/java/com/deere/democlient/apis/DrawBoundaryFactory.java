package com.deere.democlient.apis;

import com.deere.democlient.brokers.GenericListBroker;
import com.deere.democlient.brokers.OrganizationListBroker;

import java.util.Scanner;

public class DrawBoundaryFactory {

    public static DrawBoundary getDrawBoundary() {
        DrawBoundary drawBoundary = new DrawBoundary();
        drawBoundary.setOrganizationBroker(new OrganizationListBroker());
        drawBoundary.setFieldBroker(new GenericListBroker());
        drawBoundary.setScanner(new Scanner(System.in));
        drawBoundary.setApiBaseWrapper(new AbstractApiBaseWrapper());
        drawBoundary.setFileDataStoreFinder(new FileDataStoreFinderWrapper());
        return drawBoundary;
    }
}
