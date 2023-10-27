package com.sda.javaadvancedtesting.controllers;

import com.sda.javaadvancedtesting.model.OrderProduct;
import com.sda.javaadvancedtesting.services.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderProducts")
public class OrderProductController {
    @Autowired
    private OrderProductService orderProductService;

    @GetMapping
    public List<OrderProduct> getAllOrderProducts() {
        return orderProductService.getAllOrders();
    }

    @PostMapping
    public OrderProduct createOrderProduct(@RequestBody OrderProduct orderProduct) {
        return orderProductService.saveOrder(orderProduct);
    }
}
