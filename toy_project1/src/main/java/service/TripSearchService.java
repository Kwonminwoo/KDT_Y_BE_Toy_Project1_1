package service;

import exeption.InvalidFormatOptionException;
import util.Verifier;

public class TripSearchService {
    private String fileFormat;

    public void setFileFormat(int fileFormatOptionNumber) {
        try {
            Verifier.validateFileFormatOptionNumber(fileFormatOptionNumber); // 올바른 값을 입력했는지 검사할 의무
            fileFormat = getTransformedFormat(fileFormatOptionNumber); // 1:".json", 2: ".csv"
            System.out.println("fileFormat = " + fileFormat);
        } catch (InvalidFormatOptionException e) {
            e.printStackTrace();
        }
    }

    private static String getTransformedFormat(int fileFormatOptionNumber) throws InvalidFormatOptionException {
        switch (fileFormatOptionNumber) {
            case 1:
                return ".json";
            case 2:
                return ".csv";
            default:
                return "";
        }
    }


//    private void findTripFile() {
//
//    }
//
//    public void getAllTripList() {
//
//    }
}
