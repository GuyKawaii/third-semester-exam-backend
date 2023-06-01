package com.example.exam.dto;

import com.example.exam.enums.BoatType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SailboatCumulativeResultDTO {
    private Long id;
    private String name;
    private BoatType boatType;
    private Integer totalRaces;
    private Integer totalPoints;
}

