package service;

import domain.Itinerary;

public class RecordItinerary {

    private final Itinerary itinerary = new Itinerary();

    // 뷰어에서 값 받아오기
    public void recordItinerary(int 여정_id, String 출발지, String 도착지, String 출발_시간,
                                String 도착_시간, String 체크인, String 체크아웃) {

        itinerary.setItineraryId(여정_id);
        itinerary.setDeparturePlace(출발지);
        itinerary.setDestination(도착지);
        itinerary.setDepartureTime(출발_시간);
        itinerary.setArrivalTime(도착_시간);
        itinerary.setCheckIn(체크인);
        itinerary.setCheckOut(체크아웃);

    }

}
