package com.example.user.domain.model;

import com.example.common.dto.ProductDTO; 
import java.util.List;

public class User {
    private Integer id;
    private String name;
    private List<ProductDTO> products;  

    public User() {}

    public User(Integer id, String name, List<ProductDTO> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

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

    public List<ProductDTO> getProducts() {
        return products;
    }
    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
