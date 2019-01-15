package com.fantin.pizza.viewer.makers;

import com.fantin.pizza.domain.model.Sabor;
import com.fantin.pizza.viewer.SaborOutVM;

public class MakeSabor {

    private MakeSabor() {}

    public static SaborOutVM toSaborVM(Sabor sabor) {
        SaborOutVM saborOutVM = new SaborOutVM();
        saborOutVM.setSabor(sabor.getDescricao());
        saborOutVM.setValorAdicional(sabor.getValorAdicional());
        return saborOutVM;
    }

}
