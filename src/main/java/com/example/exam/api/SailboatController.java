package com.example.exam.api;

import com.example.exam.entity.Sailboat;
import com.example.exam.service.SailboatService;
import com.example.exam.utility.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sailboats")
@CrossOrigin("*")
public class SailboatController {

    private final SailboatService sailboatService;

    public SailboatController(SailboatService sailboatService) {
        this.sailboatService = sailboatService;
    }

    // Custom
    @GetMapping("/count")
    public long getSailboatCount() {
        return sailboatService.getTotalSailboatsCount();
    }

    // Default CRUD
    @GetMapping
    public ResponseEntity<List<Sailboat>> getAllSailboats() {
        return ResponseEntity.ok(sailboatService.getAllSailboats());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sailboat> getSailboat(@PathVariable Long id) {
        return ResponseEntity.of(sailboatService.getSailboat(id));
    }

    @PostMapping
    public ResponseEntity<Sailboat> createSailboat(@RequestBody Sailboat sailboat) {
        return ResponseEntity.ok(sailboatService.createSailboat(sailboat));
    }

    @PutMapping("/{id}") // todo add validation for invalid
    public ResponseEntity<Sailboat> updateSailboat(@PathVariable Long id, @RequestBody Sailboat sailboatDetails) {
        Sailboat updatedSailboat = sailboatService.updateSailboat(id, sailboatDetails);
        return new ResponseEntity<>(updatedSailboat, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSailboat(@PathVariable Long id) {
        try {
            sailboatService.deleteSailboat(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
