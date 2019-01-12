package com.fantin.pizza.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
class EnvironmentReader {

    private static final String AMBIENTE_DE_TESTES = "test";

    @Autowired
    private Environment environment;

    boolean isAmbienteDeTeste() {
        String[] activeProfiles = environment.getActiveProfiles();
        return activeProfiles[0].equals(AMBIENTE_DE_TESTES);
    }
}
