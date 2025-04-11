package com.entreprise.annuaire.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "salarie")
public class Salarie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;

    @Pattern(regexp = "^[0-9]{10}$", message = "Le téléphone fixe doit contenir 10 chiffres")
    private String telephoneFixe;

    @Pattern(regexp = "^[0-9]{10}$", message = "Le téléphone portable doit contenir 10 chiffres")
    private String telephonePortable;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "L'email doit être valide")
    @Column(unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Departement departement;

    @ManyToOne
    @JoinColumn(name = "site_id")
    private Site site;

    public Salarie() {
    }

    public Salarie(Long id, String nom, String prenom, String telephoneFixe, String telephonePortable, String email, Departement departement, Site site) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.telephoneFixe = telephoneFixe;
        this.telephonePortable = telephonePortable;
        this.email = email;
        this.departement = departement;
        this.site = site;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Le nom est obligatoire") String getNom() {
        return nom;
    }

    public void setNom(@NotBlank(message = "Le nom est obligatoire") String nom) {
        this.nom = nom;
    }

    public @NotBlank(message = "Le prénom est obligatoire") String getPrenom() {
        return prenom;
    }

    public void setPrenom(@NotBlank(message = "Le prénom est obligatoire") String prenom) {
        this.prenom = prenom;
    }

    public @Pattern(regexp = "^[0-9]{10}$", message = "Le téléphone fixe doit contenir 10 chiffres") String getTelephoneFixe() {
        return telephoneFixe;
    }

    public void setTelephoneFixe(@Pattern(regexp = "^[0-9]{10}$", message = "Le téléphone fixe doit contenir 10 chiffres") String telephoneFixe) {
        this.telephoneFixe = telephoneFixe;
    }

    public @Pattern(regexp = "^[0-9]{10}$", message = "Le téléphone portable doit contenir 10 chiffres") String getTelephonePortable() {
        return telephonePortable;
    }

    public void setTelephonePortable(@Pattern(regexp = "^[0-9]{10}$", message = "Le téléphone portable doit contenir 10 chiffres") String telephonePortable) {
        this.telephonePortable = telephonePortable;
    }

    public @NotBlank(message = "L'email est obligatoire") @Email(message = "L'email doit être valide") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "L'email est obligatoire") @Email(message = "L'email doit être valide") String email) {
        this.email = email;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }
}