package com.example.tourdefrancebackend.service;

import com.example.tourdefrancebackend.entity.Cykelhold;
import com.example.tourdefrancebackend.entity.Cykelhold;
import com.example.tourdefrancebackend.repository.CykelholdRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CykelholdService {

    private static CykelholdRepository cykelholdRepository;

    public CykelholdService(CykelholdRepository cykelholdRepository) {
        this.cykelholdRepository = cykelholdRepository;
    }

    public Cykelhold getCykelholdById(Long id) {
        Optional<Cykelhold> cykelhold = cykelholdRepository.findById(id);
        if (!cykelhold.isPresent()){
            throw new RuntimeException("Cykelhold not found with id " + id);
        }
        return cykelhold.get();
    }
}
