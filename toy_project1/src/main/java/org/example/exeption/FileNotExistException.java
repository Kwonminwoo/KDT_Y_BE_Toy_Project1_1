package org.example.exeption;

public class FileNotExistException extends Exception {
    public FileNotExistException() {
        super("file is not exist.");
    }
}
