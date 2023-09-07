package org.example.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.example.model.Itinerary;
import org.example.model.Trip;
import org.example.util.FileListLoader;
import org.example.util.FolderLocator;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TripDao {
    public List<Trip> findTripsAsJson() {
        Trip tripForJson;
        ObjectMapper objectMapper = new ObjectMapper();

        List<File> jsonFiles = FileListLoader.getJsonFiles(FolderLocator.getPath()); // TODO: Exception> File path is not correct. Please confirm your OS type.
        List<Trip> trips = new ArrayList<>();

        for (File jsonFile : jsonFiles) {
            try {
                tripForJson = objectMapper.readValue(jsonFile, Trip.class);
                trips.add(tripForJson);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return trips;
    }

    public List<Trip> findTripsAsCsv() {
        List<File> csvFiles = FileListLoader.getCsvFiles(FolderLocator.getPath()); // TODO: Exception> File path is not correct. Please confirm your OS type.

        List<Trip> trips = new ArrayList<>();
        for (File csvFile : csvFiles) {
            Trip trip = new Trip();
            try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
                List<String[]> records = reader.readAll();

                records.remove(0);

                List<Itinerary> itineraries = new ArrayList<>();
                for (String[] record : records) {
                    trip.setTripId(Integer.parseInt(record[0]));
                    trip.setTripName(record[1]);
                    trip.setStartDate(record[2]);
                    trip.setEndDate(record[3]);

                    Itinerary itinerary = new Itinerary();
                    itinerary.setItineraryId(Integer.parseInt(record[4]));
                    itinerary.setDeparturePlace(record[5]);
                    itinerary.setDestination(record[6]);
                    itinerary.setDepartureTime(record[7]);
                    itinerary.setArrivalTime(record[8]);
                    itinerary.setCheckIn(record[9]);
                    itinerary.setCheckOut(record[10]);

                    itineraries.add(itinerary);
                }
                trip.setItineraries(itineraries);
            } catch (IOException | CsvException e) {
                e.printStackTrace();
            }
            trips.add(trip);
        }
        return trips;
    }
}

