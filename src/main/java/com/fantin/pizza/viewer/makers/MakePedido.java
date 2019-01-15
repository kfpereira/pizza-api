package com.fantin.pizza.viewer.makers;

import com.fantin.pizza.domain.model.Adicional;
import com.fantin.pizza.domain.model.Pedido;
import com.fantin.pizza.domain.model.PedidoAdicional;
import com.fantin.pizza.viewer.PedidoAdicionalOutVM;
import com.fantin.pizza.viewer.PedidoOutVM;
import com.fantin.pizza.viewer.PedidoOutVMFirstStep;

import java.util.List;
import java.util.stream.Collectors;

public class MakePedido {

    private MakePedido() {}

    public static PedidoOutVM toPedidoOutVM(Pedido pedido, List<PedidoAdicional> adicionalList) {
        PedidoOutVM vm = new PedidoOutVM();
        vm.setIdPedido(pedido.getId());
        vm.setTamanho(pedido.getTamanho().getTypeTamanho());
        vm.setSabor(pedido.getSabor().getDescricao());
        vm.setTempoTotal(pedido.getTempoTotalPreparo());
        vm.setValorTotal(pedido.getValorTotal());
        vm.setAdicionais(toPedidoAdicionalOutVM(adicionalList));
        return vm;
    }

    public static PedidoOutVMFirstStep toPedidoOutVMFirstStep(Pedido pedido, List<Adicional> adicionalList) {
        PedidoOutVMFirstStep vm = new PedidoOutVMFirstStep();
        vm.setIdPedido(pedido.getId());
        vm.setTamanho(pedido.getTamanho().getTypeTamanho());
        vm.setSabor(pedido.getSabor().getDescricao());
        vm.setTempoTotal(pedido.getTempoTotalPreparo());
        vm.setValorTotal(pedido.getValorTotal());
        vm.setAdicionaisParaEscolha(toPedidoAdicionalOutVMFirstStep(adicionalList));
        return vm;
    }

    private static List<PedidoAdicionalOutVM> toPedidoAdicionalOutVM(List<PedidoAdicional> adicionalList) {
        return adicionalList == null || adicionalList.isEmpty()?
                null:
                adicionalList
                .stream()
                .map(MakePedido::getPedidoAdicionalOutVM)
                .collect(Collectors.toList());
    }

    private static PedidoAdicionalOutVM getPedidoAdicionalOutVM(PedidoAdicional pedidoAdicional) {
        PedidoAdicionalOutVM vm = new PedidoAdicionalOutVM();
        vm.setAdicional(pedidoAdicional.getAdicional().getDescricao());
        vm.setTempoAdicional(pedidoAdicional.getTempoPreparo());
        vm.setValorAdicional(pedidoAdicional.getValor());
        return vm;
    }

    private static List<PedidoAdicionalOutVM> toPedidoAdicionalOutVMFirstStep(List<Adicional> adicionalList) {
        return adicionalList == null || adicionalList.isEmpty()?
                null:
                adicionalList
                        .stream()
                        .map(MakePedido::getPedidoAdicionalOutVM)
                        .collect(Collectors.toList());
    }

    private static PedidoAdicionalOutVM getPedidoAdicionalOutVM(Adicional pedidoAdicional) {
        PedidoAdicionalOutVM vm = new PedidoAdicionalOutVM();
        vm.setAdicional(pedidoAdicional.getDescricao());
        vm.setTempoAdicional(pedidoAdicional.getTempoPreparo());
        vm.setValorAdicional(pedidoAdicional.getValorAdicional());
        return vm;
    }


}
