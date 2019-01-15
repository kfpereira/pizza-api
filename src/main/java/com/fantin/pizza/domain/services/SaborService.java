package com.fantin.pizza.domain.services;

import com.fantin.pizza.domain.model.Sabor;
import com.fantin.pizza.domain.repositories.SaborRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaborService {

    @Autowired
    private SaborRepository repository;

    public Sabor save(String descricao, Integer tempoAdicional) {
        return repository.saveAndFlush(getSabor(descricao, tempoAdicional));
    }

    private Sabor getSabor(String descricao, Integer tempoAdicional) {
        return Sabor.builder()
                .descricao(descricao)
                .tempoAdicional(tempoAdicional)
                .build();
    }

}
