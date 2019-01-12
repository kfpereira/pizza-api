package com.fantin.pizza.viewer;

import com.fantin.pizza.domain.type.TypeTamanho;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FindFirstStepVM {

    private List<TypeTamanho> tamanhos;
    private List<SaborVM> sabores;

}
