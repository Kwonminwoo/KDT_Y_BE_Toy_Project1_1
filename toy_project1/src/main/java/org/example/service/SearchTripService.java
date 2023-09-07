package org.example.service;

import org.example.model.Trip;
import org.example.repository.TripDao;

import java.util.ArrayList;
import java.util.List;

public class SearchTripService {
    private String tripBasePath;
    private final TripDao tripDao;

    public SearchTripService(TripDao tripDao) {
        this.tripDao = tripDao;
    }

//    public void setTripBasePath(String tripBasePath) {
//        this.tripBasePath = tripBasePath;
//    }

    public List<Trip> getTripsAs(String fileFormat) {
        // 여행 파일들로부터 선별적으로 읽고(바깥에서 반복호출 될 것)
        // 읽은 것을 객체로 묶어서 전달
        if (".json".equals(fileFormat)) {
            return tripDao.findTripsAsJsonFrom(); // Exception: Empty Exception
        }
        return tripDao.findTripsAsCsvFrom();
    }

    public Trip getTripById(int tripId) {
        // DAO
        Trip foundTrip = new Trip();
        List<Trip> trips = tripDao.findTripsAsJsonFrom();
        for (Trip trip : trips) {
            if(trip.getTripId() == tripId){
                foundTrip = trip;
            }
        }
        return foundTrip;
    }
}
