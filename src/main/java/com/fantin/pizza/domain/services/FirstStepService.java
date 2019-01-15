package com.fantin.pizza.domain.services;

import com.fantin.pizza.config.exceptions.RecordNotFoundException;
import com.fantin.pizza.domain.model.Pedido;
import com.fantin.pizza.domain.model.Sabor;
import com.fantin.pizza.domain.model.Tamanho;
import com.fantin.pizza.domain.repositories.TamanhoRepository;
import com.fantin.pizza.domain.type.TypeTamanho;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.fantin.pizza.config.errors.ErrorMessages.NOT_EXISTING_TASTE;

@Service
public class FirstStepService {

    @Autowired
    private TamanhoRepository tamanhoRepository;

    @Autowired
    private PedidoService pedidoService;

    public Pedido executeOrder(Sabor sabor, TypeTamanho typeTamanho) throws RecordNotFoundException {
        if (sabor == null)
            throw new RecordNotFoundException(NOT_EXISTING_TASTE.getMessage());

        Tamanho tamanho = tamanhoRepository.findByTypeTamanho(typeTamanho);
        return pedidoService.save(tamanho, sabor);
    }

}
