package com.example.tourdefrancebackend.dto;

import com.example.tourdefrancebackend.entity.Cykelrytter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CykelholdDTO {
    private Long id;
    private String navn;
}



