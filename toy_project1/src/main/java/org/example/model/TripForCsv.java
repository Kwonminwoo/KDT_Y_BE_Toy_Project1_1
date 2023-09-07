//package org.example.model;
//
//import com.fasterxml.jackson.annotation.JsonCreator;
//import com.fasterxml.jackson.annotation.JsonProperty;
//
//import java.util.List;
//
//public class TripForCsv extends Trip {
//    public TripForCsv() {
//    }
//
//    @JsonCreator
//    public TripForCsv(@JsonProperty("tripId") int tripId,
//                       @JsonProperty("tripName") String tripName,
//                       @JsonProperty("startDate") String startDate,
//                       @JsonProperty("endDate") String endDate,
//                       @JsonProperty("Itineraries") List<Itinerary> itineraries) {
//        setTripId(tripId);
//        setTripName(tripName);
//        setStartDate(startDate);
//        setEndDate(endDate);
//        setItineraries(itineraries);
//    }
//
//    public void printTripInfo() { // TODO: FOR TEST. -> have to remove later.
//        System.out.println("Trip ID: " + getTripId());
//        System.out.println("Trip Name: " + getTripName());
//        System.out.println("Start Date: " + getStartDate());
//        System.out.println("End Date: " + getEndDate());
//        System.out.println("Itineraries: " + getItineraries());
//    }
//}
