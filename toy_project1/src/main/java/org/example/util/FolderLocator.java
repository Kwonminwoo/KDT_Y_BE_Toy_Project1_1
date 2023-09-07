package org.example.util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FolderLocator {
    final static String key = "user.dir";
    final static String relativePath = "toy_project1/src/main/resources/";

    public static String getPath() {
        Path currentPath = Paths.get(System.getProperty(key));
        Path filePath = currentPath.resolve(relativePath);
        return filePath.toString() + '/';
    }
}
