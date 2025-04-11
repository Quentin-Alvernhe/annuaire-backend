package com.entreprise.annuaire.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "departement")
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom du service est obligatoire")
    @Column(unique = true)
    private String nom;

    public Departement() {
    }

    public Departement(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Le nom du service est obligatoire") String getNom() {
        return nom;
    }

    public void setNom(@NotBlank(message = "Le nom du service est obligatoire") String nom) {
        this.nom = nom;
    }
}