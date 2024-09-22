package org.example.odc.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ReferentielException.class)
    public ResponseEntity<String> handleReferentielException(ReferentielException ex) {
        // Renvoie la réponse avec le message et le code de statut personnalisé
        return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
    }

    // Vous pouvez ajouter d'autres gestionnaires pour d'autres types d'exceptions
}
