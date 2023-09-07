package org.example.util;

import org.example.exeption.FileNotExistException;
import org.example.exeption.InvalidFormatOptionException;

import java.io.File;
import java.util.List;

public class Verifier extends Exception {
    public static void validateFileFormatOptionNumber(int formatOption) throws InvalidFormatOptionException {
        if (formatOption != 1 && formatOption != 2) {
            throw new InvalidFormatOptionException(formatOption);
        }
    }

    public static void validExistenceOf(List<File> files) throws FileNotExistException {
        if (files.isEmpty()) {
            throw new FileNotExistException();
        }
    }
}