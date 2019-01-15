package com.fantin.pizza;

import com.fantin.pizza.config.core.FunctionalTest;
import com.fantin.pizza.config.errors.ErrorMessages;
import com.fantin.pizza.config.exceptions.DataRequiredException;
import com.fantin.pizza.config.exceptions.RecordNotFoundException;
import com.fantin.pizza.controller.OrdersController;
import com.fantin.pizza.domain.type.TypePersonalizacao;
import com.fantin.pizza.domain.type.TypeTamanho;
import com.fantin.pizza.environments.EnvAdicional;
import com.fantin.pizza.environments.EnvOrder;
import com.fantin.pizza.viewer.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@FunctionalTest
class OrdersControllerTest {

    @Autowired
    private OrdersController controller;

    @Autowired
    private EnvOrder envOrder;

    @Autowired
    private EnvAdicional envAdicional;


    @BeforeEach
    void setup() {
        envOrder.initEnvironments();
        envAdicional.init();
    }

    @Test
    void getInitSystem() {
        FindFirstStepVM stepVM = controller.getData(null);

        assertEquals("Tamanhos", 3, stepVM.getTamanhos().size());
        assertEquals("Sabores", 3, stepVM.getSabores().size());
    }

    @Test
    void shouldBeCalabresaGrande() throws Exception {
        SaborInVM saborVM = getSaborVM("CALABRESA");
        FirstStepVM firstStep = getFirstStepVM(saborVM, TypeTamanho.GRANDE);

        PedidoOutVMFirstStep outVM = controller.postOrder(null, firstStep);

        assertNotNull(outVM.getIdPedido());
        assertEquals(TypeTamanho.GRANDE, outVM.getTamanho());
        assertEquals("CALABRESA", outVM.getSabor());
        assertEquals(BigDecimal.valueOf(40), outVM.getValorTotal());
    }

    @Test
    void shouldBePortuguesaGrande() throws Exception {
        SaborInVM saborVM = getSaborVM("PORTUGUESA");
        FirstStepVM firstStep = getFirstStepVM(saborVM, TypeTamanho.GRANDE);

        PedidoOutVMFirstStep outVM = controller.postOrder(null, firstStep);

        assertNotNull(outVM.getIdPedido());
        assertEquals(TypeTamanho.GRANDE, outVM.getTamanho());
        assertEquals("PORTUGUESA", outVM.getSabor());
        assertEquals(BigDecimal.valueOf(45), outVM.getValorTotal());
    }

    @Test
    void shouldBeCalabresaMedia() throws Exception {
        SaborInVM saborVM = getSaborVM("CALABRESA");
        FirstStepVM firstStep = getFirstStepVM(saborVM, TypeTamanho.MEDIA);

        PedidoOutVMFirstStep outVM = controller.postOrder(null, firstStep);

        assertNotNull(outVM.getIdPedido());
        assertEquals(TypeTamanho.MEDIA, outVM.getTamanho());
        assertEquals("CALABRESA", outVM.getSabor());
        assertEquals(BigDecimal.valueOf(30), outVM.getValorTotal());
    }

    @Test
    void shouldBePortuguesaMedia() throws Exception {
        SaborInVM saborVM = getSaborVM("PORTUGUESA");
        FirstStepVM firstStep = getFirstStepVM(saborVM, TypeTamanho.MEDIA);

        PedidoOutVMFirstStep outVM = controller.postOrder(null, firstStep);

        assertNotNull(outVM.getIdPedido());
        assertEquals(TypeTamanho.MEDIA, outVM.getTamanho());
        assertEquals("PORTUGUESA", outVM.getSabor());
        assertEquals(BigDecimal.valueOf(35), outVM.getValorTotal());
    }

    @Test
    void shouldBeCalabresaPequena() throws Exception {
        SaborInVM saborVM = getSaborVM("CALABRESA");
        FirstStepVM firstStep = getFirstStepVM(saborVM, TypeTamanho.PEQUENA);

        PedidoOutVMFirstStep outVM = controller.postOrder(null, firstStep);

        assertNotNull(outVM.getIdPedido());
        assertEquals(TypeTamanho.PEQUENA, outVM.getTamanho());
        assertEquals("CALABRESA", outVM.getSabor());
        assertEquals(BigDecimal.valueOf(20), outVM.getValorTotal());
    }

