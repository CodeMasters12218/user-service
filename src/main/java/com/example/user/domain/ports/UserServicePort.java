package com.example.user.domain.ports;


import com.example.user.domain.model.User;
import com.example.user.infrastructure.repository.UserEntity;

import java.util.List;

public interface UserServicePort {
    List<User> findAll();
    User findById(Integer id);
    User save(User user);
    User update(Integer id, User user);
    void deleteById(Integer id);
    List<UserEntity> findByName(String nombre);
    List<UserEntity> findByNameContaining(String fragment);
    List<UserEntity> buscarPorPrefijo(String prefix);
}
