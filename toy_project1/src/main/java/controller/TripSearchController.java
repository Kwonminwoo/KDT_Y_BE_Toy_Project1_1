package controller;

import service.TripSearchService;
import exeption.InvalidFormatOptionException;
import util.Verifier;

public class TripSearchController {
    // ----------Requirements----------
    // 아이디 받고, 해당하는 파일 리딩 후, Trip 객체 반환*
    // int fileFormat: view 호출: JSON / CSV, getFormatFromUser(), 올바른 파일 형식이 아님에 대한 예외처리
    // List<String> allTripList: view 호출: 여행 전체 리스트 출력, getAllTripList(), 여행이 하나도 없을 경우에 대한 예외처리
    // int tripId: view 호출: 여행 ID 입력, getTripId(), allTripList에 해당 ID가 없을 경우에 대한 예외처리
    // Trip trip: getTripInformationAsJson() 또는 getTripInformationAsCsv()
    // 내부에서, Itineraries 객체로 묶기
    // back to Main: return
    // --------------------------------
    private TripSearchController() {
    }

    static TripSearchService tripSearchService = new TripSearchService();

    public static void launch() { // return: Trip
        // view 호출 메서드 실행:
        // view는 사용자로부터 값을 받고, TripSearchController에게 전달할 의무가 있음
        // int formatOption = view.getFormatOptionFromUser();
        int fileFormatOptionNumber = 3;
        try {
            Verifier.validateFileFormatOptionNumber(fileFormatOptionNumber); // 올바른 값을 입력했는지 검사할 의무
            tripSearchService.setFileFormat(fileFormatOptionNumber);
        } catch (InvalidFormatOptionException e) {
            e.printStackTrace();
        }
    }
}
