package com.sda.javaadvancedtesting.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.sda.javaadvancedtesting.model.OrderProduct;
import com.sda.javaadvancedtesting.repositories.OrderProductRepository;
import com.sda.javaadvancedtesting.services.OrderProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class OrderProductServiceTest {

    @Mock
    private OrderProductRepository orderRepository;

    @InjectMocks
    private OrderProductService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllOrders() {
        // Mock data
        List<OrderProduct> orderList = Arrays.asList(
                new OrderProduct(1L, "Order A"),
                new OrderProduct(2L, "Order B")
        );

        // Define the behavior of the repository mock
        when(orderRepository.findAll()).thenReturn(orderList);

        // Call the service method
        List<OrderProduct> result = orderService.getAllOrders();

        // Verify the result
        assertEquals(2, result.size());
        assertEquals("Order A", result.get(0).getName());
        assertEquals("Order B", result.get(1).getName());
    }

    @Test
    public void testGetOrderById() {
        // Mock data
        OrderProduct order = new OrderProduct(1L, "Order A");

        // Define the behavior of the repository mock
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        // Call the service method
        Optional<OrderProduct> result = orderService.getOrderById(1L);

        // Verify the result
        assertTrue(result.isPresent());
        assertEquals("Order A", result.get().getName());
    }

    @Test
    public void testSaveOrder() {
        // Mock data
        OrderProduct order = new OrderProduct(1L, "Order A");

        // Call the service method
        orderService.saveOrder(order);

        // Verify that the repository's save method was called
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    public void testDeleteOrder() {
        // Call the service method
        orderService.deleteOrder(1L);

        // Verify that the repository's deleteById method was called
        verify(orderRepository, times(1)).deleteById(1L);
    }
}
