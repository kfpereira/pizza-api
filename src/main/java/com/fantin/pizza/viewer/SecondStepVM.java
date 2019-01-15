package com.fantin.pizza.viewer;

import com.fantin.pizza.domain.type.TypePersonalizacao;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SecondStepVM {

    @ApiModelProperty(required=true)
    private Long idPedido;

    private List<TypePersonalizacao> adicionais;

}
