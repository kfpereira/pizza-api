package com.fantin.pizza.config.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RecordNotFoundException extends Exception {

    public RecordNotFoundException(String message) {
        super(message);
    }

}
