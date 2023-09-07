package org.example.util;

import org.example.exeption.FileNotExistException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileListLoader {
    private static String fileFormat;

    private static File[] getFilesFrom(String directoryPath) {
        File directory = new File(directoryPath);
        return directory.listFiles();
    }

    public static List<File> getJsonFiles(String directoryPath) {
        fileFormat = ".json";
        return getFiles(directoryPath);
    }

    public static List<File> getCsvFiles(String directoryPath) {
        fileFormat = ".csv";
        return getFiles(directoryPath);
    }

    private static List<File> getFiles(String directoryPath) {
        File[] wholeFiles = getFilesFrom(directoryPath);
        List<File> files = new ArrayList<>();

        if (wholeFiles.length == 0)
            throw new NullPointerException("Any files doesn't exist in this directory. Please confirm base path.");

        for (File file : wholeFiles) {
            if (file.isFile() && file.getName().endsWith(fileFormat))
                files.add(file);
        }

        try {
            Verifier.validExistenceOf(files);
        } catch (FileNotExistException e) {
            e.printStackTrace();
        }

        return files;
    }
}
