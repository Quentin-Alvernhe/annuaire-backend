package com.entreprise.annuaire.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "site")
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La ville est obligatoire")
    @Column(unique = true)
    private String ville;

    public Site() {
    }

    public Site(Long id, String ville) {
        this.id = id;
        this.ville = ville;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "La ville est obligatoire") String getVille() {
        return ville;
    }

    public void setVille(@NotBlank(message = "La ville est obligatoire") String ville) {
        this.ville = ville;
    }
}

