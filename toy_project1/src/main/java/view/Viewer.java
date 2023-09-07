package view;

import domain.Itinerary;
import domain.Trip;

import java.util.List;
import java.util.Scanner;

public class Viewer {
    Scanner sc = new Scanner(System.in);

    public void printMenuOption() {
        System.out.println("---------------------------------------");
        System.out.println("#여행 여정을 기록과 관리하는 SNS 서비스#");
        System.out.println("[메인 메뉴] 여행기록(1) 여행조회(2) 종료(3)");
    }

    public String receiveMenuOptionSelection() {
        System.out.print("시작할 메뉴번호를 입력 하세요: ");
        return sc.nextLine();
    }

    public void receiveTripInfo(Trip trip) {
        System.out.println("---여행정보 입력---");
        System.out.print("여행이름: ");
        trip.setTripName(sc.nextLine());;
        System.out.println("여행 시작날짜 입력\t(날짜 입력예시: 20230101)");
        trip.setStartDate(sc.nextLine());
        System.out.println("여행 종료날짜 입력\t(날짜 입력예시: 20230101)");
        trip.setEndDate(sc.nextLine());
    }

    public void receiveItineraryInfo(Itinerary itinerary) {
        System.out.println("---여정정보 입력---");
        System.out.print("출발지: ");
        itinerary.setDeparturePlace(sc.nextLine());
        System.out.print("도착지: ");
        itinerary.setDestination(sc.nextLine());
        System.out.print("출발시각 입력\t(시각 입력예시: 02030101-2030)");
        itinerary.setDepartureTime(sc.nextLine());
        System.out.print("도착시각 입력\t(시각 입력예시: 02030101-2030)");
        itinerary.setArrivalTime(sc.nextLine());
        System.out.print("체크인 시각 입력\t(시각 입력예시: 02030101-2030)");
        itinerary.setCheckIn(sc.nextLine());
        System.out.print("체크아웃 시각 입력\t(시각 입력예시: 02030101-2030)");
        itinerary.setCheckOut(sc.nextLine());
    }

    public String receiveAddItinerarySelection() {
        System.out.println("[여정 추가여부] 다른 여정 기록(1) 여정 기록 종료(2)");
        System.out.print("여정 추가여부를 선택하세요: ");
        return sc.nextLine();
    }

    public String receiveAddTripSelection() {
        System.out.println("[여행 추가여부] 다른 여행 기록(1) 메인으로(2)");
        System.out.print("여행 추가여부를 선택하세요: ");
        return sc.nextLine();
    }

    public String receiveFileTypeSelection() {
        System.out.println("[불러올 파일 종류 선택] JSON(1) CSV(2)");
        System.out.print("파일 종류를 선택하세요: ");
        return sc.nextLine();
    }

    public void printTripsNameAndId(List<Trip> trips) {
        System.out.println("---여행 리스트---");
        for (Trip trip : trips) {
            System.out.print(trip.getTripName() + " 여행   id: ");
            System.out.println(trip.getTripId());
        }
    }

    public String receiveTripId() {
        System.out.print("여정을 보고싶은 여행 id를 입력하세요: ");
        return sc.nextLine();
    }


    public void printTrip(Trip trip) {
        System.out.println("선택한 여행 " + trip.getTripName());
        System.out.println("여행 시작일: " + trip.getStartDate());
        System.out.println("여행 종료일: " + trip.getEndDate());
        System.out.println();

        List<Itinerary> itineraries = trip.getItineraries();
        for (Itinerary itinerary : itineraries) {
            System.out.println(itinerary.getDeparturePlace() + "로부터 " + itinerary.getDestination() + "의 여정");
            System.out.println("출발시각: " + itinerary.getDepartureTime() + "\t 도착시각: " + itinerary.getArrivalTime());
            System.out.println("체크인: " + itinerary.getCheckIn() + "\t 체크아웃: " + itinerary.getCheckOut());
            System.out.println("---------------------------------------");
        }
    }

    public void printExit() {
        System.out.println("서비스를 종료합니다.");
    }
}
