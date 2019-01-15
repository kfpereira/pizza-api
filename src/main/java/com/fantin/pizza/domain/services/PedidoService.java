package com.fantin.pizza.domain.services;

import com.fantin.pizza.config.errors.ErrorMessages;
import com.fantin.pizza.config.exceptions.RecordNotFoundException;
import com.fantin.pizza.domain.model.*;
import com.fantin.pizza.domain.repositories.OrderRepository;
import com.fantin.pizza.domain.repositories.PersonalizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private PersonalizacaoRepository personalizacaoRepository;

    public Pedido save(Tamanho tamanho, Sabor sabor) {
        return repository.saveAndFlush(getOrder(tamanho, sabor));
    }

    public void save(Pedido pedido, List<Adicional> adicionalList) {
        BigDecimal valor = pedido.getValorTotal();
        int tempo = pedido.getTempoTotalPreparo();

        for (Adicional adicional : adicionalList) {
            personalizacaoRepository.saveAndFlush(getPedidoAdicional(pedido, adicional));
            valor = valor.add(adicional.getValorAdicional());
            tempo += adicional.getTempoPreparo();
        }

        pedido.setValorTotal(valor);
        pedido.setTempoTotalPreparo(tempo);
        repository.saveAndFlush(pedido);
    }

    private PedidoAdicional getPedidoAdicional(Pedido pedido, Adicional adicional) {
        return PedidoAdicional.builder()
                        .pedido(pedido)
                        .adicional(adicional)
                        .tempoPreparo(adicional.getTempoPreparo())
                        .valor(adicional.getValorAdicional())
                        .build();
    }

    public Pedido find(Long id) throws RecordNotFoundException {
        Optional<Pedido> pedidoOptional = repository.findById(id);
        if (!pedidoOptional.isPresent())
            throw new RecordNotFoundException(ErrorMessages.PEDIDO_NOT_FOUND.getMessage());

        return pedidoOptional.get();
    }

    public List<PedidoAdicional> find(Pedido pedido) {
        return personalizacaoRepository.findByPedido(pedido);
    }

    private Pedido getOrder(Tamanho tamanho, Sabor sabor) {
        return Pedido.builder()
                .tamanho(tamanho)
                .sabor(sabor)
                .tempoTotalPreparo(tamanho.getTempoPreparo() + (sabor.getTempoAdicional()))
                .valorTotal(tamanho.getValor())
                .build();
    }

}
