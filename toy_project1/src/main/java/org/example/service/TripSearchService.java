package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domain.TripBriefCsv;
import org.example.domain.TripBriefInterface;
import org.example.domain.TripBriefJson;
import org.example.exeption.InvalidFormatOptionException;
import org.example.util.FileListLoader;
import org.example.util.Verifier;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TripSearchService {
    private String fileFormat;
    private String tripBasePath;

    public void setTripBasePath(String tripBasePath) {
        this.tripBasePath = tripBasePath;
    }

    public void setFileFormat(int fileFormatOptionNumber) {
        try {
            Verifier.validateFileFormatOptionNumber(fileFormatOptionNumber); // 올바른 값을 입력했는지 검사할 의무
            fileFormat = getTransformedFormat(fileFormatOptionNumber); // 1:".json", 2: ".csv"
        } catch (InvalidFormatOptionException e) {
            e.printStackTrace();
        }
    }

    private static String getTransformedFormat(int fileFormatOptionNumber) {
        if (fileFormatOptionNumber == 1)
            return ".json";
        return ".csv";
    }

    private List<File> getJsonFiles(String tripBasePath) {
        return FileListLoader.getJsonFile(tripBasePath);
    }

    public ArrayList<TripBriefInterface> getTripBriefs() {
        if (fileFormat == ".json") {
            List<TripBriefJson> tripBriefs = getTripBriefsAsJson();
            return new ArrayList<>(tripBriefs); // 리스트 변환
        } else {
            List<TripBriefCsv> tripBriefs = getTripBriefAsCsv();
            return new ArrayList<>(tripBriefs); // 리스트 변환
        }
    }

    private List<TripBriefJson> getTripBriefsAsJson() {
        // 여행 파일들로부터 선별적으로 읽고(바깥에서 반복호출 될 것)
        // 읽은 것을 객체로 묶어서 전달
        TripBriefJson tripJsonObject;
        ObjectMapper objectMapper = new ObjectMapper();

        List<File> jsonFiles = getJsonFiles(tripBasePath);
        List<TripBriefJson> tripList = new ArrayList<>();

        List<TripBriefJson> tripBriefs = null;
        for (File jsonFile : jsonFiles) {
            try {
                tripJsonObject = objectMapper.readValue(jsonFile, TripBriefJson.class);
                tripList.add(tripJsonObject);
            } catch (IOException e) {
                e.printStackTrace();
            }
            tripBriefs = tripList.stream().collect(TripBriefJson.toList());
        }
        return tripBriefs;
    }
    private List<TripBriefCsv> getTripBriefAsCsv() {
        List<TripBriefCsv> tripBriefs = null;
        return tripBriefs;
    }
}
