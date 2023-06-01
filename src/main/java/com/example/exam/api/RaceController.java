package com.example.exam.api;

import java.util.List;

import com.example.exam.entity.Race;
import com.example.exam.service.RaceService;
import com.example.exam.utility.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/races")
@CrossOrigin("*")
public class RaceController {

    private final RaceService RaceService;

    public RaceController(com.example.exam.service.RaceService raceService) {
        RaceService = raceService;
    }


    @GetMapping
    public ResponseEntity<List<Race>> getAllRaces() {
        return ResponseEntity.ok(RaceService.getAllRaces());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Race> getRace(@PathVariable Long id) {
        return ResponseEntity.of(RaceService.getRace(id));
    }

    @PostMapping
    public ResponseEntity<Race> createRace(@RequestBody Race Race) {
        return ResponseEntity.ok(RaceService.createRace(Race));
    }

    @PutMapping("/{id}") // todo add validation for invalid
    public ResponseEntity<Race> updateRace(@PathVariable Long id, @RequestBody Race RaceDetails) {
        Race updatedRace = RaceService.updateRace(id, RaceDetails);
        return new ResponseEntity<>(updatedRace, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRace(@PathVariable Long id) {
        try {
            RaceService.deleteRace(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
