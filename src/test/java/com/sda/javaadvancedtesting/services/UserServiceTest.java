package com.sda.javaadvancedtesting;
import com.sda.javaadvancedtesting.model.User;
import com.sda.javaadvancedtesting.repositories.UserRepository;
import com.sda.javaadvancedtesting.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        // Mock data
        List<User> userList = Arrays.asList(
                new User(1L, "John Doe", "test@test.com"),
                new User(2L, "Jane Doe", "test@test.com")
        );

        // Define the behavior of the repository mock
        when(userRepository.findAll()).thenReturn(userList);

        // Call the service method
        List<User> result = userService.getAllUsers();

        // Verify the result
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getFullName());
        assertEquals("Jane Doe", result.get(1).getFullName());
    }

    @Test
    public void testGetUserById() {
        // Mock data
        User user = new User(1L, "John Doe", "test@test.com");

        // Define the behavior of the repository mock
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Call the service method
        Optional<User> result = userService.getUserById(1L);

        // Verify the result
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getFullName());
    }

    @Test
    public void testSaveUser() {
        // Mock data
        User user = new User(1L, "John Doe","test@test.com");

        // Call the service method
        userService.saveUser(user);

        // Verify that the repository's save method was called
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testDeleteUser() {
        // Call the service method
        userService.deleteUser(1L);

        // Verify that the repository's deleteById method was called
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testGetUserByFullName() {
        // Mock data
        User user = new User(1L, "John Doe", "test@test.com");

        // Define the behavior of the repository mock
        when(userRepository.findByFullName("John Doe")).thenReturn(Optional.of(user));

        // Call the service method
        Optional<User> result = userService.getUserByFullName("John Doe");

        // Verify the result
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getFullName());
    }

    @Test
    public void testGetUserByNonexistentFullName() {
        // Define the behavior of the repository mock
        when(userRepository.findByFullName("Nonexistent User")).thenReturn(Optional.empty());

        // Call the service method
        Optional<User> result = userService.getUserByFullName("Nonexistent User");

        // Verify the result
        assertFalse(result.isPresent());
    }
}