package org.example.domain;

import java.util.ArrayList;
import java.util.List;

public class Trip {
    private int tripId;
    private String tripName;
    private String startDate;
    private String endDate;
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
}
