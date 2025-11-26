package com.example.user.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Integer> {
    List<UserEntity> findByName(String name);

    List<UserEntity> findByNameContaining(String fragment);

    @Query("SELECT u FROM UserEntity u WHERE u.name LIKE CONCAT(:prefix, '%')")
    List<UserEntity> buscarPorPrefijo(@Param("prefix") String prefix);
}
