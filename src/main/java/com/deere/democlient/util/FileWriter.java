package com.deere.democlient.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.google.common.io.ByteStreams.copy;

public class FileWriter {

    public static void writeToFile(InputStream inputStream, String outputPath, String outputFilename) throws IOException {
        makeOutputDirectory(outputPath);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(outputPath, outputFilename));
            copy(inputStream, fos);
        } catch (IOException e) {
            System.err.println("Error writing file content");
            throw e;
        } finally {
            closeStream(inputStream);
            closeStream(fos);
        }
    }

    private static void closeStream(InputStream inputStream) {
        if (inputStream == null) {
            return;
        }

        try {
            inputStream.close();
        } catch (IOException e) {}
    }

    private static void closeStream(FileOutputStream fos) {
        if (fos == null) {
            return;
        }

        try {
            fos.close();
        } catch (IOException e) {}
    }

    private static void makeOutputDirectory(String path) {
        new File(path).mkdirs();
    }
}
