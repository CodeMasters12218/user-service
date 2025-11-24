package com.example.user.domain.ports;


import com.example.user.domain.model.User;
import java.util.List;

public interface UserServicePort {
    List<User> findAll();
    User findById(Integer id);
    User save(User user);
    User update(Integer id, User user);
    void deleteById(Integer id);
}
