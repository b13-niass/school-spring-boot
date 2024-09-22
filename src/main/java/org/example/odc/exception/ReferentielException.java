package org.example.odc.exception;

import org.springframework.http.HttpStatus;

public class ReferentielException extends RuntimeException {

    private final HttpStatus status;

    public ReferentielException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public ReferentielException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
