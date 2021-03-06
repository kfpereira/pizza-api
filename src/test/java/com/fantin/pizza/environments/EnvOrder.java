package com.fantin.pizza.environments;

import com.fantin.pizza.domain.model.Sabor;
import com.fantin.pizza.domain.model.Tamanho;
import com.fantin.pizza.domain.repositories.SaborRepository;
import com.fantin.pizza.domain.repositories.TamanhoRepository;
import com.fantin.pizza.domain.services.PedidoService;
import com.fantin.pizza.domain.type.TypeTamanho;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnvOrder {

    private static final String CALABRESA = "CALABRESA";

    @Autowired
    private PedidoService service;

    @Autowired
    private TamanhoRepository tamanhoRepository;

    @Autowired
    private SaborRepository saborRepository;

    @Autowired
    private EnvTamanho envTamanho;

    @Autowired
    private EnvSabor envSabor;

    public void init() {
        initEnvironments();

        service.save(getByTypeTamanho(TypeTamanho.PEQUENA), getByDescricao(CALABRESA));
        service.save(getByTypeTamanho(TypeTamanho.MEDIA), getByDescricao(CALABRESA));
        service.save(getByTypeTamanho(TypeTamanho.GRANDE), getByDescricao(CALABRESA));
    }

    private Sabor getByDescricao(String descricao) {
        return saborRepository.findByDescricao(descricao);
    }

    private Tamanho getByTypeTamanho(TypeTamanho tamanho) {
        return tamanhoRepository.findByTypeTamanho(tamanho);
    }

    public void initEnvironments() {
        envSabor.init();
        envTamanho.init();
    }
}
