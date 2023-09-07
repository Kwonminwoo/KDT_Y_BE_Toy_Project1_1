package org.example.controller;

import org.example.exeption.InvalidFormatOptionException;
import org.example.model.Trip;
import org.example.repository.TripDao;
import org.example.service.SearchTripService;
import org.example.util.Verifier;

import java.util.List;

public class SearchTripController {
    private static SearchTripController instance;

    private SearchTripController() {
    }

    public static synchronized SearchTripController getInstance() {
        if (instance == null)
            instance = new SearchTripController();
        return instance;
    }

    private String getTransformedFormat(int fileFormatOptionNumber) {
        if (fileFormatOptionNumber == 1)
            return ".json";
        return ".csv";
    }

    public String setFileFormat(int fileFormatOptionNumber) {
        String fileFormat = "";
        try {
            Verifier.validateFileFormatOptionNumber(fileFormatOptionNumber);
            fileFormat = getTransformedFormat(fileFormatOptionNumber);
        } catch (InvalidFormatOptionException e) {
            e.printStackTrace();
        }
        return fileFormat;
    }

    private TripDao tripDao = new TripDao();
    private SearchTripService searchTripService = new SearchTripService(tripDao);

    public void launch() {
        // view 입력 메서드 실행: view는 사용자로부터 값을 받고, TripSearchController에게 전달할 의무가 있음
        // e.g.,int formatOption = view.getFormatOptionFromUser();

        int fileFormatOptionNumber = 1;
        String fileFormat = setFileFormat(fileFormatOptionNumber);

        List<Trip> trips = searchTripService.getTripsAs(fileFormat);
        // view 출력 메서드 실행: TripBrief의 printTripInfo 메서드를 이용하여 출력
        // e.g.,view.displayTripBriefs(tripBriefs);
//        for (Trip trip : trips) {
//            System.out.println(trip.toString());
//        }

        // id 입력받기 from viewer.intput
        int tripId = 3;
        Trip foundTrip = searchTripService.getTripById(tripId);
        // view 출력 메서드 실행:  해당 여행 객체 -> viewer.output
        // e.g.,view.displayTripBriefs(tripBriefs);
    }
}
