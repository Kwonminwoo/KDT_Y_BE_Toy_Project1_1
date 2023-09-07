package org.example.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TripForJson extends Trip {
    public TripForJson() {
    }

    @JsonCreator
    public TripForJson(@JsonProperty("tripId") int tripId,
                       @JsonProperty("tripName") String tripName,
                       @JsonProperty("startDate") String startDate,
                       @JsonProperty("endDate") String endDate,
                       @JsonProperty("Itineraries") List<Itinerary> itineraries) {
        setTripId(tripId);
        setTripName(tripName);
        setStartDate(startDate);
        setEndDate(endDate);
        setItineraries(itineraries);
    }
}
