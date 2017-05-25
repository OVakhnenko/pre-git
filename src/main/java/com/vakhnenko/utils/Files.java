package com.vakhnenko.utils;

import java.io.File;
import java.util.ArrayList;

import static com.vakhnenko.utils.Strings.*;

public class Files {
    private static ArrayList<String> files;

    private static void processFilesFromFolder(File folder) {
        String tmp;
        File[] folderEntries = folder.listFiles();

        if (folderEntries != null) {
            for (File entry : folderEntries) {
                if (entry.isDirectory()) {
                    processFilesFromFolder(entry);
                    continue;
                }
                tmp = entry.getAbsolutePath().toUpperCase();
                if ("JAVA".equals(getFileExtension(tmp))) {
                    files.add(tmp);
                }
            }
        }
    }

    public static ArrayList<String> getAllJavaFilesFromDirectory(String directory) {
        files = new ArrayList<>();
        processFilesFromFolder(new File(directory));

        return files;
    }
}
