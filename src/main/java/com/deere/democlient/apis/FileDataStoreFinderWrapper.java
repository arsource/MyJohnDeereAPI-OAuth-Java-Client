package com.deere.democlient.apis;

import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;

import java.io.File;
import java.io.IOException;

public class FileDataStoreFinderWrapper {
    public FileDataStore getDataStore(File file) throws IOException {
        return FileDataStoreFinder.getDataStore(file);
    }
}
