package org.example.util;

import org.example.exeption.FileNotExistException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileListLoader {
    private static String fileFormat;

    private static File[] get(String directoryPath) {
        File directory = new File(directoryPath);
        return directory.listFiles();
    }

    public static List<File> getJsonFile(String directoryPath) {
        fileFormat = ".json";
        File[] wholeFiles = get(directoryPath);
        List<File> jsonFiles = new ArrayList<>();

        if (wholeFiles.length == 0)
            throw new NullPointerException("Any files doesn't exist in this directory. Please confirm base path.");

        for (File file : wholeFiles) {
            if (file.isFile() && file.getName().endsWith(fileFormat))
                jsonFiles.add(file);
        }

        try {
            Verifier.validExistenceOf(jsonFiles);
        } catch (FileNotExistException e) {
            e.printStackTrace();
        }

        return jsonFiles;
    }
}
