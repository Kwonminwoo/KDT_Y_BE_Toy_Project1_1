package org.example.controller;

import org.example.exeption.InvalidFormatOptionException;
import org.example.model.Itinerary;
import org.example.model.Trip;
import org.example.model.TripForJson;
import org.example.repository.TripDao;
import org.example.service.SearchTripService;
import org.example.util.Verifier;
import org.example.view.Viewer;
import org.example.service.SaveTripService;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

public class SearchTripController {
    private static SearchTripController instance;
    private Viewer viewer = new Viewer();
    private final int ERROR = -9999999;
    private TripDao tripDao = new TripDao();

    private SearchTripService searchTripService = new SearchTripService(tripDao);
    private SaveTripService saveTripService = new SaveTripService();

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


    public void launch() {
        viewer.printMenuOption();
        int menuOption;
        while (true){
            menuOption = toInteger(viewer.receiveMenuOptionSelection());
            if (menuOption != -ERROR && isInOptionRange(menuOption, 3)) {
                break;
            }
        }
        switch (menuOption) {
            case 1:
                recordTrip();
                break;

            case 2:
                printTrip();
                break;

            case 3:
                terminate();
        }
        launch();

//
//
//
//
//        // view 입력 메서드 실행: view는 사용자로부터 값을 받고, TripSearchController에게 전달할 의무가 있음
//        // e.g.,int formatOption = view.getFormatOptionFromUser();
//
//        int fileFormatOptionNumber = 1;
//        String fileFormat = setFileFormat(fileFormatOptionNumber);
//
//        List<Trip> trips = searchTripService.getTripsAs(fileFormat);
//        // view 출력 메서드 실행: TripBrief의 printTripInfo 메서드를 이용하여 출력
//        // e.g.,view.displayTripBriefs(tripBriefs);
////        for (Trip trip : trips) {
////            System.out.println(trip.toString());
////        }
//
//        // id 입력받기 from viewer.intput
//        int tripId = 3;
//        Trip foundTrip = searchTripService.getTripById(tripId);
//        // view 출력 메서드 실행:  해당 여행 객체 -> viewer.output
//        // e.g.,view.displayTripBriefs(tripBriefs);
    }

    private void recordTrip() {
        Trip trip = new Trip();
        viewer.receiveTripInfo(trip);
        receiveItineraryFromViewer(trip.getItineraries());
        // 저장서비스.함수(trip) 저장서비스에 id 제외하고 여행과 하위 여정들이 채워진 trip 전달
//        trip = saveTripService.setIdToTrip();
        saveTripService.saveAsJSON(trip);
        saveTripService.saveAsCSV(trip);
        // 여행, 여정 저장 끝

        int ifAddTrip;
        while (true){
            ifAddTrip = toInteger(viewer.receiveAddTripSelection());
            if (ifAddTrip != ERROR && isInOptionRange(ifAddTrip, 2)) {
                break;
            }
        }

        if (ifAddTrip == 2) {
            return;
        }

        recordTrip();
    }

    private void printTrip() {
        int fileTypeNum;
        while (true){
            fileTypeNum = toInteger(viewer.receiveFileTypeSelection());
            if (fileTypeNum != ERROR && isInOptionRange(fileTypeNum, 2)) {
                break;
            }
        }

        List<Trip> trips = new ArrayList<>();
        if(fileTypeNum == 1){
            trips = searchTripService.getTripsAs(".json");
        } else{
            trips = searchTripService.getTripsAs(".csv");
        }
        viewer.printTripsNameAndId(trips);// 불러온 여행리스트 이름, ID로 출력


        int selectedTripId;
        while (true){
            selectedTripId = toInteger(viewer.receiveTripId());
            if (selectedTripId != ERROR) {
                break;
            }
        }
        Trip foundTrip = searchTripService.getTripById(selectedTripId); // 선택된 id의 trip받아오기
        viewer.printTrip(foundTrip);
    }


    private void receiveItineraryFromViewer(List<Itinerary> itineraries) {
        while (true) {
            Itinerary itinerary = new Itinerary();
            viewer.receiveItineraryInfo(itinerary);
            itineraries.add(itinerary);

            int ifAddItinerary;
            while (true){
                ifAddItinerary = toInteger(viewer.receiveAddItinerarySelection());
                if (ifAddItinerary != ERROR && isInOptionRange(ifAddItinerary, 2)) {
                    break;
                }
            }

            if (ifAddItinerary == 2) {
                break;
            }

        }
    }

    private void terminate() {
        viewer.printExit();
        exit(0);
    }

    private int toInteger(String num) {
        try {
            int integerNum = Integer.parseInt(num);
            return integerNum;
        } catch (NumberFormatException e) {
            System.out.println("정수가 아닙니다. 다시 입력해주세요.");
            return ERROR;
        }
    }

    private boolean isInOptionRange(int optionNum, int maxOptionNum) {
        if(optionNum < 1 || optionNum > maxOptionNum){
            System.out.println("리스트에 있는 번호를 입력해주세요.");
            return false;
        }
        return true;
    }
}
