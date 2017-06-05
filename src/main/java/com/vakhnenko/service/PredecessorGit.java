package com.vakhnenko.service;

import com.vakhnenko.dao.ChangesDao;
import com.vakhnenko.entity.Changes;
import com.vakhnenko.utils.ConnectionUtilHibernate;
import com.vakhnenko.utils.PrintHelper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.vakhnenko.App.logger;
import static com.vakhnenko.utils.Constants.*;
import static com.vakhnenko.utils.Dates.yyyyMMdd2String;
import static com.vakhnenko.utils.Files.*;
import static com.vakhnenko.utils.Strings.*;

public class PredecessorGit {
    private String[] commands;
    private ArrayList<String> files = new ArrayList<>();
    private ChangesDao changesDao = new ChangesDao(ConnectionUtilHibernate.getSessionFactory());

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
            case QUERY_COMMAND:
                if (commands.length != 2) {
                    PrintHelper.printSyntaxError();
                    break;
                }
                query(commands[SECOND_POSITION].toUpperCase());
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

                boolean foundAnnotation = tmpString.contains(ANNOTATION_SIGN);
                boolean foundClass = tmpString.contains(CLASS_SIGN);
                boolean foundMethod = tmpString.contains(METHOD_SIGN);

                if (!foundAnnotation & !foundClass & !foundMethod) {
                    continue;
                }

                if (foundAnnotation) {
                    String tmpAnnotation = getAnntotation(tmpString);
                    String tmpAnnotaionValue = getAnntotationValue(tmpString, tmpAnnotation);
                    logger.info("found annotation - " + swq(tmpAnnotation));

                    switch (tmpAnnotation) {
                        case "AUTHOR":
                            changes.setAuthor(tmpAnnotaionValue);
                            break;
                        case "DATE":
                            changes.setDate(yyyyMMdd2String(tmpAnnotaionValue));
                            break;
                        case "REASON":
                            changes.setReason(tmpAnnotaionValue);
                            break;
                    }
                }

                if (!foundAnnotation && foundClass) {
                    String tmpClass = getClazz(tmpString);
                    logger.info("found class - " + swq(tmpClass));
                    changes.setType(CLASS_TYPE);
                    changes.setUnit(tmpClass);

                    if (changes.filled()) {
                        logger.info("save changes - " + changes);
                        changesDao.add(changes);
                        changes.clear();
                    }
                }

                if (!foundAnnotation && foundMethod) {
                    String tmpMethod = getMethod(tmpString);
                    logger.info("found method - " + swq(tmpMethod));
                    changes.setType(METHOD_TYPE);
                    changes.setUnit(tmpMethod);

                    if (changes.filled()) {
                        logger.info("save changes - " + changes);
                        changesDao.add(changes);
                        changes.clear();
                    }
                }
            }

        } catch (IOException e) {
            PrintHelper.printIOError(file);
        }
    }

    private void query(String queryCommand) {
        switch (queryCommand) {
            case QUERY_HISTORY:
                queryHistory();
                break;
            default:
                PrintHelper.printSyntaxError();
        }
    }

    private void queryHistory() {
        List<Changes> listChanges = changesDao.list();

        if (listChanges.size() > 0) {
            for (Changes changes : listChanges) {
                logger.info(changes);
            }
        } else {
            logger.info("Not found any changes.");
        }
    }
}
