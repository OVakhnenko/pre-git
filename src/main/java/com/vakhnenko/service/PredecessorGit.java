package com.vakhnenko.service;

import com.vakhnenko.entity.Changes;
import com.vakhnenko.utils.PrintHelper;

import java.io.*;
import java.text.StringCharacterIterator;
import java.util.ArrayList;

import static com.vakhnenko.App.logger;
import static com.vakhnenko.utils.Constants.*;
import static com.vakhnenko.utils.Files.*;
import static com.vakhnenko.utils.Strings.*;

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
                analizeFile(file);
            }
        }
    }

    private void analizeFile(String file) {
        Changes changes = new Changes();

        logger.info("\n" + file);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

            String tmpString;
            while ((tmpString = bufferedReader.readLine()) != null) {
                tmpString = shrink(tmpString);
                logger.info(tmpString);

                boolean foundAnnotation = tmpString.indexOf(ANNOTATION_SIGN) != -1;
                boolean foundClass = tmpString.indexOf(CLASS_SIGN) != -1;
                boolean foundMethod = tmpString.indexOf(METHOD_SIGN) != -1;

                if (!foundAnnotation & !foundClass & !foundMethod) {
                    continue;
                }

                String tmpAnnotation = "";
                if (foundAnnotation) {
                    tmpAnnotation = getAnntotation(tmpString);
                    logger.info("found annotation - " + swq(tmpAnnotation));
                }

                String tmpClass = "";
                if (!foundAnnotation && foundClass) {
                    tmpClass = getClazz(tmpString);
                    logger.info("found class - " + swq(tmpClass));
                }

                String tmpMethod = "";
                if (!foundAnnotation && foundMethod) {
                    tmpMethod = getMethod(tmpString);
                    logger.info("found method - " + swq(tmpMethod));
                }
            }


        } catch (IOException e) {
            PrintHelper.printIOError(file);
        }
    }
}
