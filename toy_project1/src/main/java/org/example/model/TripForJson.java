package org.example.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TripForJson extends Trip {
    public TripForJson() {
    }

    @JsonCreator
    public TripForJson(@JsonProperty("trip_id") int tripId,
                       @JsonProperty("trip_name") String tripName,
                       @JsonProperty("start_date") String startDate,
                       @JsonProperty("end_date") String endDate,
                       @JsonProperty("itineraries") List<Itinerary> itineraries) {
        setTripId(tripId);
        setTripName(tripName);
        setStartDate(startDate);
        setEndDate(endDate);
        setItineraries(itineraries);
    }
}
