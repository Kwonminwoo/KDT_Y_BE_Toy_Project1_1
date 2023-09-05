package org.example.domain;

import lombok.Getter;

public class TripBriefCsv implements TripBriefInterface{


    @Override
    public int getTripId() {
        return 0;
    }

    @Override
    public String getTripName() {
        return null;
    }

    @Override
    public String getStartDate() {
        return null;
    }

    @Override
    public String getEndDate() {
        return null;
    }

    @Override
    public void printTripInfo() {

    }
}
