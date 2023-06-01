package com.example.exam.dto;

import com.example.exam.enums.BoatType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SailboatDTO {
    private Long id;
    private String name;
    private BoatType boatType;
}

