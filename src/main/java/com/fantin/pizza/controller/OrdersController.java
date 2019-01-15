package com.fantin.pizza.controller;

import com.fantin.pizza.config.exceptions.DataRequiredException;
import com.fantin.pizza.config.exceptions.InvalidateDataException;
import com.fantin.pizza.config.exceptions.RecordNotFoundException;
import com.fantin.pizza.domain.model.Adicional;
import com.fantin.pizza.domain.model.Pedido;
import com.fantin.pizza.domain.model.Sabor;
import com.fantin.pizza.domain.repositories.SaborRepository;
import com.fantin.pizza.domain.repositories.TamanhoRepository;
import com.fantin.pizza.domain.services.AdicionalService;
import com.fantin.pizza.domain.services.FirstStepService;
import com.fantin.pizza.domain.services.PedidoService;
import com.fantin.pizza.domain.type.TypePersonalizacao;
import com.fantin.pizza.viewer.*;
import com.fantin.pizza.viewer.makers.MakeSabor;
import com.fantin.pizza.viewer.makers.MakeTamanho;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.fantin.pizza.config.errors.ErrorMessages.REQUIRED_SABOR;
import static com.fantin.pizza.viewer.makers.MakePedido.toPedidoOutVM;
import static com.fantin.pizza.viewer.makers.MakePedidoFirstStep.toPedidoOutVMFirstStep;

@Api(tags = { "ordersController" })
@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private FirstStepService firstStepService;

    @Autowired
    private AdicionalService adicionalService;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private SaborRepository saborRepository;

    @Autowired
    private TamanhoRepository tamanhoRepository;

    @GetMapping
    public FindFirstStepVM getData(HttpServletRequest request) {
        return getView();
    }

    @PostMapping
    public PedidoOutVMFirstStep postOrder(HttpServletRequest request, FirstStepVM firstStepVM) throws RecordNotFoundException, DataRequiredException {
        SaborInVM saborVM = firstStepVM.getSabor();
        if (saborVM == null || saborVM.getSabor() == null || saborVM.getSabor().isEmpty())
            throw new DataRequiredException(REQUIRED_SABOR.getMessage());

        Sabor sabor = saborRepository.findByDescricao(saborVM.getSabor().toUpperCase());
        Pedido pedido = firstStepService.executeOrder(sabor, firstStepVM.getTamanho());
        return toPedidoOutVMFirstStep(pedido, adicionalService.find(Arrays.asList(TypePersonalizacao.values())));
    }

    @PostMapping(value = "/adicionais")
    public PedidoOutVM postOrderAdicionais(HttpServletRequest request, SecondStepVM secondStepVM) throws RecordNotFoundException, InvalidateDataException {
        Pedido pedido = pedidoService.find(secondStepVM.getIdPedido());

        List<Adicional> adicionalList = adicionalService.find(secondStepVM.getAdicionais());
        pedidoService.save(pedido, adicionalList);
        return toPedidoOutVM(pedido, pedidoService.find(pedido));
    }

    private FindFirstStepVM getView() {
        FindFirstStepVM findFirstStepVM = new FindFirstStepVM();
        findFirstStepVM.setTamanhos(getTamanhosVM());
        findFirstStepVM.setSabores(getSaboresVM());

        return findFirstStepVM;
    }

    private List<TamanhoVM> getTamanhosVM() {
        return tamanhoRepository.findAll()
                .stream()
                .map(MakeTamanho::toTamanhoVM)
                .collect(Collectors.toList());
    }

    private List<SaborOutVM> getSaboresVM() {
        return saborRepository.findAll()
                .stream()
                .map(MakeSabor::toSaborVM)
                .collect(Collectors.toList());
    }

}
