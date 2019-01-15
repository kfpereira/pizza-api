package com.fantin.pizza.environments;

import com.fantin.pizza.domain.model.Pedido;
import com.fantin.pizza.domain.model.Sabor;
import com.fantin.pizza.domain.model.Tamanho;
import com.fantin.pizza.domain.repositories.SaborRepository;
import com.fantin.pizza.domain.repositories.TamanhoRepository;
import com.fantin.pizza.domain.services.PedidoService;
import com.fantin.pizza.domain.type.TypeTamanho;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.fantin.pizza.domain.type.TypeTamanho.GRANDE;

@Component
public class EnvPedido {

    private static final String PORTUGUESA = "PORTUGUESA";

    @Autowired
    private PedidoService service;

    @Autowired
    private TamanhoRepository tamanhoRepository;

    @Autowired
    private SaborRepository saborRepository;

    @Autowired
    private EnvOrder envOrder;

    public Pedido init() {
        initEnvironments();
        return service.save(getByTypeTamanho(GRANDE), getByDescricao(PORTUGUESA));
    }

    private Sabor getByDescricao(String descricao) {
        return saborRepository.findByDescricao(descricao);
    }

    private Tamanho getByTypeTamanho(TypeTamanho tamanho) {
        return tamanhoRepository.findByTypeTamanho(tamanho);
    }

    private void initEnvironments() {
        envOrder.initEnvironments();
    }

}
