package com.entreprise.annuaire.controller;

import com.entreprise.annuaire.dto.DepartementDTO;
import com.entreprise.annuaire.service.DepartementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departement")
public class DepartementController {

    @Autowired
    private DepartementService departementService;

    @GetMapping
    public ResponseEntity<List<DepartementDTO>> getAllDepartements() {
        return ResponseEntity.ok(departementService.getAllDepartements());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartementDTO> getDepartementById(@PathVariable Long id) {
        return ResponseEntity.ok(departementService.getDepartementById(id));
    }

    @PostMapping
    public ResponseEntity<DepartementDTO> createDepartement(@Valid @RequestBody DepartementDTO departementDTO) {
        return new ResponseEntity<>(departementService.createDepartement(departementDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartementDTO> updateDepartement(@PathVariable Long id, @Valid @RequestBody DepartementDTO departementDTO) {
        return ResponseEntity.ok(departementService.updateDepartement(id, departementDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartement(@PathVariable Long id) {
        departementService.deleteDepartement(id);
        return ResponseEntity.noContent().build();
    }
}