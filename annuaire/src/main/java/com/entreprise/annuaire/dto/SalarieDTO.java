package com.entreprise.annuaire.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class SalarieDTO {
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
    private String email;

    private Long departementId;  // Renommé depuis serviceId
    private String departementNom;  // Renommé depuis serviceNom

    private Long siteId;
    private String siteVille;

    // Constructeur par défaut
    public SalarieDTO() {
    }

    // Constructeur avec tous les champs
    public SalarieDTO(Long id, String nom, String prenom, String telephoneFixe, String telephonePortable,
                      String email, Long departementId, String departementNom, Long siteId, String siteVille) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.telephoneFixe = telephoneFixe;
        this.telephonePortable = telephonePortable;
        this.email = email;
        this.departementId = departementId;
        this.departementNom = departementNom;
        this.siteId = siteId;
        this.siteVille = siteVille;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephoneFixe() {
        return telephoneFixe;
    }

    public void setTelephoneFixe(String telephoneFixe) {
        this.telephoneFixe = telephoneFixe;
    }

    public String getTelephonePortable() {
        return telephonePortable;
    }

    public void setTelephonePortable(String telephonePortable) {
        this.telephonePortable = telephonePortable;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getDepartementId() {  // Renommé depuis getServiceId
        return departementId;
    }

    public void setDepartementId(Long departementId) {  // Renommé depuis setServiceId
        this.departementId = departementId;
    }

    public String getDepartementNom() {  // Renommé depuis getServiceNom
        return departementNom;
    }

    public void setDepartementNom(String departementNom) {  // Renommé depuis setServiceNom
        this.departementNom = departementNom;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getSiteVille() {
        return siteVille;
    }

    public void setSiteVille(String siteVille) {
        this.siteVille = siteVille;
    }
}