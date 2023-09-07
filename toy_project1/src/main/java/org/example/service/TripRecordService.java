package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Itinerary;
import org.example.model.Trip;
import org.example.util.FileListLoader;
import org.example.util.FolderLocator;

import java.io.*;

public class TripRecordService {
    public void setIdToTrip(Trip trip) {
        int lastTripId = FileListLoader.getNumberFromLastFiles(FolderLocator.getPath()); // TODO: Exception> File path is not correct. Please confirm your OS type.
        trip.setTripId(++lastTripId);

        // TODO: need to make method for splitting role. Below is for itineraryId but this method name is setIdToTrip.
        int itineraryId = 1;
        for (Itinerary itinerary : trip.getItineraries()) {
            itinerary.setItineraryId(itineraryId++);
        }
    }

    public void saveJsonToFile(Trip trip) {
//        System.out.println("trip id" + trip.getTripId()); // TODO: magic number
        ObjectMapper objectMapper = new ObjectMapper();
        String fileName = FolderLocator.getPath() + "trip_" + trip.getTripId() + ".json"; // TODO: 2 magic number,
        // TODO: Exception> File path is not correct. Please confirm your OS type.
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(fileName))) { //
            objectMapper.writeValue(writer, trip);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveCsvToFile(Trip trip) {
        String csvColumnName = "trip_id,trip_name,start_date,end_date,itinerary_id,departure,destination,departure_time,arrival_time,check_in,check_out\n"; // TODO: magic number
        String filename = FolderLocator.getPath() + "trip_" + trip.getTripId() + ".csv"; // TODO: magic number
        // TODO: Exception> File path is not correct. Please confirm your OS type.
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(filename))) {
            writer.append(csvColumnName);
            for (Itinerary itinerary : trip.getItineraries()) {
                writer.append(Integer.toString(trip.getTripId())).append(",") // TODO: magic number
                        .append(trip.getTripName()).append(",")
                        .append(trip.getStartDate()).append(",")
                        .append(trip.getEndDate()).append(",")
                        .append(String.valueOf(itinerary.getItineraryId())).append(",")
                        .append(itinerary.getDeparturePlace()).append(",")
                        .append(itinerary.getDestination()).append(",")
                        .append(itinerary.getDepartureTime()).append(",")
                        .append(itinerary.getArrivalTime()).append(",")
                        .append(itinerary.getCheckIn()).append(",")
                        .append(itinerary.getCheckOut()).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}