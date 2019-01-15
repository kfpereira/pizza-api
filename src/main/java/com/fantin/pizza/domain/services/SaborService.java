package com.fantin.pizza.domain.services;

import com.fantin.pizza.domain.model.Sabor;
import com.fantin.pizza.domain.repositories.SaborRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SaborService {

    @Autowired
    private SaborRepository repository;

    public Sabor save(String descricao, BigDecimal valor) {
        return repository.saveAndFlush(getSabor(descricao, valor));
    }

    private Sabor getSabor(String descricao, BigDecimal valorAdicional) {
        return Sabor.builder()
                .descricao(descricao)
                .valorAdicional(valorAdicional)
                .build();
    }

}
