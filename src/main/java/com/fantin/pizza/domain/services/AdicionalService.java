package com.fantin.pizza.domain.services;

import com.fantin.pizza.config.errors.ErrorMessages;
import com.fantin.pizza.config.exceptions.RecordNotFoundException;
import com.fantin.pizza.domain.model.Adicional;
import com.fantin.pizza.domain.repositories.AdicionalRepository;
import com.fantin.pizza.domain.type.TypePersonalizacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdicionalService {

    @Autowired
    private AdicionalRepository adicionalRepository;

    public Adicional save(TypePersonalizacao typePersonalizacao, int tempoPreparo, BigDecimal valorAdicional) {
        return adicionalRepository.saveAndFlush(getAdicional(typePersonalizacao, tempoPreparo, valorAdicional));
    }

    public List<Adicional> find(List<TypePersonalizacao> adicionais) throws RecordNotFoundException {
        List<Adicional> result = new ArrayList<>();
        if (adicionais != null) {
            for (TypePersonalizacao adicional : adicionais)
                result.add(getAdicional(adicional));
        }
        return result;
    }

    private Adicional getAdicional(TypePersonalizacao typePersonalizacao) throws RecordNotFoundException {
        Adicional adicional = adicionalRepository.findByDescricao(typePersonalizacao);
        if (adicional == null)
            throw new RecordNotFoundException(ErrorMessages.ADICIONAL_NOT_FOUND.getMessage());

        return adicional;
    }

    private Adicional getAdicional(TypePersonalizacao personalizacao, int tempoPreparo, BigDecimal valor) {
        return Adicional.builder()
                .descricao(personalizacao)
                .tempoPreparo(tempoPreparo)
                .valorAdicional(valor)
                .build();
    }

}
