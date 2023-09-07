package org.example;

import org.example.controller.SearchTripController;

public class Main {
    public static void main(String[] args) {
        SearchTripController controller = SearchTripController.getInstance();
        controller.launch();
    }
}