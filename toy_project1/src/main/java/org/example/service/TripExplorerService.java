package org.example.service;

import org.example.model.Trip;
import org.example.repository.TripDao;

import java.util.List;

public class TripExplorerService {
    private final TripDao tripDao;

    public TripExplorerService(TripDao tripDao) {
        this.tripDao = tripDao;
    }

    public List<Trip> getTripsAs(String fileFormat) {
        if (".json".equals(fileFormat)) {
            return tripDao.findTripsAsJson(); // TODO: Exception: Empty Exception
        }
        return tripDao.findTripsAsCsv();
    }

    public Trip getTripById(int tripId) {
        // DAO
        Trip foundTrip = new Trip();
        List<Trip> trips = tripDao.findTripsAsJson();
        for (Trip trip : trips) {
            if (trip.getTripId() == tripId) {
                foundTrip = trip;
            }
        }
        return foundTrip;
    }
}
