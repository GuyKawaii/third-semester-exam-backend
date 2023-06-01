package com.example.exam.api;

import java.util.List;

import com.example.exam.dto.RaceDTO;
import com.example.exam.dto.SailboatCumulativeResultDTO;
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

    private final RaceService raceService;

    public RaceController(com.example.exam.service.RaceService raceService) {
        this.raceService = raceService;
    }

    // Custom
    @GetMapping("/races-dto")
    public ResponseEntity<List<RaceDTO>> getAllDTORaces() {
        List<RaceDTO> raceDTOs = raceService.getALLraceDTOs();
        return new ResponseEntity<>(raceDTOs, HttpStatus.OK);
    }

    @GetMapping("/sailboats-cumulative-results-dto")
    public ResponseEntity<List<SailboatCumulativeResultDTO>> getAllSailboatCumulativeResultsDTO() {
        List<SailboatCumulativeResultDTO> resultDTOs = raceService.getAllSailboatCumulativeResultsDTO();
        return new ResponseEntity<>(resultDTOs, HttpStatus.OK);
    }

    // Default CRUD
    @GetMapping
    public ResponseEntity<List<Race>> getAllRaces() {
        return ResponseEntity.ok(raceService.getAllRaces());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Race> getRace(@PathVariable Long id) {
        return ResponseEntity.of(raceService.getRace(id));
    }

    @PostMapping
    public ResponseEntity<Race> createRace(@RequestBody Race Race) {
        return ResponseEntity.ok(raceService.createRace(Race));
    }

    @PutMapping("/{id}") // todo add validation for invalid
    public ResponseEntity<Race> updateRace(@PathVariable Long id, @RequestBody Race RaceDetails) {
        Race updatedRace = raceService.updateRace(id, RaceDetails);
        return new ResponseEntity<>(updatedRace, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRace(@PathVariable Long id) {
        try {
            raceService.deleteRace(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
