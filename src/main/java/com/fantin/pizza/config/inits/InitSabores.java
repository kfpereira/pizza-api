package com.fantin.pizza.config.inits;

import com.fantin.pizza.domain.services.SaborService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitSabores {

    @Autowired
    private SaborService service;

    public void init() {
        service.save("CALABRESA", 0);
        service.save("MARGUERITA", 0);
        service.save("PORTUGUESA", 5);
    }

}
