package com.fantin.pizza.config;

import com.fantin.pizza.domain.Sabor;
import com.fantin.pizza.repositories.SaborRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class InitSabores {

    @Autowired
    private SaborRepository repository;

    public void init() {
        repository.saveAndFlush(getSabor("CALABRESA", BigDecimal.ZERO));
        repository.saveAndFlush(getSabor("MARGUERITA", BigDecimal.ZERO));
        repository.saveAndFlush(getSabor("PORTUGUESA", BigDecimal.valueOf(5)));
    }

    private Sabor getSabor(String descricao, BigDecimal valorAdicional) {
        return Sabor.builder()
                .descricao(descricao)
                .valorAdicional(valorAdicional)
                .build();
    }

}
