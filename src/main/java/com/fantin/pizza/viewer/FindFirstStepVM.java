package com.fantin.pizza.viewer;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FindFirstStepVM {

    private List<TamanhoVM> tamanhos;
    private List<SaborOutVM> sabores;

}
