package com.fantin.pizza;


import com.fantin.pizza.controller.HomeController;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class HomeControllerTest {

    @Test
    void happyDay() {
        HomeController homeController = new HomeController();
        assertEquals("redirect:swagger-ui.html", homeController.home());
    }

}
