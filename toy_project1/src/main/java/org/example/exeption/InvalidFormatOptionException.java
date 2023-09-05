package org.example.exeption;

public class InvalidFormatOptionException extends Exception {
    public InvalidFormatOptionException(int fileFormatOptionNumber) {
        super("Invalid format option: " + fileFormatOptionNumber + ". Please choose 1 for JSON or 2 for CSV.");
    }
}