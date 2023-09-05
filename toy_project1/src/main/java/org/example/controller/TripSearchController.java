package org.example.controller;

import org.example.domain.TripBriefInterface;
import org.example.service.TripSearchService;

import java.util.List;

public class TripSearchController {
    // ----------Requirements----------
    // 아이디 받고, 해당하는 파일 리딩 후, Trip 객체 반환*
    // int fileFormat: view 호출: JSON / CSV, getFormatFromUser(), 올바른 파일 형식이 아님에 대한 예외처리
    // List<String> allTripList: view 호출: 여행 전체 리스트 출력, getAllTripList(), 여행이 하나도 없을 경우에 대한 예외처리
    // int tripId: view 호출: 여행 ID 입력, getTripId(), allTripList에 해당 ID가 없을 경우에 대한 예외처리
    // Trip trip: getTripInformationAsJson() 또는 getTripInformationAsCsv()
    // 내부에서, Itineraries 객체로 묶기
    // back to Main: return
    // --------------------------------

    private final static String TRIP_BASE_PATH = "/Users/dohk/Library/CloudStorage/Dropbox/code/fc_toy_project/KDT_Y_BE_Toy_Project1_1/";

    private static TripSearchController instance;

    private TripSearchController() {
    }

    public static synchronized TripSearchController getInstance() {
        if (instance == null)
            instance = new TripSearchController();
        return instance;
    }

    private TripSearchService tripSearchService = new TripSearchService();

    public void launch() { // return: Trip
        // view 입력 메서드 실행: view는 사용자로부터 값을 받고, TripSearchController에게 전달할 의무가 있음
        // e.g.,int formatOption = view.getFormatOptionFromUser();
        int fileFormatOptionNumber = 1;
        tripSearchService.setFileFormat(fileFormatOptionNumber);

        tripSearchService.setTripBasePath(TRIP_BASE_PATH);
        List<TripBriefInterface> tripBriefs = tripSearchService.getTripBriefs();
        // view 출력 메서드 실행: TripBrief의 printTripInfo 메서드를 이용하여 출력
        // e.g.,view.displayTripBriefs(tripBriefs);
        for(TripBriefInterface tripBrief : tripBriefs)
            tripBrief.printTripInfo();
    }
}
