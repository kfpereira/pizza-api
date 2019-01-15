package com.fantin.pizza;

import com.fantin.pizza.config.core.FunctionalTest;
import com.fantin.pizza.domain.model.Adicional;
import com.fantin.pizza.domain.model.Pedido;
import com.fantin.pizza.domain.model.PedidoAdicional;
import com.fantin.pizza.domain.repositories.AdicionalRepository;
import com.fantin.pizza.domain.repositories.PersonalizacaoRepository;
import com.fantin.pizza.environments.EnvAdicional;
import com.fantin.pizza.environments.EnvPedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;

import static com.fantin.pizza.domain.type.TypePersonalizacao.BORDA_RECHEADA;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@FunctionalTest
class PedidoAdicionalTest {

    @Autowired
    private PersonalizacaoRepository repository;

    @Autowired
    private AdicionalRepository adicionalRepository;

    @Autowired
    private EnvAdicional envAdicional;

    @Autowired
    private EnvPedido envPedido;

    @BeforeEach
    void setup() {
        envAdicional.init();
    }

    @Test
    void shouldNotExistsPersonalizacaoWithoutPedido() {
        Adicional adicional = adicionalRepository.findByDescricao(BORDA_RECHEADA);

        PedidoAdicional pedidoAdicional = PedidoAdicional.builder()
                .adicional(adicional)
                .tempoPreparo(adicional.getTempoPreparo())
                .valor(adicional.getValorAdicional())
                .build();

        assertThrows(ConstraintViolationException.class,
                () -> repository.saveAndFlush(pedidoAdicional),
                "Pedido é Obrigatório"
        );
    }

    @Test
    void shouldNotAcceptNullAdicional() {
        Pedido pedido = envPedido.init();
        PedidoAdicional pedidoAdicional = PedidoAdicional.builder()
                .pedido(pedido)
                .tempoPreparo(5)
                .valor(BigDecimal.valueOf(5))
                .build();

        assertThrows(ConstraintViolationException.class,
                () -> repository.saveAndFlush(pedidoAdicional),
                "Adicional não pode ser nulo no pedido"
        );
    }

    @Test
    void shouldForbiddenSamePedidoWithSameAdicionais() {
        Pedido pedido = envPedido.init();
        Adicional adicional = adicionalRepository.findByDescricao(BORDA_RECHEADA);

        PedidoAdicional pedidoAdicional = PedidoAdicional.builder()
                .pedido(pedido)
                .adicional(adicional)
                .tempoPreparo(adicional.getTempoPreparo())
                .valor(adicional.getValorAdicional())
                .build();

        repository.saveAndFlush(pedidoAdicional);

        PedidoAdicional pedidoAdicionalEqual = PedidoAdicional.builder()
                .pedido(pedido)
                .adicional(adicional)
                .tempoPreparo(adicional.getTempoPreparo())
                .valor(adicional.getValorAdicional())
                .build();

        assertThrows(DataIntegrityViolationException.class,
                () -> repository.saveAndFlush(pedidoAdicionalEqual),
                ""
        );
    }


}
