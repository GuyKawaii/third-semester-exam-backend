package com.example.exam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CykelrytterDTO {
    private Long id;
    private String navn;
    private int alder;
    private int samletTid;
    private int bjergpoint;
    private int spurtpoint;
    private CykelholdDTO Cykelhold;
}
