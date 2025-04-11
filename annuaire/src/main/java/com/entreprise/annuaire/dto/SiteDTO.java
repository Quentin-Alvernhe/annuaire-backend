package com.entreprise.annuaire.dto;

import jakarta.validation.constraints.NotBlank;

public class SiteDTO {
    private Long id;

    @NotBlank(message = "La ville est obligatoire")
    private String ville;

    // Constructeur par défaut
    public SiteDTO() {
    }

    // Constructeur avec tous les champs
    public SiteDTO(Long id, String ville) {
        this.id = id;
        this.ville = ville;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}