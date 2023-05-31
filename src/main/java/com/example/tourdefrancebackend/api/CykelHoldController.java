package com.example.tourdefrancebackend.api;

import com.example.tourdefrancebackend.entity.Cykelhold;
import com.example.tourdefrancebackend.service.CykelholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cykelhold")
@CrossOrigin("*")
public class CykelHoldController {

    private final CykelholdService cykelholdService;

    public CykelHoldController(CykelholdService cykelholdService) {
        this.cykelholdService = cykelholdService;
    }

    // mapping to get team
    @GetMapping("/{id}")
    public ResponseEntity<Cykelhold> getCykelhold(@PathVariable Long id) {
        Cykelhold cykelhold = cykelholdService.getCykelholdById(id);
        return new ResponseEntity<>(cykelhold, HttpStatus.OK);
    }
}
