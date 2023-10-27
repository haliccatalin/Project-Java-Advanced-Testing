package com.sda.javaadvancedtesting.services;

import com.sda.javaadvancedtesting.model.OrderProduct;
import com.sda.javaadvancedtesting.repositories.OrderProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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

    public OrderProduct saveOrder(OrderProduct order) {
        return orderProductRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderProductRepository.deleteById(id);
    }
}
