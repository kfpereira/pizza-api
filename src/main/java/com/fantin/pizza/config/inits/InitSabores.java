package com.fantin.pizza.config.inits;

import com.fantin.pizza.domain.services.SaborService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class InitSabores {

    @Autowired
    private SaborService service;

    public void init() {
        service.save("CALABRESA", BigDecimal.ZERO);
        service.save("MARGUERITA", BigDecimal.ZERO);
        service.save("PORTUGUESA", BigDecimal.valueOf(5));
    }

}
