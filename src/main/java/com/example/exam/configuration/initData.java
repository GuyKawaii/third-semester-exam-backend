package com.example.exam.configuration;

import com.example.exam.entity.Cykelhold;
import com.example.exam.entity.Cykelrytter;
import com.example.exam.entity.Sailboat;
import com.example.exam.enums.BoatType;
import com.example.exam.repository.CykelholdRepository;
import com.example.exam.repository.CykelrytterRepository;
import com.example.exam.repository.SailboatRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class initData implements CommandLineRunner {

    @Value("${STATE}")
    private String state;

    private final CykelholdRepository cykelholdRepository;
    private final CykelrytterRepository cykelrytterRepository;
    private final SailboatRepository sailboatRepository;


    public initData(CykelholdRepository cykelholdRepository, CykelrytterRepository cykelrytterRepository, SailboatRepository sailboatRepository) {
        this.cykelholdRepository = cykelholdRepository;
        this.cykelrytterRepository = cykelrytterRepository;
        this.sailboatRepository = sailboatRepository;
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
//        createHold();
        createSailboats();
    }

    void createSailboats() {
        List<Sailboat> sailboats = new ArrayList<>();
        sailboats.add(new Sailboat(1L, "Sailboat middle", BoatType.BETWEEN_25_AND_40_FEET));
        sailboats.add(new Sailboat(2L, "Sailboat short", BoatType.LESS_THAN_25_FEET));
        sailboats.add(new Sailboat(3L, "Sailboat long", BoatType.LONGER_THAN_40_FEET));

        sailboatRepository.saveAll(sailboats);
    }

    void createHold() {
        Cykelhold hold1 = new Cykelhold();
        hold1.setNavn("Team Jumbo-Visma");

        // ryttere
        hold1.addRytter(new Cykelrytter(1L, "Primoz Roglic", 22, 100, 90, 90, hold1));
        hold1.addRytter(new Cykelrytter(2L, "Wout van Aert", 23, 110, 95, 85, hold1));
        hold1.addRytter(new Cykelrytter(3L, "Steven Kruijswijk", 24, 120, 100, 80, hold1));
        hold1.addRytter(new Cykelrytter(4L, "Sepp Kuss", 25, 130, 105, 75, hold1));

        cykelholdRepository.save(hold1);
        cykelrytterRepository.saveAll(hold1.getCykelryttere()); // could not save from hold1
    }



}
