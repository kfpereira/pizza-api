package com.fantin.pizza;

import com.fantin.pizza.config.core.FunctionalTest;
import com.fantin.pizza.domain.model.Tamanho;
import com.fantin.pizza.domain.repositories.TamanhoRepository;
import com.fantin.pizza.domain.type.TypeTamanho;
import com.fantin.pizza.environments.EnvTamanho;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@FunctionalTest
class TamanhoTest {

    @Autowired
    private TamanhoRepository repository;

    @Autowired
    private EnvTamanho envTamanho;

    @Test
    void shouldBeOneRecord() {
        Tamanho tamanho = Tamanho.builder()
                .typeTamanho(TypeTamanho.PEQUENA)
                .tempoPreparo(15)
                .valor(BigDecimal.valueOf(20))
                .build();

        repository.saveAndFlush(tamanho);

        assertEquals(1, repository.findAll().size());
    }

    @Test
    void happyDay() {
        envTamanho.init();
        assertEquals(3, repository.findAll().size());
    }
}
