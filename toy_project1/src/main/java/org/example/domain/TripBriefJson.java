package org.example.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
public class TripBriefJson implements TripBriefInterface{
    private int tripId;
    private String tripName;
    private String startDate;
    private String endDate;

    @JsonCreator
    public TripBriefJson(@JsonProperty("tripId") int tripId,
                         @JsonProperty("tripName") String tripName,
                         @JsonProperty("startDate") String startDate,
                         @JsonProperty("endDate") String endDate) {
        this.tripId = tripId;
        this.tripName = tripName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Collector<TripBriefJson, ?, List<TripBriefJson>> toList() {
        return Collectors.toList();
    }

    public void printTripInfo() { // TODO: FOR TEST. -> have to remove later.
        System.out.println("Trip ID: " + tripId);
        System.out.println("Trip Name: " + tripName);
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);
    }
}
