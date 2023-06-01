package com.example.exam.dto;

import com.example.exam.enums.BoatType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RaceDTO {
    private Long id;
    private LocalDate date;
    private BoatType boatType;
    private List<RaceResultDTO> raceResults;
}
