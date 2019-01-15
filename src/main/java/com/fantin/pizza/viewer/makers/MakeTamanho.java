package com.fantin.pizza.viewer.makers;

import com.fantin.pizza.domain.model.Tamanho;
import com.fantin.pizza.viewer.TamanhoVM;

public class MakeTamanho {

    private MakeTamanho() {}

    public static TamanhoVM toTamanhoVM(Tamanho tamanho) {
        TamanhoVM vm = new TamanhoVM();
        vm.setTamanho(tamanho.getTypeTamanho());
        vm.setTempoPreparo(tamanho.getTempoPreparo());
        vm.setValor(tamanho.getValor());

        return vm;
    }
}
