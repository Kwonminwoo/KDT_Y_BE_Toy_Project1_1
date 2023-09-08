package org.example.util;

import org.example.exeption.FileNotExistException;
import org.example.exeption.NotAppropriateOptionException;
import org.example.exeption.NotIntegerException;
import java.io.File;
import java.util.List;

public class Verifier extends Exception {
    public static void validExistenceOf(List<File> files) throws FileNotExistException {
        if (files.isEmpty()) {
            throw new FileNotExistException();
        }
    }
    public static int validOptionFormatIsInteger(String num) throws NotIntegerException{
        try {
            return Integer.parseInt(num);
        }catch (NumberFormatException e){
            throw new NotIntegerException();
        }
    }
    public static void validInOptionRange(int optionNum, int maxOptionNum) throws NotAppropriateOptionException {
        if(optionNum < 1 || optionNum > maxOptionNum){
            throw new NotAppropriateOptionException();
        }
    }
}