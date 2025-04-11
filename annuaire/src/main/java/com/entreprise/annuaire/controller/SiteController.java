package com.entreprise.annuaire.controller;

import com.entreprise.annuaire.dto.SiteDTO;
import com.entreprise.annuaire.service.SiteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sites")
public class SiteController {

    @Autowired
    private SiteService siteService;

    @GetMapping
    public ResponseEntity<List<SiteDTO>> getAllSites() {
        return ResponseEntity.ok(siteService.getAllSites());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SiteDTO> getSiteById(@PathVariable Long id) {
        return ResponseEntity.ok(siteService.getSiteById(id));
    }

    @PostMapping
    public ResponseEntity<SiteDTO> createSite(@Valid @RequestBody SiteDTO siteDTO) {
        return new ResponseEntity<>(siteService.createSite(siteDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SiteDTO> updateSite(@PathVariable Long id, @Valid @RequestBody SiteDTO siteDTO) {
        return ResponseEntity.ok(siteService.updateSite(id, siteDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSite(@PathVariable Long id) {
        siteService.deleteSite(id);
        return ResponseEntity.noContent().build();
    }
}