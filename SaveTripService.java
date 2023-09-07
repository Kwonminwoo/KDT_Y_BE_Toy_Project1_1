package service;
import model.Trip;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public class SaveTripService {


    public void setIdToTrip(Trip trip, List<Trip> tripList) {

        int maxID = 0;

        //최대ID값 다음 값으로 ID입력
        for (int i = 0; i < tripList.size(); i++) {
            if (tripList.get(i).getTripId() > maxID) {
                maxID = tripList.get(i).getTripId();
            }
        }
        trip.setTripId(maxID + 1);

    }


    public void saveAsJSON(Trip trip) {
        ObjectMapper objectMapper = new ObjectMapper();
        //TripName_ID.json 으로 파일 생성 후 , 인코딩 설정
        String filename = trip.getTripName() + "_" + trip.getTripId() + ".json";
        Writer writer = new OutputStreamWriter(new FileOutputStream(filename, StandardCharsets.UTF_8);
        objectMapper.writeValue(writer, trip);

        writer.close();

        moveFileToResources(fileName);
    }



    public void saveAsCSV(Trip trip) {
        //TripName_ID.csv 으로 파일 생성 후 , 인코딩 설정
        String filename = trip.getTripName() + "_" + trip.getTripId() + ".csv";
        Writer writer = new OutputStreamWriter(new FileOutputStream(filename, StandardCharsets.UTF_8);
        //CSV 헤더(컬럼이름) 작성
        writer.append("trip_id,trip_name,start_date,end_date,itinerary_id,departure,destination,departure_time,arrival_time,check_in,check_out\n");

        for (Itinerary itinerary : trip.getItineraries()) {
            writer.append(trip.getTripId()).append(",")
                    .append(trip.getTripName()).append(",")
                    .append(trip.getStartDate()).append(",")
                    .append(trip.getEndDate()).append(",")
                    .append(String.valueOf(itinerary.getItineraryId())).append(",")
                    .append(itinerary.getDeparture()).append(",")
                    .append(itinerary.getDestination()).append(",")
                    .append(itinerary.getDepartureTime()).append(",")
                    .append(itinerary.getArrivalTime()).append(",")
                    .append(itinerary.getCheckIn()).append(",")
                    .append(itinerary.getCheckOut()).append("\n");
        }
        writer.close();

        moveFileToResources(fileName);
    }



    private void moveFileToResources(String fileName) throws IOException {
        Path source = Paths.get(fileName);
        Path target = Paths.get("resources", fileName);

        Files.move(source, target);
    }

}




//todo 1. Itinerary가 입력받으면서 객체로 따로 분리될 필요는?
//     2. [?] FileWriter와 Writer는 인코딩문제로  outputStreamWriter로 인코딩 지정하기 위해 Writer사용
//     3. trip 모델 안에 addItinerary ? 어떤 형태로 여정을 받는지
//        public void addItinerary(Itinerary itinerary) {
//        this.itineraries.add(itinerary);}
//     4. 테스트용이성 ?
//     5. gradle 빌드 : OpenCSV
//     6. resource 저장 상대경로 , 및 파일 이동 후 예외처리
//     7. enum이나 상수 사용 방법
//     8. departure이 arrival 보다 나중인지, check_in이 check_out보다 나중인지 유효성 체크?
//     9. Viewer에서 날짜 시간 입력받을때, DataFormatter 사용하는지?.?
//    10. 파일 네이밍 방식 적절 여부
//    11. 2-3) 예외처리 -->>  JSON, CSV 형식에 맞지 않으면 오류 메세지를 출력한다.??

