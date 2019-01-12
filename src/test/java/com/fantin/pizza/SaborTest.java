package com.fantin.pizza;

import com.fantin.pizza.config.core.FunctionalTest;
import com.fantin.pizza.domain.Sabor;
import com.fantin.pizza.environments.EnvSabor;
import com.fantin.pizza.repositories.SaborRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@FunctionalTest
class SaborTest {

    @Autowired
    private SaborRepository repository;

    @Autowired
    private EnvSabor envSabor;

    @Test
    void shouldBeCalabresa() {
        Sabor calabresa = Sabor.builder()
                .descricao("CALABRESA")
                .build();
        repository.saveAndFlush(calabresa);

        assertEquals(1, repository.findAll().size());
        assertEquals("CALABRESA", repository.findAll().get(0).getDescricao());
    }

    @Test
    void shouldBePortuguesa() {
        Sabor portuguesa = Sabor.builder()
                .descricao("PORTUGUESA")
                .build();
        repository.saveAndFlush(portuguesa);

        assertEquals(1, repository.findAll().size());
        assertEquals("PORTUGUESA", repository.findAll().get(0).getDescricao());
    }

    @Test
    void shouldHave3Sabores() {
        envSabor.init();
        assertEquals(3, repository.findAll().size());
    }

}
