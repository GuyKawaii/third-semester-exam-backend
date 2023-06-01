package com.example.exam.api;

import com.example.exam.entity.RaceResult;
import com.example.exam.service.RaceResultService;
import com.example.exam.utility.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/race-results")
@CrossOrigin("*")
public class RaceResultController {

    private final RaceResultService raceResultService;

    public RaceResultController(RaceResultService raceResultRepository) {
        this.raceResultService = raceResultRepository;
    }

    // Custom
    @GetMapping("count")
    public Long countRaceResults() {
        return raceResultService.countRaceResults();
    }

    // Default CRUD
    @GetMapping("/sailboat/{id}")
    public List<RaceResult> getRaceResultsBySailboatId(@PathVariable Long id) {
        return raceResultService.getRaceResultsBySailboatId(id);
    }

    @GetMapping("/race/{id}")
    public List<RaceResult> getRaceResultsByRaceId(@PathVariable Long id) {
        return raceResultService.getRaceResultsByRaceId(id);
    }

    // Default CRUD
    @GetMapping
    public ResponseEntity<List<RaceResult>> getAllRaces() {
        return ResponseEntity.ok(raceResultService.getAllRaceResults());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RaceResult> getRace(@PathVariable Long id) {
        return ResponseEntity.of(raceResultService.getRaceResult(id));
    }

    @PostMapping
    public ResponseEntity<RaceResult> createRace(@RequestBody RaceResult raceResult) {
        return ResponseEntity.ok(raceResultService.createRaceResult(raceResult));
    }

    @PutMapping("/{id}") // todo add validation for invalid
    public ResponseEntity<RaceResult> updateRace(@PathVariable Long id, @RequestBody RaceResult raceResultDetails) {
        RaceResult updatedRace = raceResultService.updateRaceResult(id, raceResultDetails);
        return new ResponseEntity<>(updatedRace, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRace(@PathVariable Long id) {
        try {
            raceResultService.deleteRaceResult(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
