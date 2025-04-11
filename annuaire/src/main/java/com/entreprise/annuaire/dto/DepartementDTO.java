package com.entreprise.annuaire.dto;

import jakarta.validation.constraints.NotBlank;

public class DepartementDTO {
    private Long id;

    @NotBlank(message = "Le nom du département est obligatoire")
    private String nom;

    // Constructeur par défaut
    public DepartementDTO() {
    }

    // Constructeur avec tous les champs
    public DepartementDTO(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}