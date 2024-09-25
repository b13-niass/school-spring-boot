package org.example.odc.exception.promo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class PromoUnauthorizedException extends RuntimeException {

    public PromoUnauthorizedException(String message) {
        super(message);
    }
}