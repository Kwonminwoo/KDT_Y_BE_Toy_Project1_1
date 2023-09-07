package org.example.controller;

import org.example.FILEOPTION;
import org.example.exeption.InvalidFormatOptionException;
import org.example.model.Trip;
import org.example.repository.TripDao;
import org.example.service.SearchTripService;
import org.example.util.Verifier;

import java.util.List;

public class SearchTripController {
    // ----------Requirements----------
    // 아이디 받고, 해당하는 파일 리딩 후, Trip 객체 반환*
    // int fileFormat: view 호출: JSON / CSV, getFormatFromUser(), 올바른 파일 형식이 아님에 대한 예외처리
    // List<String> allTripList: view 호출: 여행 전체 리스트 출력, getAllTripList(), 여행이 하나도 없을 경우에 대한 예외처리
    // int tripId: view 호출: 여행 ID 입력, getTripId(), allTripList에 해당 ID가 없을 경우에 대한 예외처리
    // Trip trip: getTripInformationAsJson() 또는 getTripInformationAsCsv()
    // 내부에서, Itineraries 객체로 묶기
    // back to Main: return
    // --------------------------------

    private static SearchTripController instance;
    private final static int jsonOption = 1;

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
            Verifier.validateFileFormatOptionNumber(fileFormatOptionNumber); // 올바른 값을 입력했는지 검사할 의무
            fileFormat = getTransformedFormat(fileFormatOptionNumber); // 1:".json", 2: ".csv"
        } catch (InvalidFormatOptionException e) {
            e.printStackTrace();
        }
        return fileFormat;
    }

    private TripDao tripDao = new TripDao();
    private SearchTripService searchTripService = new SearchTripService(tripDao);

    public void launch() { // return: Trip
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
        // csv 로딩 처리 추가
        int tripId = 3; // id 입력받기 from viewer.intput
        Trip foundTrip = searchTripService.getTripById(tripId);
        // view 출력 메서드 실행: TripBrief의 printTripInfo 메서드를 이용하여 출력
        // e.g.,view.displayTripBriefs(tripBriefs);
        // 해당 여행 객체 -> viewer.output
    }
}
