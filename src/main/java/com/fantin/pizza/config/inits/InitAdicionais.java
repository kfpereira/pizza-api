package com.fantin.pizza.config.inits;

import com.fantin.pizza.domain.services.AdicionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.fantin.pizza.domain.type.TypePersonalizacao.*;

@Component
public class InitAdicionais {

    @Autowired
    private AdicionalService service;

    public void init() {
        service.save(EXTRA_BACON, 0, BigDecimal.valueOf(3));
        service.save(SEM_CEBOLA, 0, BigDecimal.ZERO);
        service.save(BORDA_RECHEADA, 5, BigDecimal.valueOf(5));
    }

}
