package com.example.exam.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cykelrytter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String navn;
    private int alder;
    private int samletTid;
    private int bjergpoint;
    private int spurtpoint;

    @ManyToOne
    @JsonBackReference
    private Cykelhold cykelhold;
}

