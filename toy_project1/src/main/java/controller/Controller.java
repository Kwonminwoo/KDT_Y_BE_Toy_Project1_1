package controller;

import domain.Itinerary;
import domain.Trip;
import view.Viewer;

import java.util.List;

import static java.lang.System.exit;

public class Controller {
    Viewer viewer = new Viewer();

    public void run() {

        int menuOption = viewer.receiveMenuSelectOption();

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
        do{
            Trip trip = new Trip();
            viewer.receiveTripInfo(trip);
            receiveItineraryFromViewer(trip.getItineraries());
            // 저장서비스.함수(trip) 저장서비스에 id 제외하고 여행과 하위 여정들이 채워진 trip 전달
        } while(viewer.receiveIfAddTrip() == 2);
    }

    private void printTrip() {
        int fileType = viewer.receiveFileType();

        List<Trip> trips;
        if(fileType == 1){
            // trips = JSON 파일로 불러오기
        } else{
            // trips = CSV 파일로 불러오기
        }

        Trip trip = new Trip();
        // trip = viewer.receiveTripId(trips)); //사용자로부터 ID 가져와서 trip 가져오기
        viewer.printTrip(trip); // 해당 trip 의 여정들 출력
    }

    private void terminate() {
        viewer.printExit();
        exit(0);
    }

    private void receiveItineraryFromViewer(List<Itinerary> itineraries) {
        do{
            Itinerary itinerary = new Itinerary();
            viewer.receiveItinerary(itinerary);
            itineraries.add(itinerary);
        }while(viewer.receiveIfAddItinerary() == 2);
    }
}
