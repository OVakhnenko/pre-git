package com.vakhnenko;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
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
