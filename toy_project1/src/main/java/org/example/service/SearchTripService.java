package org.example.service;

import org.example.model.Trip;
import org.example.repository.TripDao;

import java.util.List;

public class SearchTripService {
    private final TripDao tripDao;

    public SearchTripService(TripDao tripDao) {
        this.tripDao = tripDao;
    }

    public List<Trip> getTripsAs(String fileFormat) {
        if (".json".equals(fileFormat)) {
            return tripDao.findTripsAsJsonFrom(); // TODO: Exception: Empty Exception
        }
        return tripDao.findTripsAsCsvFrom();
    }

    public Trip getTripById(int tripId) {
        // DAO
        Trip foundTrip = new Trip();
        List<Trip> trips = tripDao.findTripsAsJsonFrom();
        for (Trip trip : trips) {
            if (trip.getTripId() == tripId) {
                foundTrip = trip;
            }
        }
        return foundTrip;
    }
}
