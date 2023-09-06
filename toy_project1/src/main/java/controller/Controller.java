package controller;

import domain.Trip;
import view.Viewer;

import java.util.List;

import static java.lang.System.exit;

public class Controller {
    Viewer viewer = new Viewer();

    public void run() {

        int menuOption = viewer.receiveMenuSelectOption();

        switch (menuOption) {
            case 1:
                recordTrip();
                break;

            case 2:
                printTrip();
                break;

            case 3:
                terminate();
        }

        run();
    }

    private void recordTrip() {
        do{
            // 서비스.여행저장(viewer.receiveTripInfo());
        } while(viewer.receiveIfAddTrip() == 2);
    }

    private void printTrip() {
        // 서비스.ID로 Trip 가져오기(viewer.receiveTripId(가져온 Trip 리스트)); //사용자로부터 ID 가져와서 다시 서비스에게 전달
        // viewer.printItineraryOfTrip(가져온 trip); 해당 trip 의 여정들 출력
    }

    private void terminate() {
        viewer.printExit();
        exit(0);
    }
}
