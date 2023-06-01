package com.example.exam.utility;

import com.example.exam.dto.RaceDTO;
import com.example.exam.dto.RaceResultDTO;
import com.example.exam.dto.SailboatCumulativeResultDTO;
import com.example.exam.dto.SailboatDTO;
import com.example.exam.entity.Race;
import com.example.exam.entity.RaceResult;
import com.example.exam.entity.Sailboat;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DTOConverter {

    // sailboatToDTO specific
    public SailboatCumulativeResultDTO convertToSailboatCumulativeResultDTO(Sailboat sailboat) {
        SailboatCumulativeResultDTO sailboatDTO = new SailboatCumulativeResultDTO();
        sailboatDTO.setId(sailboat.getId());
        sailboatDTO.setName(sailboat.getName());
        sailboatDTO.setBoatType(sailboat.getBoatType());
        sailboatDTO.setTotalRaces(sailboat.getRaceResults().size());
        sailboatDTO.setTotalPoints(sailboat.getRaceResults().stream()
                .mapToInt(RaceResult::getPoints)
                .sum());
        return sailboatDTO;
    }

    // RaceToDTO specific
    public RaceDTO convertToRaceDTO(Race race) {
        RaceDTO raceDTO = new RaceDTO();
        BeanUtils.copyProperties(race, raceDTO);

        List<RaceResultDTO> raceResultDTOList = race.getRaceResults().stream()
                .map(this::convertToRaceResultDTO)
                .collect(Collectors.toList());

        raceDTO.setRaceResults(raceResultDTOList);

        return raceDTO;
    }

    public RaceResultDTO convertToRaceResultDTO(RaceResult raceResult) {
        RaceResultDTO raceResultDTO = new RaceResultDTO();
        BeanUtils.copyProperties(raceResult, raceResultDTO);

        SailboatDTO sailboatDTO = convertToSailboatDTO(raceResult.getSailboat());
        raceResultDTO.setSailboat(sailboatDTO);

        return raceResultDTO;
    }

    public SailboatDTO convertToSailboatDTO(Sailboat sailboat) {
        SailboatDTO sailboatDTO = new SailboatDTO();
        BeanUtils.copyProperties(sailboat, sailboatDTO);

        return sailboatDTO;
    }
}

