package com.fantin.pizza.config.inits;

import com.fantin.pizza.domain.services.TamanhoService;
import com.fantin.pizza.domain.type.TypeTamanho;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class InitTamanhos {

    @Autowired
    private TamanhoService service;

    public void init() {
        service.save(TypeTamanho.PEQUENA, 15, BigDecimal.valueOf(20));
        service.save(TypeTamanho.MEDIA, 20, BigDecimal.valueOf(30));
        service.save(TypeTamanho.GRANDE, 25, BigDecimal.valueOf(40));
    }

}
