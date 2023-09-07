package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Itinerary;
import org.example.model.Trip;
import org.example.model.TripForJson;
import org.example.repository.TripDao;
import org.example.util.FolderLocator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SaveTripService {
    TripDao tripDao = new TripDao();

    public void setIdToTrip(Trip trip) {
        List<Trip> trips = tripDao.findTripsAsJsonFrom();

        int lastTripId = trips.isEmpty() ? 0 : trips.get(trips.size() - 1).getTripId();
        trip.setTripId(lastTripId + 1);
        int itineraryId = 1;

        //최대ID값 다음 값으로 ID입력

        for (Itinerary itinerary : trip.getItineraries()) {
            itinerary.setItineraryId(itineraryId++);
        }
    }


    public TripForJson saveAsJSON(Trip trip) {
        setIdToTrip(trip);
//        TripForJson tripForJson = (TripForJson) trip;
        ObjectMapper objectMapper = new ObjectMapper();
        //TripName_ID.json 으로 파일 생성 후 , 인코딩 설정
        String filename = FolderLocator.getPath() + "\\trip_" + trip.getTripId() + ".json";
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(filename))) {
            objectMapper.writeValue(writer, trip);

//            moveFileToResources(filename);
        } catch (IOException e){
            e.printStackTrace();
        }
        return trip;
    }



    public Trip saveAsCSV(Trip trip) {
        setIdToTrip(trip);
        String csvColumnName = "trip_id,trip_name,start_date,end_date,itinerary_id,departure,destination,departure_time,arrival_time,check_in,check_out\n";
        //TripName_ID.csv 으로 파일 생성 후 , 인코딩 설정
        String filename = FolderLocator.getPath() + "\\trip_" + trip.getTripId() + ".csv";
        try(Writer writer = new OutputStreamWriter(new FileOutputStream(filename))) {

//            new FileOutputStream(filename, StandardCharsets.UTF_8)
            //CSV 헤더(컬럼이름) 작성
            writer.append(csvColumnName);

            for (Itinerary itinerary : trip.getItineraries()) {
                writer.append(Integer.toString(trip.getTripId())).append(",")
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
            

//            moveFileToResources(filename);
        } catch (IOException e){
            e.printStackTrace();
        }
        return trip;
    }



//    private void moveFileToResources(String fileName) throws IOException {
//        Path source = Paths.get(fileName);
//        Path target = Paths.get("resources", fileName);
//
//        Files.move(source, target);
//    }

}




//todo 1. 테스트용이성 ?
//     2. gradle 빌드 : OpenCSV
//     3. resource 저장 상대경로 , 및 파일 이동 후 예외처리
//     4. enum이나 상수 사용 방법
//     5. departure이 arrival 보다 나중인지, check_in이 check_out보다 나중인지 유효성 체크?
//     6. 2-3) 예외처리 -->>  JSON, CSV 형식에 맞지 않으면 오류 메세지를 출력한다.??

