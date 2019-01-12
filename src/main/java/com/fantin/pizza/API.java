package com.fantin.pizza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.fantin.pizza")
@EnableAutoConfiguration
public class API {

    public static void main(String[] args) {
        SpringApplication.run(API.class);
    }

}
