package com.example.tourdefrancebackend.service;

import com.example.tourdefrancebackend.dto.JerseysDTO;
import com.example.tourdefrancebackend.entity.Cykelrytter;
import com.example.tourdefrancebackend.repository.CykelrytterRepository;
import com.example.tourdefrancebackend.utility.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import static com.example.tourdefrancebackend.enums.Jersey.*;

@Service
public class CykelrytterService {

    private final CykelrytterRepository cykelrytterRepository;

    @Autowired
    public CykelrytterService(CykelrytterRepository cykelrytterRepository) {
        this.cykelrytterRepository = cykelrytterRepository;
    }

    public List<Cykelrytter> getAllCykelryttere() {
        return cykelrytterRepository.findAll();
    }

    public Cykelrytter getCykelrytterById(Long id) {
        Optional<Cykelrytter> cykelrytter = cykelrytterRepository.findById(id);
        if (!cykelrytter.isPresent()){
            throw new RuntimeException("Cykelrytter not found with id " + id);
        }
        return cykelrytter.get();
    }

    public Cykelrytter createCykelrytter(Cykelrytter cykelrytter) {
        return cykelrytterRepository.save(cykelrytter);
    }

    public Cykelrytter updateCykelrytter(Long id, Cykelrytter cykelrytterDetails) {
        Cykelrytter existingCykelrytter = getCykelrytterById(id);
        existingCykelrytter.setNavn(cykelrytterDetails.getNavn());
        existingCykelrytter.setAlder(cykelrytterDetails.getAlder());
        existingCykelrytter.setSamletTid(cykelrytterDetails.getSamletTid());
        existingCykelrytter.setBjergpoint(cykelrytterDetails.getBjergpoint());
        existingCykelrytter.setSpurtpoint(cykelrytterDetails.getSpurtpoint());
        existingCykelrytter.setCykelhold(cykelrytterDetails.getCykelhold());
        return cykelrytterRepository.save(existingCykelrytter);
    }

    public void deleteCykelrytter(Long id) {
        Cykelrytter existingCykelrytter = getCykelrytterById(id);
        cykelrytterRepository.delete(existingCykelrytter);
    }

    public List<Cykelrytter> getCykelryttereByHoldId(Long holdId) {
        // Find the Cykelryttere by holdId
        // Please implement this method based on your needs
        return cykelrytterRepository.findByCykelholdId(holdId);
    }

    public List<JerseysDTO> getTrøjer() {
        return calculateJerseys();
    }

    private List<JerseysDTO> calculateJerseys() {
        Cykelrytter yellowJerseyRider = cykelrytterRepository.findFirstByOrderBySamletTidAsc();
        Cykelrytter mountainJerseyRider = cykelrytterRepository.findFirstByOrderByBjergpointDesc();
        Cykelrytter greenJerseyRider = cykelrytterRepository.findFirstByOrderBySpurtpointDesc();
        Cykelrytter whiteJerseyRider = cykelrytterRepository.findFirstBySamletTidOrderByAlderAsc(26);

        List<JerseysDTO> jerseys = new ArrayList<>();

        if (yellowJerseyRider != null) jerseys.add(DTOConverter.convertToJerseysDTO(yellowJerseyRider, "YELLOW"));
        if (mountainJerseyRider != null) jerseys.add(DTOConverter.convertToJerseysDTO(mountainJerseyRider, "BJERG"));
        if (greenJerseyRider != null) jerseys.add(DTOConverter.convertToJerseysDTO(greenJerseyRider, "GREEN"));
        if (whiteJerseyRider != null) jerseys.add(DTOConverter.convertToJerseysDTO(whiteJerseyRider, "HVID"));

        return jerseys;
    }


}


