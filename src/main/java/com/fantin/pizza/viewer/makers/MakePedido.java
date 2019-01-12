package com.fantin.pizza.viewer.makers;

import com.fantin.pizza.domain.Pedido;
import com.fantin.pizza.viewer.FirstStepOutVM;

public class MakePedido {

    private MakePedido() {}

    public static FirstStepOutVM toFirstStepOut(Pedido pedido) {
        FirstStepOutVM vm = new FirstStepOutVM();
        vm.setTamanho(pedido.getTamanho().getTypeTamanho());
        vm.setSabor(pedido.getSabor().getDescricao());
        vm.setValorTotal(pedido.getValorTotal());
        return vm;
    }
}
