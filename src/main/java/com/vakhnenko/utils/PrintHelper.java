package com.vakhnenko.utils;

import static com.vakhnenko.App.logger;

public class PrintHelper {

    public static void printSyntaxError() {
        logger.error("");
        logger.error("Usage:");
        logger.error("pre-git analize <project_directory>");
        logger.error("pre-git query history");
    }

    public static void printIOError(String file) {
        logger.error(file + " IO error!");
    }
}