    @Test
    void shouldBePortuguesaPequena() throws Exception {
        SaborInVM saborVM = getSaborVM("PORTUGUESA");
        FirstStepVM firstStep = getFirstStepVM(saborVM, TypeTamanho.PEQUENA);

        PedidoOutVMFirstStep outVM = controller.postOrder(null, firstStep);

        assertNotNull(outVM.getIdPedido());
        assertEquals(TypeTamanho.PEQUENA, outVM.getTamanho());
        assertEquals("PORTUGUESA", outVM.getSabor());
        assertEquals(BigDecimal.valueOf(25), outVM.getValorTotal());
    }

    @Test
    void pizzaShouldBeRequired() throws Exception {
        FirstStepVM firstStep = getFirstStepVM(null, TypeTamanho.PEQUENA);
        assertThrows(DataRequiredException.class,
                () -> controller.postOrder(null, firstStep),
                ErrorMessages.REQUIRED_SABOR.getMessage());
    }

    @Test
    void pizzaShouldNotBeFind() throws Exception {
        SaborInVM saborVM = getSaborVM("peperoni");
        FirstStepVM firstStep = getFirstStepVM(saborVM, TypeTamanho.PEQUENA);
        assertThrows(RecordNotFoundException.class,
                () -> controller.postOrder(null, firstStep),
                ErrorMessages.NOT_EXISTING_TASTE.getMessage());
    }

    @Test
    void shouldFinalizeStep2WithAdicionaisEmpty() throws RecordNotFoundException, DataRequiredException {
        SaborInVM saborVM = getSaborVM("PORTUGUESA");
        FirstStepVM firstStep = getFirstStepVM(saborVM, TypeTamanho.PEQUENA);

        PedidoOutVMFirstStep outVM = controller.postOrder(null, firstStep);
        assertNotNull(outVM);

        Long idPedido = outVM.getIdPedido();
        List<TypePersonalizacao> adicionais = new ArrayList<>();

        SecondStepVM secondStepVM = new SecondStepVM();
        secondStepVM.setIdPedido(idPedido);
        secondStepVM.setAdicionais(adicionais);

        PedidoOutVM vm = controller.postOrderAdicionais(null, secondStepVM);

        assertNotNull(vm);
        assertEquals(outVM.getValorTotal(), vm.getValorTotal());
    }

    @Test
    void shouldFinalizeStep2WithAdicionaisNull() throws RecordNotFoundException, DataRequiredException {
        SaborInVM saborVM = getSaborVM("PORTUGUESA");
        FirstStepVM firstStep = getFirstStepVM(saborVM, TypeTamanho.PEQUENA);

        PedidoOutVMFirstStep outVM = controller.postOrder(null, firstStep);
        assertNotNull(outVM);

        SecondStepVM secondStepVM = new SecondStepVM();
        secondStepVM.setIdPedido(outVM.getIdPedido());
        secondStepVM.setAdicionais(null);

        PedidoOutVM vm = controller.postOrderAdicionais(null, secondStepVM);

        assertNotNull(vm);
        assertEquals(outVM.getValorTotal(), vm.getValorTotal());
    }

    @Test
    void shouldFinalizeStep2WithAdicionaisExtraBacon() throws RecordNotFoundException, DataRequiredException {
        SaborInVM saborVM = getSaborVM("PORTUGUESA");
        FirstStepVM firstStep = getFirstStepVM(saborVM, TypeTamanho.GRANDE);

        PedidoOutVMFirstStep outVM = controller.postOrder(null, firstStep);
        assertNotNull(outVM);

        Long idPedido = outVM.getIdPedido();
        List<TypePersonalizacao> adicionais = new ArrayList<>();
        adicionais.add(TypePersonalizacao.EXTRA_BACON);

        SecondStepVM secondStepVM = new SecondStepVM();
        secondStepVM.setIdPedido(idPedido);
        secondStepVM.setAdicionais(adicionais);

        PedidoOutVM vm = controller.postOrderAdicionais(null, secondStepVM);

        assertNotNull(vm);
        assertEquals(outVM.getValorTotal().add(BigDecimal.valueOf(3)), vm.getValorTotal());
    }

