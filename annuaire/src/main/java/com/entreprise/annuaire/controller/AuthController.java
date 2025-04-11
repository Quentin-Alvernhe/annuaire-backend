package com.entreprise.annuaire.controller;

import com.entreprise.annuaire.dto.AdminLoginDTO;
import com.entreprise.annuaire.dto.JwtResponseDTO;
import com.entreprise.annuaire.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> login(@Valid @RequestBody AdminLoginDTO loginDTO) {
        return ResponseEntity.ok(authService.login(loginDTO));
    }
}