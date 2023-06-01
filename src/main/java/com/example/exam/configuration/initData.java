package com.example.exam.configuration;

import com.example.exam.entity.*;
import com.example.exam.enums.BoatType;
import com.example.exam.repository.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@Component
public class initData implements CommandLineRunner {

    @Value("${STATE}")
    private String state;

    private final SailboatRepository sailboatRepository;
    private final RaceRepository raceRepository;
    private final RaceResultRepository raceResultRepository;


    public initData(SailboatRepository sailboatRepository, RaceRepository raceRepository, RaceResultRepository raceResultRepository) {
        this.sailboatRepository = sailboatRepository;
        this.raceRepository = raceRepository;
        this.raceResultRepository = raceResultRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        // ### use application.properties to run ###
        if ("create-drop".equals(state)) System.out.printf("STATE:%s - RUNNING initData.run \n", state);
        else {
            System.out.printf("STATE:%s - NO initData.run\n", state);
            return;
        }

        // data to be initialized
        createPlaceholder();
//        createSeason();
    }

    void createPlaceholder() {
        Sailboat sailboat1 = new Sailboat(1L, "Sailboat 1", BoatType.BETWEEN_25_AND_40_FEET, null);
        Sailboat sailboat2 = new Sailboat(2L, "Sailboat 2", BoatType.LESS_THAN_25_FEET, null);
        Sailboat sailboat3 = new Sailboat(3L, "Sailboat 3", BoatType.LONGER_THAN_40_FEET, null);

        Race race1 = new Race(1L, LocalDate.now(), BoatType.BETWEEN_25_AND_40_FEET, null);
        Race race2 = new Race(2L, LocalDate.now().plusDays(7), BoatType.LESS_THAN_25_FEET, null);
        Race race3 = new Race(3L, LocalDate.now().plusDays(14), BoatType.LONGER_THAN_40_FEET, null);

        RaceResult raceResult1 = new RaceResult(1L, sailboat1, race1, 1);
        RaceResult raceResult2 = new RaceResult(2L, sailboat2, race2, 2);
        RaceResult raceResult3 = new RaceResult(3L, sailboat3, race3, 3);

        sailboatRepository.saveAll(List.of(sailboat1, sailboat2, sailboat3));
        raceRepository.saveAll(List.of(race1, race2, race3));
        raceResultRepository.saveAll(List.of(raceResult1, raceResult2, raceResult3));

        List<Race> races = raceRepository.findAll();
        System.out.println(races);
    }

    // Opgave 3 - A: Create a season
    void createSeason() {
        // inclusive range for both start and end date todo fix later
        LocalDate startDate = LocalDate.of(LocalDate.now().getYear(), Month.MAY, 1);
        LocalDate endDate = LocalDate.of(LocalDate.now().getYear(), Month.OCTOBER, 1);

        List<Race> seasonRaces = new ArrayList<>();

        // within range find first Wednesday
        LocalDate current = startDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.WEDNESDAY));

        while (!current.isAfter(endDate)) {
            // Create a race for each boat type
            for (BoatType boatType : BoatType.values()) {
                seasonRaces.add(new Race(null, current, boatType, null));
            }

            // Move to the next Wednesday
            current = current.plusWeeks(1);
        }

        // Save all races to the database
        raceRepository.saveAll(seasonRaces);
    }

}
