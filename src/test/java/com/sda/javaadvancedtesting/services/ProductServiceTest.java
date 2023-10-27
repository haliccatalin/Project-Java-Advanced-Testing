package com.sda.javaadvancedtesting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.sda.javaadvancedtesting.model.Product;
import com.sda.javaadvancedtesting.repositories.ProductRepository;
import com.sda.javaadvancedtesting.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        // Mock data
        List<Product> productList = Arrays.asList(
                new Product(1L, "Product A", 10.0),
                new Product(2L, "Product B", 15.0)
        );

        // Define the behavior of the repository mock
        when(productRepository.findAll()).thenReturn(productList);

        // Call the service method
        List<Product> result = productService.getAllProducts();

        // Verify the result
        assertEquals(2, result.size());
        assertEquals("Product A", result.get(0).getName());
        assertEquals("Product B", result.get(1).getName());
    }

    @Test
    public void testGetProductById() {
        // Mock data
        Product product = new Product(1L, "Product A", 10.0);

        // Define the behavior of the repository mock
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        // Call the service method
        Optional<Product> result = productService.getProductById(1L);

        // Verify the result
        assertEquals(true, result.isPresent());
        assertEquals("Product A", result.get().getName());
    }

    @Test
    public void testSaveProduct() {
        // Mock data
        Product product = new Product(1L, "Product A", 10.0);

        // Call the service method
        productService.saveProduct(product);

        // Verify that the repository's save method was called
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testDeleteProduct() {
        // Call the service method
        productService.deleteProduct(1L);

        // Verify that the repository's deleteById method was called
        verify(productRepository, times(1)).deleteById(1L);
    }
}
