package com.fantin.pizza.viewer;

import com.fantin.pizza.domain.type.TypeTamanho;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class PedidoOutVMFirstStep {

    private Long idPedido;
    private TypeTamanho tamanho;
    private String sabor;
    private Integer tempoTotal;
    private BigDecimal valorTotal;

    @ApiModelProperty(value = "Opções para escolher no segundo passo (OPCIONAL)")
    private List<PedidoAdicionalOutVM> adicionaisParaEscolha;

}
