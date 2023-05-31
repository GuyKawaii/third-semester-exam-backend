package com.example.tourdefrancebackend.entity;

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
@Entity
public class Cykelhold {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String navn;

    @OneToMany(mappedBy = "cykelhold")
    private List<Cykelrytter> cykelryttere;

    // NoArgsConstructor - needed to initialize the list for extra methods
    public Cykelhold() {
        this.cykelryttere = new ArrayList<>();
    }

    // extra methods
    public void addRytter(Cykelrytter rytter) {
        this.cykelryttere.add(rytter);
        rytter.setCykelhold(this);
    }
}


