package com.fantin.pizza;

import com.fantin.pizza.config.core.FunctionalTest;
import com.fantin.pizza.config.errors.ErrorMessages;
import com.fantin.pizza.config.exceptions.DataRequiredException;
import com.fantin.pizza.config.exceptions.RecordNotFoundException;
import com.fantin.pizza.controller.OrdersController;
import com.fantin.pizza.domain.type.TypeTamanho;
import com.fantin.pizza.environments.EnvOrder;
import com.fantin.pizza.viewer.FindFirstStepVM;
import com.fantin.pizza.viewer.FirstStepOutVM;
import com.fantin.pizza.viewer.FirstStepVM;
import com.fantin.pizza.viewer.SaborVM;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@FunctionalTest
class OrdersControllerTest {

    @Autowired
    private OrdersController controller;

    @Autowired
    private EnvOrder envOrder;

    @BeforeEach
    void setup() {
        envOrder.initEnvironments();
    }

    @Test
    void getInitSystem() {
        FindFirstStepVM stepVM = controller.getData(null);

        assertEquals("Tamanhos", 3, stepVM.getTamanhos().size());
        assertEquals("Sabores", 3, stepVM.getSabores().size());
    }

    @Test
    void shouldBeCalabresaGrande() throws Exception {
        SaborVM saborVM = getSaborVM("CALABRESA");
        FirstStepVM firstStep = getFirstStepVM(saborVM, TypeTamanho.GRANDE);

        FirstStepOutVM outVM = controller.postOrder(null, firstStep);

        assertEquals(TypeTamanho.GRANDE, outVM.getTamanho());
        assertEquals("CALABRESA", outVM.getSabor());
        assertEquals(BigDecimal.valueOf(40), outVM.getValorTotal());
    }

    @Test
    void shouldBePortuguesaGrande() throws Exception {
        SaborVM saborVM = getSaborVM("PORTUGUESA");
        FirstStepVM firstStep = getFirstStepVM(saborVM, TypeTamanho.GRANDE);

        FirstStepOutVM outVM = controller.postOrder(null, firstStep);

        assertEquals(TypeTamanho.GRANDE, outVM.getTamanho());
        assertEquals("PORTUGUESA", outVM.getSabor());
        assertEquals(BigDecimal.valueOf(45), outVM.getValorTotal());
    }

    @Test
    void shouldBeCalabresaMedia() throws Exception {
        SaborVM saborVM = getSaborVM("CALABRESA");
        FirstStepVM firstStep = getFirstStepVM(saborVM, TypeTamanho.MEDIA);

        FirstStepOutVM outVM = controller.postOrder(null, firstStep);

        assertEquals(TypeTamanho.MEDIA, outVM.getTamanho());
        assertEquals("CALABRESA", outVM.getSabor());
        assertEquals(BigDecimal.valueOf(30), outVM.getValorTotal());
    }

    @Test
    void shouldBePortuguesaMedia() throws Exception {
        SaborVM saborVM = getSaborVM("PORTUGUESA");
        FirstStepVM firstStep = getFirstStepVM(saborVM, TypeTamanho.MEDIA);

        FirstStepOutVM outVM = controller.postOrder(null, firstStep);

        assertEquals(TypeTamanho.MEDIA, outVM.getTamanho());
        assertEquals("PORTUGUESA", outVM.getSabor());
        assertEquals(BigDecimal.valueOf(35), outVM.getValorTotal());
    }

    @Test
    void shouldBeCalabresaPequena() throws Exception {
        SaborVM saborVM = getSaborVM("CALABRESA");
        FirstStepVM firstStep = getFirstStepVM(saborVM, TypeTamanho.PEQUENA);

        FirstStepOutVM outVM = controller.postOrder(null, firstStep);

        assertEquals(TypeTamanho.PEQUENA, outVM.getTamanho());
        assertEquals("CALABRESA", outVM.getSabor());
        assertEquals(BigDecimal.valueOf(20), outVM.getValorTotal());
    }

    @Test
    void shouldBePortuguesaPequena() throws Exception {
        SaborVM saborVM = getSaborVM("PORTUGUESA");
        FirstStepVM firstStep = getFirstStepVM(saborVM, TypeTamanho.PEQUENA);

        FirstStepOutVM outVM = controller.postOrder(null, firstStep);

        assertEquals(TypeTamanho.PEQUENA, outVM.getTamanho());
        assertEquals("PORTUGUESA", outVM.getSabor());
        assertEquals(BigDecimal.valueOf(25), outVM.getValorTotal());
    }

    @Test
    void pizzaShouldBeRequired() throws Exception {
        FirstStepVM firstStep = getFirstStepVM(null, TypeTamanho.PEQUENA);
        assertThrows(DataRequiredException.class,
                () -> controller.postOrder(null, firstStep),
                ErrorMessages.SABOR_OBRIGATORIO.getMessage());
    }

    @Test
    void pizzaShouldBeFind() throws Exception {
        SaborVM saborVM = getSaborVM("peperoni");
        FirstStepVM firstStep = getFirstStepVM(saborVM, TypeTamanho.PEQUENA);
        assertThrows(RecordNotFoundException.class,
                () -> controller.postOrder(null, firstStep),
                ErrorMessages.SABOR_INEXISTENTE.getMessage());
    }

    private FirstStepVM getFirstStepVM(SaborVM saborVM, TypeTamanho grande) {
        FirstStepVM firstStep = new FirstStepVM();
        firstStep.setTamanho(grande);
        firstStep.setSabor(saborVM);
        return firstStep;
    }

    private SaborVM getSaborVM(String sabor) {
        if (sabor == null)
            return null;

        SaborVM saborVM = new SaborVM();
        saborVM.setSabor(sabor);
        return saborVM;
    }
}
