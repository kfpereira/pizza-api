package com.fantin.pizza.environments;

import com.fantin.pizza.config.InitSabores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnvSabor {

    @Autowired
    private InitSabores initSabores;

    public void init() {
        initSabores.init();
    }

}
