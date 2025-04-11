package com.entreprise.annuaire.service;

import com.entreprise.annuaire.dto.AdminLoginDTO;
import com.entreprise.annuaire.dto.JwtResponseDTO;
import com.entreprise.annuaire.exception.InvalidCredentialsException;
import com.entreprise.annuaire.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public JwtResponseDTO login(AdminLoginDTO loginDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException("Nom d'utilisateur ou mot de passe incorrect");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        return new JwtResponseDTO(token, userDetails.getUsername());
    }
}