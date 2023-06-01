package com.example.exam.dto;

import com.example.exam.entity.Sailboat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RaceResultDTO {
    private Long id;
    private Integer points;
    private SailboatDTO sailboat;
}
