package com.fantin.pizza.config.errors;

import lombok.Getter;

@Getter
public enum ErrorMessages {

    REQUIRED_SABOR("Sabor obrigatório"),
    NOT_EXISTING_TASTE("Sabor Inexistente - Use o método GET para verificar os sabores válidos"),
    REQUIRED_PEDIDO("Pedido é Obrigatório"),
    PEDIDO_NOT_FOUND("Pedido não encontrado"),
    ADICIONAL_NOT_FOUND("Tipo descricao não existente");

    private String message;

    ErrorMessages(String message) {
        this.message = message;
    }

}
