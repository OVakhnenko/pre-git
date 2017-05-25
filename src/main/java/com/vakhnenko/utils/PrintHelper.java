package com.vakhnenko.utils;

import static com.vakhnenko.App.logger;

public class PrintHelper {

    public static void printSyntaxError() {
        logger.error("Usage:");
        logger.error("pre-git analize <project_directory>");
    }
}
