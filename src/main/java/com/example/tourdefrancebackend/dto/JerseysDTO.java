package com.example.tourdefrancebackend.dto;

import com.example.tourdefrancebackend.enums.Jersey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JerseysDTO {
    private Long id;
    private String navn;
    private int alder;
    private int samletTid;
    private int bjergpoint;
    private int spurtpoint;
    private CykelholdDTO Cykelhold;
    private String jersey;
}
