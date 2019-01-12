package com.fantin.pizza.controller;

import com.fantin.pizza.config.exceptions.DataRequiredException;
import com.fantin.pizza.config.exceptions.RecordNotFoundException;
import com.fantin.pizza.services.FirstStepService;
import com.fantin.pizza.viewer.FindFirstStepVM;
import com.fantin.pizza.viewer.FirstStepOutVM;
import com.fantin.pizza.viewer.FirstStepVM;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(tags = { "ordersController" })
@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private FirstStepService firstStepService;

    @GetMapping
    public FindFirstStepVM getData(HttpServletRequest request) {
        return firstStepService.getView();
    }

    @PostMapping
    public FirstStepOutVM postOrder(HttpServletRequest request, FirstStepVM firstStepVM) throws RecordNotFoundException, DataRequiredException {
        return firstStepService.executeOrder(firstStepVM);
    }

}
