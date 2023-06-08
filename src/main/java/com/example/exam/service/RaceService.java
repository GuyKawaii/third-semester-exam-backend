package com.example.exam.service;

import com.example.exam.dto.RaceDTO;
import com.example.exam.dto.SailboatCumulativeResultDTO;
import com.example.exam.entity.Race;
import com.example.exam.entity.RaceResult;
import com.example.exam.entity.Sailboat;
import com.example.exam.repository.RaceRepository;
import com.example.exam.repository.RaceResultRepository;
import com.example.exam.repository.SailboatRepository;
import com.example.exam.utility.DTOConverter;
import com.example.exam.utility.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RaceService {

    private final RaceRepository raceRepository;
    private final RaceResultRepository raceResultRepository;
    private final SailboatRepository sailboatRepository;
    private final DTOConverter dtoConverter;

    public RaceService(RaceRepository RaceRepository, RaceResultRepository raceResultRepository, DTOConverter dtoConverter, SailboatRepository sailboatRepository, DTOConverter dtoConverter1) {
        this.raceRepository = RaceRepository;
        this.raceResultRepository = raceResultRepository;
        this.sailboatRepository = sailboatRepository;
        this.dtoConverter = dtoConverter1;
    }

    // map .map(elm -> elm.getSomething())
    // filter .filter(elm -> elm.getSomething() > 10)
    // reduce .reduce(0, (acc, elm) -> acc + elm.getSomething())
    // ### Custom ###
    public List<SailboatCumulativeResultDTO> getAllSailboatCumulativeResultsDTO() {
        List<Sailboat> sailboats = sailboatRepository.findAll();
        return sailboats.stream()
                .map(dtoConverter::convertToSailboatCumulativeResultDTO)
                .collect(Collectors.toList());
    }

    public List<RaceDTO> getALLraceDTOs() {
        List<Race> races = raceRepository.findAll();
        List<RaceResult> raceResults = raceResultRepository.findAll();

        // Map RaceResult to their Race ID
        Map<Long, List<RaceResult>> raceResultsByRace = raceResults.stream()
                .collect(Collectors.groupingBy(raceResult -> raceResult.getRace().getId()));

        return races.stream()
                .peek(race -> race.setRaceResults(raceResultsByRace.getOrDefault(race.getId(), new ArrayList<>())))
                .map(dtoConverter::convertToRaceDTO) // Non-static reference
                .collect(Collectors.toList());
    }


    // ### Default CRUD ###
    public List<Race> getAllRaces() {
        return raceRepository.findAllByOrderByDateAsc();
    }

    public Optional<Race> getRace(Long id) {
        return raceRepository.findById(id);
    }

    public Race createRace(Race Race) {
        return raceRepository.save(Race);
    }

    public Race updateRace(Long id, Race RaceDetails) {
        Optional<Race> RaceOptional = raceRepository.findById(id);

        if (RaceOptional.isPresent()) {
            Race updatedRace = RaceOptional.get();
            BeanUtils.copyProperties(RaceDetails, updatedRace);
            updatedRace.setId(id);

            System.out.println(updatedRace);
            return raceRepository.save(updatedRace);
        } else {
            throw new ResourceNotFoundException("Race not found with id " + id);
        }
    }

    public void deleteRace(Long id) {
        if (raceRepository.existsById(id)) {
            raceRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Race not found with id " + id);
        }
    }

    public Long countRaces() {
        return raceRepository.count();
    }
}
