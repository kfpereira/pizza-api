package com.fantin.pizza.config.errors;

import lombok.Getter;

@Getter
public enum ErrorMessages {

    SABOR_OBRIGATORIO("Sabor obrigatório"),
    SABOR_INEXISTENTE("Sabor Inexistente - Use o método GET para verificar os sabores válidos");

    private String message;

    ErrorMessages(String message) {
        this.message = message;
    }

}
