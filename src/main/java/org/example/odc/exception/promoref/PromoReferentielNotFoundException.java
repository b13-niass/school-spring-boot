package org.example.odc.exception.promoref;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PromoReferentielNotFoundException extends RuntimeException {

    public PromoReferentielNotFoundException(String message) {
        super(message);
    }
}
