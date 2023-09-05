package org.example.domain;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public interface TripBriefInterface {
    int getTripId();
    String getTripName();
    String getStartDate();
    String getEndDate();

    public static Collector<TripBriefInterface, ?, List<TripBriefInterface>> toList() {
        return Collectors.toList();
    }

    void printTripInfo(); // TODO: Remove later
}
