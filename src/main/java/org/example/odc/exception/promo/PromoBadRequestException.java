package org.example.odc.exception.promo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PromoBadRequestException extends RuntimeException {

    public PromoBadRequestException(String message) {
        super(message);
    }
}
