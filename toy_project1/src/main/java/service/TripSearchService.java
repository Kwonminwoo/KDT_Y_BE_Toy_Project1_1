package service;

import util.Exeption.InvalidFormatOptionException;
import util.Verifier;

public class TripSearchService {
    private String fileFormat;

    public void setFileFormat(int fileFormatOptionNumber) {
        try {
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
                throw new InvalidFormatOptionException(fileFormatOptionNumber);
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
