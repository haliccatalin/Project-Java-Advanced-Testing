package com.sda.javaadvancedtesting.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.javaadvancedtesting.model.OrderProduct;
import com.sda.javaadvancedtesting.services.OrderProductService;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.*;

@WebMvcTest(OrderProductController.class)
public class OrderProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderProductService orderProductService;

    @Test
    public void testGetAllOrderProducts() throws Exception {
        List<OrderProduct> orderProducts = Arrays.asList(
                new OrderProduct(1L, "coffee"),
                new OrderProduct(2L, "cola")
        );

        Mockito.when(orderProductService.getAllOrders()).thenReturn(orderProducts);

        mockMvc.perform(MockMvcRequestBuilders.get("/orderProducts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", IsCollectionWithSize.hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("coffee"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("cola"));
    }

    @Test
    public void testCreateOrderProduct() throws Exception {
        OrderProduct orderProduct = new OrderProduct(null,"cola");

        Mockito.when(orderProductService.saveOrder(Mockito.any(OrderProduct.class))).thenReturn(orderProduct);

        mockMvc.perform(post("/orderProducts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(orderProduct)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("cola"));
    }
}