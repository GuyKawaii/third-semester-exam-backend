package com.example.exam.service;

import com.example.exam.entity.RaceResult;
import com.example.exam.repository.RaceResultRepository;
import com.example.exam.utility.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RaceResultService {
    private final RaceResultRepository raceResultRepository;

    public RaceResultService(RaceResultRepository raceResultRepository) {
        this.raceResultRepository = raceResultRepository;
    }

    public List<RaceResult> getAllRaceResults() {
        return raceResultRepository.findAll();
    }

    public Optional<RaceResult> getRaceResult(Long id) {
        return raceResultRepository.findById(id);
    }

    public RaceResult createRaceResult(RaceResult RaceResult) {
        return raceResultRepository.save(RaceResult);
    }

    public RaceResult updateRaceResult(Long id, RaceResult RaceResultDetails) {
        Optional<RaceResult> RaceResultOptional = raceResultRepository.findById(id);

        if (RaceResultOptional.isPresent()) {
            RaceResult updatedRaceResult = RaceResultOptional.get();
            BeanUtils.copyProperties(RaceResultDetails, updatedRaceResult);
            updatedRaceResult.setId(id);

            System.out.println(updatedRaceResult);
            return raceResultRepository.save(updatedRaceResult);
        } else {
            throw new ResourceNotFoundException("RaceResult not found with id " + id);
        }
    }

    public void deleteRaceResult(Long id) {
        if (raceResultRepository.existsById(id)) {
            raceResultRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("RaceResult not found with id " + id);
        }
    }

    public List<RaceResult> getRaceResultsBySailboatId(Long id) {
        return raceResultRepository.findBySailboatIdOrderByPointsAsc(id);
    }

    public List<RaceResult> getRaceResultsByRaceId(Long id) {
        return raceResultRepository.findByRaceIdOrderByPointsAsc(id);
    }

    public Long countRaceResults() {
        return raceResultRepository.count();
    }
}
