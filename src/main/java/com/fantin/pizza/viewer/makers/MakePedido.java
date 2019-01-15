package com.fantin.pizza.viewer.makers;

import com.fantin.pizza.domain.model.Pedido;
import com.fantin.pizza.domain.model.PedidoAdicional;
import com.fantin.pizza.viewer.PedidoOutVM;
import com.fantin.pizza.viewer.ResumoPersonalizacaoVM;
import com.fantin.pizza.viewer.ResumoVM;

import java.util.List;
import java.util.stream.Collectors;

public class MakePedido {

    private MakePedido() {}

    public static PedidoOutVM toPedidoOutVM(Pedido pedido, List<PedidoAdicional> adicionalList) {
        PedidoOutVM vm = new PedidoOutVM();
        vm.setTempoPreparo(pedido.getTempoTotalPreparo());
        vm.setValorTotal(pedido.getValorTotal());
        vm.setResumo(getResumo(pedido));
        vm.setPersonalizacao(getPersonalizacao(adicionalList));
        return vm;
    }

    private static List<ResumoPersonalizacaoVM> getPersonalizacao(List<PedidoAdicional> list) {
        return list == null || list.isEmpty()?
                null:
                list.stream().map(MakePedido::getResumoPersonalizacaoVM).collect(Collectors.toList());
    }

    private static ResumoPersonalizacaoVM getResumoPersonalizacaoVM(PedidoAdicional pedidoAdicional) {
        ResumoPersonalizacaoVM vm = new ResumoPersonalizacaoVM();
        vm.setAdicional(pedidoAdicional.getAdicional().getDescricao());
        vm.setValor(pedidoAdicional.getValor());
        return vm;
    }

    private static ResumoVM getResumo(Pedido pedido) {
        ResumoVM vm = new ResumoVM();
        vm.setTamanho(pedido.getTamanho().getTypeTamanho());
        vm.setSabor(pedido.getSabor().getDescricao());
        vm.setPreco(pedido.getTamanho().getValor());
        return vm;
    }
}
