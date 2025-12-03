package com.example.user.domain.model;

import com.example.common.dto.ProductDTO;
import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Represents a system user with associated products.")
public class User {
    @Schema(description = "Unique identifier of the user", example = "10")
    private Integer id;

    @Schema(description = "Name of the user", example = "Ismael Muñoz Morales")
    private String name;

    @Schema(description = "List of products associated with the user")
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
