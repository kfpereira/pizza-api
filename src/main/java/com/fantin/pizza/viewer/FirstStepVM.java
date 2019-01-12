package com.fantin.pizza.viewer;

import com.fantin.pizza.domain.type.TypeTamanho;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FirstStepVM {

    @ApiModelProperty(required=true)
    private TypeTamanho tamanho;

    @ApiModelProperty(required=true)
    private SaborVM sabor;

}
