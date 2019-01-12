package com.fantin.pizza.viewer;

import com.fantin.pizza.domain.type.TypeTamanho;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class FirstStepOutVM {

    private TypeTamanho tamanho;
    private String sabor;
    private BigDecimal valorTotal;

}
