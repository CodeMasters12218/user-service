package com.example.user;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.user.application.UserServiceManager;
import com.example.user.domain.model.User;
import com.example.user.domain.ports.UserRepositoryPort;
import com.example.user.infrastructure.repository.JpaUserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceManagerTest {

    @Mock
    private UserRepositoryPort repository;

    @Mock
    private JpaUserRepository jpaRepository;

    @InjectMocks
    private UserServiceManager service;

    @Test
    void testUpdateUser() {
        // Arrange
        User existingUser = new User();
        existingUser.setId(1);
        existingUser.setName("Alice");

        User newUserData = new User();
        newUserData.setName("Bob");

        when(repository.findById(1)).thenReturn(Optional.of(existingUser));
        when(repository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        User updatedUser = service.update(1, newUserData);

        // Assert
        assertEquals("Bob", updatedUser.getName());
        verify(repository).findById(1);
        verify(repository).save(existingUser);
    }
}

