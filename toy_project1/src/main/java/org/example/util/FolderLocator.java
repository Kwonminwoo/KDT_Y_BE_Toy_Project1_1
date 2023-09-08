package org.example.util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FolderLocator {
    static final String KEY = "user.dir";
    static final String OS_NAME = System.getProperty("os.name");
    static final String MAC_OS = "Mac OS X";
    static final String WINDOWS_OS = "Windows";
    static final String RELATIVE_PATH_OF_MAC = "toy_project1/src/main/resources/";
    static final String RELATIVE_PATH_OF_WINDOWS = "src/main/resources/";

    public static String getPath() {
        Path filePath;
        Path currentPath = Paths.get(System.getProperty(KEY));
        if (OS_NAME.equals(MAC_OS)) {
            filePath = currentPath.resolve(RELATIVE_PATH_OF_MAC);
            return filePath + "/";
        } else if (OS_NAME.contains(WINDOWS_OS)) {
            filePath = currentPath.resolve(RELATIVE_PATH_OF_WINDOWS);
            return filePath + "\\";
        }
        return "";
    }
}