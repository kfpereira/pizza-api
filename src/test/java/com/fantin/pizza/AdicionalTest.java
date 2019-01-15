package com.fantin.pizza;

import com.fantin.pizza.config.core.FunctionalTest;
import com.fantin.pizza.domain.model.Adicional;
import com.fantin.pizza.domain.repositories.AdicionalRepository;
import com.fantin.pizza.environments.EnvAdicional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;

import static com.fantin.pizza.domain.type.TypePersonalizacao.BORDA_RECHEADA;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@FunctionalTest
class AdicionalTest {

    @Autowired
    private AdicionalRepository repository;

    @Autowired
    private EnvAdicional envAdicional;

    @Test
    void shouldNotAcceptNullAdicional() {
        Adicional adicional = Adicional.builder()
                .tempoPreparo(5)
                .valorAdicional(BigDecimal.valueOf(5))
                .build();

        assertThrows(ConstraintViolationException.class,
                () -> repository.saveAndFlush(adicional),
                "Adicional não pode ser nulo"
        );
    }

    @Test
    void shouldForbiddenSameAdicional() {
        Adicional adicional = Adicional.builder()
                .descricao(BORDA_RECHEADA)
                .tempoPreparo(5)
                .valorAdicional(BigDecimal.valueOf(5))
                .build();

        repository.saveAndFlush(adicional);

        Adicional adicionalEqual = Adicional.builder()
                .descricao(BORDA_RECHEADA)
                .tempoPreparo(4)
                .valorAdicional(BigDecimal.valueOf(3))
                .build();

        assertThrows(DataIntegrityViolationException.class,
                () -> repository.saveAndFlush(adicionalEqual),
                ""
        );
    }

    @Test
    void loadEnvironment() {
        envAdicional.init();
        assertEquals(3, repository.findAll().size());
    }

}
