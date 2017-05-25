package com.vakhnenko;

import com.vakhnenko.service.PredecessorGit;

import org.apache.log4j.Logger;

public class App {
    public static final Logger logger = Logger.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Applicaion start...");
        PredecessorGit predecessorGit = new PredecessorGit(args);
        predecessorGit.run();
        logger.info("Applicaion done...");
    }
}

/*

public void processFilesFromFolder(File folder)
{
    File[] folderEntries = folder.listFiles();
    for (File entry : folderEntries)
    {
        if (entry.isDirectory())
        {
            processFilesFromFolder(entry);
            continue;
        }
        // иначе вам попался файл, обрабатывайте его!
    }
}

 */
