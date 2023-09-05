package org.example.domain;

import java.util.ArrayList;
import java.util.List;

public class TripCollection {
    private List<Trip> tripCollection = new ArrayList<>();

    public boolean isEmpty() {
        return tripCollection.isEmpty();
    }

    private TripCollection() {
    }

    public void add(Trip trip) {
        tripCollection.add(trip);
    }

    public List<Trip> get() {
        if (tripCollection.isEmpty())
            throw new RuntimeException("No Trip Object present");
        return tripCollection;
    }
}
