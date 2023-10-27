package com.sda.javaadvancedtesting.services;

import com.sda.javaadvancedtesting.model.OrderProduct;
import com.sda.javaadvancedtesting.repositories.OrderProductRepository;

import java.util.List;
import java.util.Optional;

public class OrderProductService {
    private final OrderProductRepository orderProductRepository;

    public OrderProductService(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }


    public List<OrderProduct> getAllOrders() {
        return orderProductRepository.findAll();
    }

    public Optional<OrderProduct> getOrderById(Long id) {
        return orderProductRepository.findById(id);
    }

    public void saveOrder(OrderProduct order) {
        orderProductRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderProductRepository.deleteById(id);
    }
}
