package org.example.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.example.model.Itinerary;
import org.example.model.Trip;
//import org.example.model.TripForCsv;
import org.example.model.TripForJson;
import org.example.util.FileListLoader;
import org.example.util.FolderLocator;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TripDao {
    public List<Trip> findTripsAsJsonFrom() {
        TripForJson tripForJson;
        ObjectMapper objectMapper = new ObjectMapper();

        List<File> jsonFiles = FileListLoader.getJsonFiles(FolderLocator.getPath());
        List<Trip> trips = new ArrayList<>();

        for (File jsonFile : jsonFiles) {
            try {
                tripForJson = objectMapper.readValue(jsonFile, TripForJson.class);
                trips.add(tripForJson);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return trips;
    }

    public List<Trip> findTripsAsCsvFrom() {
        List<File> csvFiles = FileListLoader.getCsvFiles(FolderLocator.getPath());
//        List<Trip> trips = new ArrayList<>();
//        Trip tripForCsv = new Trip();

        List<Trip> trips = new ArrayList<>();
        for (File csvFile : csvFiles) {
            try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
                List<String[]> records = reader.readAll();

                records.remove(0);

                for (String[] record : records) {
                    Trip trip = new Trip();
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

                    trip.getItineraries().add(itinerary);

                    trips.add(trip);
                }
            } catch (IOException | CsvException e) {
                e.printStackTrace();
            }
        }
        return trips;
    }

//    public Trip findTripByIdFromJson(int id) { // 웹 MVC 패턴을 굳이 따랐다. 그래서 이렇게 했다.
//        // id로 파일 읽어서 json 반환
//
//        return null;
//    }
//
//    public Trip findTripByIdFromCsv(int id) {
//
//        return null;
//    }

    public void saveTripToJson(Trip trip) {
        // 파일
    }

    public void saveTripToCsv(Trip trip) {

    }
}

