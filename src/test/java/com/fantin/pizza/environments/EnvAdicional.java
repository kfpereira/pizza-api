package com.fantin.pizza.environments;

import com.fantin.pizza.config.inits.InitAdicionais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnvAdicional {

    @Autowired
    private InitAdicionais initAdicionais;

    public void init() {
        initAdicionais.init();
    }

}
