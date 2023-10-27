package com.sda.javaadvancedtesting.repositories;

import com.sda.javaadvancedtesting.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
