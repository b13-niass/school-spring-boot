package org.example.odc.exception.promo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class PromoInternalServerException extends RuntimeException {

    public PromoInternalServerException(String message) {
        super(message);
    }
}