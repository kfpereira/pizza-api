package com.fantin.pizza;

import com.fantin.pizza.config.core.FunctionalTest;
import com.fantin.pizza.environments.EnvOrder;
import com.fantin.pizza.repositories.OrderRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@FunctionalTest
class PedidoTest {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private EnvOrder envOrder;

    @Test
    void happyDay() {
        envOrder.init();
        Assert.assertEquals(3, repository.findAll().size());
    }
}
