package com.fantin.pizza.viewer;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class PedidoOutVM {

    private ResumoVM resumo;
    private List<ResumoPersonalizacaoVM> personalizacao;
    private BigDecimal valorTotal;
    private Integer tempoPreparo;

}
