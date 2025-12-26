package com.example.user.infrastructure.repository;

import com.example.user.domain.model.User;

public class UserMapper {

    public static User toDomain(UserEntity entity) {
        return new User(
            entity.getId(),
            entity.getName(),
            null,
            entity.getEmail(),
            entity.getPoints()
        );
    }

    public static UserEntity toEntity(User domain) {
        return new UserEntity(
            domain.getId(),
            domain.getName(),
            domain.getEmail(),
            domain.getPoints()
        );
    }
}
