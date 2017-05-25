package com.vakhnenko.service;

import com.vakhnenko.utils.PrintHelper;

import java.io.File;
import java.util.ArrayList;

import static com.vakhnenko.App.logger;
import static com.vakhnenko.utils.Constants.*;
import static com.vakhnenko.utils.Files.*;

public class PredecessorGit {
    private String[] commands;
    private ArrayList<String> files = new ArrayList<>();

    public PredecessorGit(String[] commands) {
        this.commands = commands;
    }

    public void run() {
        if (commands.length == 0) {
            PrintHelper.printSyntaxError();
            return;
        }

        switch (commands[FIRST_POSITION].toUpperCase()) {
            case ANALIZE_COMMAND:
                if (commands.length != 2) {
                    PrintHelper.printSyntaxError();
                    break;
                }
                analize(commands[SECOND_POSITION]);
                break;
            default:
                PrintHelper.printSyntaxError();
        }
    }

    private void analize(String directory) {
        files = getAllJavaFilesFromDirectory(directory);

        if (files == null) {
            logger.info("Directory " + directory + " maybe not exists or not found project files");
            PrintHelper.printSyntaxError();
        } else {
            for (String file : files) {
                logger.info(file);
            }
        }
    }
}
