package controller;

import domain.Itinerary;
import domain.Trip;
import view.Viewer;

import java.util.List;

import static java.lang.System.exit;

public class Controller {
    final int ERROR = -9999999;
    Viewer viewer = new Viewer();

    public void run() {
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

        run();
    }

    private void recordTrip() {

        Trip trip = new Trip();
        viewer.receiveTripInfo(trip);
        receiveItineraryFromViewer(trip.getItineraries());
        // 저장서비스.함수(trip) 저장서비스에 id 제외하고 여행과 하위 여정들이 채워진 trip 전달

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

        List<Trip> trips;
        if(fileTypeNum == 1){
            // trips = JSON 파일로 불러오기
        } else{
            // trips = CSV 파일로 불러오기
        }
        // viewer.printTripList(trips); 불러온 여행리스트 이름, ID로 출력

        int selectedTripId;
        while (true){
            selectedTripId = toInteger(viewer.receiveTripId());
            if (selectedTripId != ERROR) {
                break;
            }
        }
        // trip = 서비스.id로trip반환(selectedTripId)  선택된 id의 trip받아오기
        // viewer.printTrip(trip1);
    }

    private void terminate() {
        viewer.printExit();
        exit(0);
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
