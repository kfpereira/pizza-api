package com.fantin.pizza.viewer;

import com.fantin.pizza.domain.type.TypeTamanho;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class PedidoOutVM {

    private Long idPedido;
    private TypeTamanho tamanho;
    private String sabor;
    private Integer tempoTotal;
    private BigDecimal valorTotal;
    private List<PedidoAdicionalOutVM> adicionais;

}
