package com.example.exam.service;

import com.example.exam.entity.Cykelhold;
import com.example.exam.repository.CykelholdRepository;
import org.springframework.stereotype.Service;

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
