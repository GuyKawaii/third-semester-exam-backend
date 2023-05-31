package com.example.tourdefrancebackend.configuration;

import com.example.tourdefrancebackend.entity.Cykelhold;
import com.example.tourdefrancebackend.entity.Cykelrytter;
import com.example.tourdefrancebackend.repository.CykelholdRepository;
import com.example.tourdefrancebackend.repository.CykelrytterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class initData implements CommandLineRunner {

    private final CykelholdRepository cykelholdRepository;
    private final CykelrytterRepository cykelrytterRepository;

    public initData(CykelholdRepository cykelholdRepository, CykelrytterRepository cykelrytterRepository) {
        this.cykelholdRepository = cykelholdRepository;
        this.cykelrytterRepository = cykelrytterRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        // ### if true dont run ###
        if (false) {
            System.out.println("NO initData.run");
            return;

        } else System.out.println("RUNNING initData.run");

        // data to be initialized
        createHold();
    }

    void createHold() {
        Cykelhold hold1 = new Cykelhold();
        hold1.setNavn("Team Jumbo-Visma");

        // ryttere
        hold1.addRytter(new Cykelrytter(1L, "Primoz Roglic", 31, 100, 90, 90, hold1));
        hold1.addRytter(new Cykelrytter(2L, "Wout van Aert", 31, 110, 95, 85, hold1));
        hold1.addRytter(new Cykelrytter(3L, "Steven Kruijswijk", 34, 120, 100, 80, hold1));
        hold1.addRytter(new Cykelrytter(4L, "Sepp Kuss", 27, 130, 105, 75, hold1));

        cykelholdRepository.save(hold1);
        cykelrytterRepository.saveAll(hold1.getCykelryttere()); // could not save from hold1
    }



}
