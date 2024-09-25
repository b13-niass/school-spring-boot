package org.example.odc.exception.promo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class PromoForbiddenException extends RuntimeException {

    public PromoForbiddenException(String message) {
        super(message);
    }
}