    @Test
    void shouldFinalizeStep2WithAdicionaisSemCebola() throws RecordNotFoundException, DataRequiredException {
        SaborInVM saborVM = getSaborVM("PORTUGUESA");
        FirstStepVM firstStep = getFirstStepVM(saborVM, TypeTamanho.GRANDE);

        PedidoOutVMFirstStep outVM = controller.postOrder(null, firstStep);
        assertNotNull(outVM);

        Long idPedido = outVM.getIdPedido();
        List<TypePersonalizacao> adicionais = new ArrayList<>();
        adicionais.add(TypePersonalizacao.SEM_CEBOLA);

        SecondStepVM secondStepVM = new SecondStepVM();
        secondStepVM.setIdPedido(idPedido);
        secondStepVM.setAdicionais(adicionais);

        PedidoOutVM vm = controller.postOrderAdicionais(null, secondStepVM);

        assertNotNull(vm);
        assertEquals(outVM.getValorTotal(), vm.getValorTotal());
    }

    @Test
    void shouldFinalizeStep2WithAdicionaisBordaRecheada() throws RecordNotFoundException, DataRequiredException {
        SaborInVM saborVM = getSaborVM("PORTUGUESA");
        FirstStepVM firstStep = getFirstStepVM(saborVM, TypeTamanho.GRANDE);

        PedidoOutVMFirstStep outVM = controller.postOrder(null, firstStep);
        assertNotNull(outVM);

        Long idPedido = outVM.getIdPedido();
        List<TypePersonalizacao> adicionais = new ArrayList<>();
        adicionais.add(TypePersonalizacao.BORDA_RECHEADA);

        SecondStepVM secondStepVM = new SecondStepVM();
        secondStepVM.setIdPedido(idPedido);
        secondStepVM.setAdicionais(adicionais);

        PedidoOutVM vm = controller.postOrderAdicionais(null, secondStepVM);

        assertNotNull(vm);
        assertEquals(outVM.getValorTotal().add(BigDecimal.valueOf(5)), vm.getValorTotal());
    }

    @Test
    void shouldFinalizeStep2WithAdicionaisExtraBaconAndBordaRecheada() throws RecordNotFoundException, DataRequiredException {
        SaborInVM saborVM = getSaborVM("PORTUGUESA");
        FirstStepVM firstStep = getFirstStepVM(saborVM, TypeTamanho.GRANDE);

        PedidoOutVMFirstStep outVM = controller.postOrder(null, firstStep);
        assertNotNull(outVM);

        Long idPedido = outVM.getIdPedido();
        List<TypePersonalizacao> adicionais = new ArrayList<>();
        adicionais.add(TypePersonalizacao.EXTRA_BACON);
        adicionais.add(TypePersonalizacao.BORDA_RECHEADA);

        SecondStepVM secondStepVM = new SecondStepVM();
        secondStepVM.setIdPedido(idPedido);
        secondStepVM.setAdicionais(adicionais);

        PedidoOutVM vm = controller.postOrderAdicionais(null, secondStepVM);

        assertNotNull(vm);
        assertEquals(outVM.getValorTotal().add(BigDecimal.valueOf(8)), vm.getValorTotal());
        assertEquals(Integer.valueOf(outVM.getTempoTotal() + 5), vm.getTempoTotal());
    }

    private FirstStepVM getFirstStepVM(SaborInVM saborVM, TypeTamanho grande) {
        FirstStepVM firstStep = new FirstStepVM();
        firstStep.setTamanho(grande);
        firstStep.setSabor(saborVM);
        return firstStep;
    }

    private SaborInVM getSaborVM(String sabor) {
        if (sabor == null)
            return null;

        SaborInVM saborVM = new SaborInVM();
        saborVM.setSabor(sabor);
        return saborVM;
    }
}
