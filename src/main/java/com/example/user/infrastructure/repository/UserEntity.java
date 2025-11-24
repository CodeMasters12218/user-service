package com.example.user.infrastructure.repository;

import jakarta.persistence.*;
import java.util.ArrayList;
import com.example.common.dto.ProductDTO; 
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public UserEntity() {}

    public UserEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
