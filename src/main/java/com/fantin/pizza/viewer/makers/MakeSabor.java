package com.fantin.pizza.viewer.makers;

import com.fantin.pizza.domain.Sabor;
import com.fantin.pizza.viewer.SaborVM;

public class MakeSabor {

    private MakeSabor() {}

    public static SaborVM toSaborVM(Sabor sabor) {
        SaborVM saborVM = new SaborVM();
        saborVM.setSabor(sabor.getDescricao());
        return saborVM;
    }

}
