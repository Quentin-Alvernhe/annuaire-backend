package com.entreprise.annuaire.exception;

import java.time.LocalDateTime;
import java.util.Map;

public class ValidationErrorResponse extends ErrorResponse {
    private Map<String, String> errors;

    // Constructeur par défaut
    public ValidationErrorResponse() {
        super();
    }

    // Constructeur avec tous les champs
    public ValidationErrorResponse(int status, String message, LocalDateTime timestamp, Map<String, String> errors) {
        super(status, message, timestamp);
        this.errors = errors;
    }

    // Getters et Setters
    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}