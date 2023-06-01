package com.example.exam.service;

import com.example.exam.entity.Race;
import com.example.exam.repository.RaceRepository;
import com.example.exam.utility.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RaceService {

    private final RaceRepository RaceRepository;

    public RaceService(RaceRepository RaceRepository) {
        this.RaceRepository = RaceRepository;
    }

    public List<Race> getAllRaces() {
        return RaceRepository.findAll();
    }

    public Optional<Race> getRace(Long id) {
        return RaceRepository.findById(id);
    }

    public Race createRace(Race Race) {
        return RaceRepository.save(Race);
    }

    public Race updateRace(Long id, Race RaceDetails) {
        Optional<Race> RaceOptional = RaceRepository.findById(id);

        if (RaceOptional.isPresent()) {
            Race updatedRace = RaceOptional.get();
            BeanUtils.copyProperties(RaceDetails, updatedRace);
            updatedRace.setId(id);

            System.out.println(updatedRace);
            return RaceRepository.save(updatedRace);
        } else {
            throw new ResourceNotFoundException("Race not found with id " + id);
        }
    }

    public void deleteRace(Long id) {
        if (RaceRepository.existsById(id)) {
            RaceRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Race not found with id " + id);
        }
    }
}
