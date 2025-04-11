package com.entreprise.annuaire.dto;

public class JwtResponseDTO {
    private String token;
    private String type = "Bearer";
    private String username;

    // Constructeur par défaut
    public JwtResponseDTO() {
    }

    // Constructeur avec tous les champs
    public JwtResponseDTO(String token, String username) {
        this.token = token;
        this.username = username;
    }

    // Constructeur complet
    public JwtResponseDTO(String token, String type, String username) {
        this.token = token;
        this.type = type;
        this.username = username;
    }

    // Getters et Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}