package com.fantin.pizza.environments;

import com.fantin.pizza.config.inits.InitTamanhos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnvTamanho {

    @Autowired
    private InitTamanhos initTamanhos;

    public void init() {
        initTamanhos.init();
    }

}
