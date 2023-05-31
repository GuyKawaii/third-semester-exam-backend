package com.example.tourdefrancebackend.api;

import com.example.tourdefrancebackend.dto.CykelholdDTO;
import com.example.tourdefrancebackend.dto.CykelrytterDTO;
import com.example.tourdefrancebackend.entity.Cykelhold;
import com.example.tourdefrancebackend.entity.Cykelrytter;
import com.example.tourdefrancebackend.service.CykelrytterService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cykelryttere")
@CrossOrigin("*")
public class CykelrytterController {

    private final CykelrytterService cykelrytterService;

    public CykelrytterController(CykelrytterService cykelrytterService) {
        this.cykelrytterService = cykelrytterService;
    }


    @GetMapping
    public ResponseEntity<List<CykelrytterDTO>> getAllCykelryttere() {
        List<Cykelrytter> cykelryttere = cykelrytterService.getAllCykelryttere();
        List<CykelrytterDTO> cykelrytterDTOs = new ArrayList<>();
        for (Cykelrytter cykelrytter : cykelryttere) {
            CykelrytterDTO cykelrytterDTO = cykelrytterToDTO(cykelrytter);
            cykelrytterDTOs.add(cykelrytterDTO);
        }
        return new ResponseEntity<>(cykelrytterDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CykelrytterDTO> getCykelrytterById(@PathVariable Long id) {
        Cykelrytter cykelrytter = cykelrytterService.getCykelrytterById(id);
        CykelrytterDTO cykelrytterDTO = cykelrytterToDTO(cykelrytter);
        return new ResponseEntity<>(cykelrytterDTO, HttpStatus.OK);
    }

    // todo move to util class
    private CykelrytterDTO cykelrytterToDTO(Cykelrytter cykelrytter) {
        CykelrytterDTO cykelrytterDTO = new CykelrytterDTO();
        BeanUtils.copyProperties(cykelrytter, cykelrytterDTO);
        cykelrytterDTO.setCykelhold(convertToCykelholdDTO(cykelrytter.getCykelhold()));
        return cykelrytterDTO;
    }

    private CykelholdDTO convertToCykelholdDTO(Cykelhold cykelhold) {
        return new CykelholdDTO(cykelhold.getId(), cykelhold.getNavn());
    }



    @GetMapping("/hold/{holdId}")
    public ResponseEntity<List<Cykelrytter>> getCykelryttereByHoldId(@PathVariable Long holdId) {
        List<Cykelrytter> cykelryttere = cykelrytterService.getCykelryttereByHoldId(holdId);
        return new ResponseEntity<>(cykelryttere, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cykelrytter> createCykelrytter(@RequestBody Cykelrytter Cykelrytter) {
        Cykelrytter createdCykelrytter = cykelrytterService.createCykelrytter(Cykelrytter);
        return new ResponseEntity<>(createdCykelrytter, HttpStatus.CREATED);
    }

    @PutMapping("/{id}") // todo check simplet way to update
    public ResponseEntity<Cykelrytter> updateCykelrytter(@PathVariable Long id, @RequestBody Cykelrytter Cykelrytter) {
        Cykelrytter updatedCykelrytter = cykelrytterService.updateCykelrytter(id, Cykelrytter);
        return new ResponseEntity<>(updatedCykelrytter, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCykelrytter(@PathVariable Long id) {
        cykelrytterService.deleteCykelrytter(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
