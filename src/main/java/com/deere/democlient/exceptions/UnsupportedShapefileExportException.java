package com.deere.democlient.exceptions;

public class UnsupportedShapefileExportException extends Exception {
    public UnsupportedShapefileExportException() {
        super("This particular operation does not support shapefile export");
    }
}
