package com.fantin.pizza.config;

import com.fantin.pizza.repositories.SaborRepository;
import com.fantin.pizza.repositories.TamanhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private InitSabores initSabores;

    @Autowired
    private InitTamanhos initTamanhos;

    @Autowired
    private SaborRepository saborRepository;

    @Autowired
    private TamanhoRepository tamanhoRepository;

    @Autowired
    private EnvironmentReader environmentReader;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (!environmentReader.isAmbienteDeTeste()) {
            if (saborRepository.findAll().isEmpty())
                initSabores.init();
            if (tamanhoRepository.findAll().isEmpty())
                initTamanhos.init();
        }
    }
}
