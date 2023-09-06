package view;

import domain.Itinerary;
import domain.Trip;

import java.util.List;
import java.util.Scanner;

public class Viewer {
    Scanner sc = new Scanner(System.in);

    public int receiveMenuSelectOption() {
        System.out.println("---------------------------------------");
        System.out.println("#여행 여정을 기록과 관리하는 SNS 서비스#");
        System.out.println("[메인 메뉴] 여행기록(1) 여행조회(2) 종료(3)");

        while (true) {
            try {
                System.out.print("시작할 메뉴번호를 입력 하세요: ");
                int selectedMenuOption = sc.nextInt();
                sc.nextLine();
                if (selectedMenuOption < 1 || selectedMenuOption > 3) {
                    System.out.println("메뉴 리스트에 있는 번호를 입력해주세요.");
                    continue;
                }
                return selectedMenuOption;
            } catch(Exception e){
                System.out.println("정수 번호를 입력해야 합니다.");
                sc.nextLine();
            }
        }
    }

    public void receiveTripInfo(Trip trip) {
        System.out.println("---여행정보 입력---");
        System.out.print("여행이름: ");
        trip.setTripName(sc.nextLine());;
        System.out.print("시작날짜: ");
        trip.setStartDate(sc.nextLine());
        System.out.print("종료날짜: ");
        trip.setEndDate(sc.nextLine());
    }

    public void receiveItinerary(Itinerary itinerary) {
        System.out.println("---여정정보 입력---");
        System.out.print("출발지: ");
        itinerary.setDeparturePlace(sc.nextLine());
        System.out.print("도착지: ");
        itinerary.setDestination(sc.nextLine());
        System.out.print("출발시각: ");
        itinerary.setDepartureTime(sc.nextLine());
        System.out.print("도착시각: ");
        itinerary.setArrivalTime(sc.nextLine());
        System.out.print("체크인 시각: ");
        itinerary.setCheckIn(sc.nextLine());
        System.out.print("체크아웃 시각: ");
        itinerary.setCheckOut(sc.nextLine());
    }

    public int receiveIfAddItinerary() {
        while (true) {
            try {
                System.out.println("[여정 추가여부] 여정 기록 종료(1) 다른 여정 기록(2)");
                int selectedItineraryOption = sc.nextInt();
                sc.nextLine();

                if (selectedItineraryOption < 1 || selectedItineraryOption > 2) {
                    System.out.println("리스트에 있는 번호를 입력해주세요.");
                    continue;
                }

                return selectedItineraryOption;

            } catch(Exception e){
                System.out.println("정수 번호를 입력해야 합니다.");
                sc.nextLine();
            }
        }
    }

    public int receiveIfAddTrip() {
        while (true) {
            try {
                System.out.println("[여행 추가여부] 메인으로(1) 다른 여행 기록(2)");
                int selectedTripOption = sc.nextInt();
                sc.nextLine();

                if (selectedTripOption < 1 || selectedTripOption > 2) {
                    System.out.println("리스트에 있는 번호를 입력해주세요.");
                    continue;
                }

                return selectedTripOption;

            } catch(Exception e){
                System.out.println("정수 번호를 입력해야 합니다.");
                sc.nextLine();
            }
        }
    }

    public int receiveFileType() {
        while (true) {
            try {
                System.out.println("[불러올 파일 종류 선택] JSON(1) CSV(2)");
                int selectedTripOption = sc.nextInt();
                sc.nextLine();

                if (selectedTripOption < 1 || selectedTripOption > 2) {
                    System.out.println("리스트에 있는 번호를 입력해주세요.");
                    continue;
                }

                return selectedTripOption;

            } catch(Exception e){
                System.out.println("정수 번호를 입력해야 합니다.");
                sc.nextLine();
            }
        }
    }

    public int receiveTripId(List<Trip> trips) {
        System.out.println("---여행 리스트---");
        for (Trip trip : trips) {
            System.out.print(trip.getTripName() + " 여행   id: ");
            System.out.println(trip.getTripId());
        }

        while (true) {
            try {
                System.out.print("여정을 보고싶은 여행 id를 입력하세요: ");
                int tripId = sc.nextInt();
                sc.nextLine();

                return tripId;

            } catch(Exception e){
                System.out.println("정수 번호를 입력해야 합니다.");
                sc.nextLine();
            }
        }
    }


    public void printItineraryOfTrip(Trip trip) {
        System.out.println("선택한 여행 " + trip.getTripName());
        System.out.println("여행 시작일: " + trip.getStartDate());
        System.out.println("여행 종료일: " + trip.getEndDate());
        System.out.println();

        List<Itinerary> itineraries = trip.getItineraries();
        for (Itinerary itinerary : itineraries) {
            System.out.println(itinerary.getDepartureTime() + "로부터 " + itinerary.getDestination() + "의 여정");
            System.out.println("출발시각: " + itinerary.getDepartureTime() + "\t 도착시각: " + itinerary.getArrivalTime());
            System.out.println("체크인: " + itinerary.getCheckIn() + "\t 체크아웃: " + itinerary.getCheckOut());
            System.out.println("---------------------------------------");
        }
    }

    public void printExit() {
        System.out.println("서비스를 종료합니다.");
    }


}
