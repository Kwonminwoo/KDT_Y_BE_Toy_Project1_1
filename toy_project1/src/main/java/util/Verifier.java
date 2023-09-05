package util;

import util.Exeption.InvalidFormatOptionException;

public class Verifier extends Exception {
    public static void validateFileFormatOptionNumber(int formatOption) throws InvalidFormatOptionException {
        if (formatOption != 1 && formatOption != 2) {
            throw new InvalidFormatOptionException(formatOption);
        }
    }
}