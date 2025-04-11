package com.entreprise.annuaire.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom d'utilisateur est obligatoire")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "Le mot de passe est obligatoire")
    private String password;

    public Admin() {
    }

    public Admin(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Le nom d'utilisateur est obligatoire") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Le nom d'utilisateur est obligatoire") String username) {
        this.username = username;
    }

    public @NotBlank(message = "Le mot de passe est obligatoire") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Le mot de passe est obligatoire") String password) {
        this.password = password;
    }
}