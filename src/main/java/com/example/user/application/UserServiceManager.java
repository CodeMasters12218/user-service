package com.example.user.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.user.domain.model.User;
import com.example.user.domain.ports.UserRepositoryPort;
import com.example.user.domain.ports.UserServicePort;
@Service
public class UserServiceManager implements UserServicePort {

    private final UserRepositoryPort repository;

    public UserServiceManager(UserRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findById(Integer id) {
        return this.repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User update(Integer id, User newUserData) {
        User existingUser = this.repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));

        existingUser.setName(newUserData.getName());

        return repository.save(existingUser);
    }
    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
