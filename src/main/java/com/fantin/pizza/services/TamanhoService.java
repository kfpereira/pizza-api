package com.fantin.pizza.services;

import com.fantin.pizza.domain.Tamanho;
import com.fantin.pizza.domain.type.TypeTamanho;
import com.fantin.pizza.repositories.TamanhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TamanhoService {

    @Autowired
    private TamanhoRepository repository;

    public Tamanho save(TypeTamanho typeTamanho, int tempo, BigDecimal valor) {
        return repository.saveAndFlush(getTamanho(typeTamanho, tempo, valor));
    }
    private Tamanho getTamanho(TypeTamanho tamanho, int tempo, BigDecimal valor) {
        return Tamanho.builder()
                .typeTamanho(tamanho)
                .tempoPreparo(tempo)
                .valor(valor)
                .build();
    }

}
