package org.example.odc.exception.promo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PromoNotFoundException extends RuntimeException {

    public PromoNotFoundException(String message) {
        super(message);
    }
}
