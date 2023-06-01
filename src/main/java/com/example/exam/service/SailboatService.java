package com.example.exam.service;

import com.example.exam.entity.Sailboat;
import com.example.exam.repository.SailboatRepository;
import com.example.exam.utility.ResourceNotFoundException;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.sun.tools.jconsole.JConsoleContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SailboatService {

    private final SailboatRepository sailboatRepository;

    public SailboatService(SailboatRepository sailboatRepository) {
        this.sailboatRepository = sailboatRepository;
    }

    public List<Sailboat> getAllSailboats() {
        return sailboatRepository.findAll();
    }

    public Optional<Sailboat> getSailboat(Long id) {
        return sailboatRepository.findById(id);
    }

    public Sailboat createSailboat(Sailboat sailboat) {
        return sailboatRepository.save(sailboat);
    }

    public Sailboat updateSailboat(Long id, Sailboat sailboatDetails) {
        Optional<Sailboat> sailboatOptional = sailboatRepository.findById(id);

        if (sailboatOptional.isPresent()) {
            Sailboat updatedSailboat = sailboatOptional.get();
            BeanUtils.copyProperties(sailboatDetails, updatedSailboat);
            updatedSailboat.setId(id);

            System.out.println(updatedSailboat);
            return sailboatRepository.save(updatedSailboat);
        } else {
            throw new ResourceNotFoundException("Sailboat not found with id " + id);
        }
    }

    public void deleteSailboat(Long id) {
        if (sailboatRepository.existsById(id)) {
            sailboatRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Sailboat not found with id " + id);
        }
    }

    public long getTotalSailboatsCount() {
        return sailboatRepository.count();
    }
}
