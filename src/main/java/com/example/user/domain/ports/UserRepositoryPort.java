package com.example.user.domain.ports;


import com.example.user.domain.model.User;
import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {
    List<User> findAll();
    Optional<User> findById(Integer id);
    User save(User user);
    void deleteById(Integer id);
    boolean existsById(Integer id);
}
