package org.example.odc.exception.promo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class PromoAlreadyExistsException extends RuntimeException {

    public PromoAlreadyExistsException(String message) {
        super(message);
    }
}