package com.entreprise.annuaire.controller;

import com.entreprise.annuaire.dto.SalarieDTO;
import com.entreprise.annuaire.service.SalarieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salaries")
public class SalarieController {

    @Autowired
    private SalarieService salarieService;

    @GetMapping
    public ResponseEntity<List<SalarieDTO>> getAllSalaries() {
        return ResponseEntity.ok(salarieService.getAllSalaries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalarieDTO> getSalarieById(@PathVariable Long id) {
        return ResponseEntity.ok(salarieService.getSalarieById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<SalarieDTO>> searchSalaries(@RequestParam String term) {
        return ResponseEntity.ok(salarieService.searchSalaries(term));
    }

    @GetMapping("/site/{siteId}")
    public ResponseEntity<List<SalarieDTO>> getSalariesBySiteId(@PathVariable Long siteId) {
        return ResponseEntity.ok(salarieService.getSalariesBySiteId(siteId));
    }

    @GetMapping("/service/{serviceId}")
    public ResponseEntity<List<SalarieDTO>> getSalariesByServiceId(@PathVariable Long serviceId) {
        return ResponseEntity.ok(salarieService.getSalariesByServiceId(serviceId));
    }

    @PostMapping
    public ResponseEntity<SalarieDTO> createSalarie(@Valid @RequestBody SalarieDTO salarieDTO) {
        return new ResponseEntity<>(salarieService.createSalarie(salarieDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalarieDTO> updateSalarie(@PathVariable Long id, @Valid @RequestBody SalarieDTO salarieDTO) {
        return ResponseEntity.ok(salarieService.updateSalarie(id, salarieDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalarie(@PathVariable Long id) {
        salarieService.deleteSalarie(id);
        return ResponseEntity.noContent().build();
    }
}