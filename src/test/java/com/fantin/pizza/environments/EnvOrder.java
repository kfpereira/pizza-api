package com.fantin.pizza.environments;

import com.fantin.pizza.domain.Sabor;
import com.fantin.pizza.domain.Tamanho;
import com.fantin.pizza.domain.type.TypeTamanho;
import com.fantin.pizza.repositories.SaborRepository;
import com.fantin.pizza.repositories.TamanhoRepository;
import com.fantin.pizza.services.PedidoService;
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

    private Sabor getByDescricao(String calabresa) {
        return saborRepository.findByDescricao(calabresa);
    }

    private Tamanho getByTypeTamanho(TypeTamanho pequena) {
        return tamanhoRepository.findByTypeTamanho(pequena);
    }

    public void initEnvironments() {
        envSabor.init();
        envTamanho.init();
    }
}
