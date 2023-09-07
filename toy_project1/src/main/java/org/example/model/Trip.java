package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Trip {
    @JsonProperty("trip_id")
    private int tripId;
    @JsonProperty("trip_name")
    private String tripName;
    @JsonProperty("start_date")
    private String startDate;
    @JsonProperty("end_date")
    private String endDate;
    @JsonProperty("itineraries")
    private List<Itinerary> Itineraries = new ArrayList<>();

    public Trip(int tripId, String tripName, String startDate, String endDate) {
        this.tripId = tripId;
        this.tripName = tripName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Trip() {
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<Itinerary> getItineraries() {
        return Itineraries;
    }

    public void setItineraries(List<Itinerary> itineraries) {
        Itineraries = itineraries;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Trip ID: ").append(tripId).append("\n");
        stringBuilder.append("Trip Name: ").append(tripName).append("\n");
        stringBuilder.append("Start Date: ").append(startDate).append("\n");
        stringBuilder.append("End Date: ").append(endDate).append("\n");

        for (Itinerary itinerary : Itineraries) {
            stringBuilder.append("Itinerary ID: ").append(itinerary.getItineraryId()).append("\n");
            stringBuilder.append("Departure Place: ").append(itinerary.getDeparturePlace()).append("\n");
            stringBuilder.append("Destination: ").append(itinerary.getDestination()).append("\n");
            stringBuilder.append("Departure Time: ").append(itinerary.getDepartureTime()).append("\n");
            stringBuilder.append("Arrival Time: ").append(itinerary.getArrivalTime()).append("\n");
            stringBuilder.append("Check-In: ").append(itinerary.getCheckIn()).append("\n");
            stringBuilder.append("Check-Out: ").append(itinerary.getCheckOut()).append("\n");
        }

        return stringBuilder.toString();
    }
}