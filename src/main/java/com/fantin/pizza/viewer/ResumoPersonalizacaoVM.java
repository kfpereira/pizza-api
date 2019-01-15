package com.fantin.pizza.viewer;

import com.fantin.pizza.domain.type.TypePersonalizacao;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ResumoPersonalizacaoVM {

    private TypePersonalizacao adicional;
    private BigDecimal valor;

}
