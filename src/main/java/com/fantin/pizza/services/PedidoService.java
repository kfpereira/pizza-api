package com.fantin.pizza.services;

import com.fantin.pizza.domain.Pedido;
import com.fantin.pizza.domain.Sabor;
import com.fantin.pizza.domain.Tamanho;
import com.fantin.pizza.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private OrderRepository repository;

    public Pedido save(Tamanho tamanho, Sabor sabor) {
        return repository.saveAndFlush(getOrder(tamanho, sabor));
    }

    private Pedido getOrder(Tamanho tamanho, Sabor sabor) {
        return Pedido.builder()
                .tamanho(tamanho)
                .sabor(sabor)
                .valorTotal(tamanho.getValor().add(sabor.getValorAdicional()))
                .build();
    }

}
