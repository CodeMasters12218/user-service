package com.example.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import com.example.user.infrastructure.repository.JpaUserRepository;
import com.example.user.infrastructure.repository.UserEntity;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryCustomTest {

    @Autowired
    private JpaUserRepository userRepository;

    @Test
    void testFindByNameContaining() {
        userRepository.save(new UserEntity(null, "Ismael"));
        userRepository.save(new UserEntity(null, "Isabella"));

        List<UserEntity> users = userRepository.findByNameContaining("Is");

        assertThat(users).hasSize(2);
    }

    @Test
    void testBuscarPorPrefijo() {
        userRepository.save(new UserEntity(null, "Carlos"));
        userRepository.save(new UserEntity(null, "Carmen"));
        userRepository.save(new UserEntity(null, "Ana"));

        List<UserEntity> usersWithPrefix = userRepository.buscarPorPrefijo("Car");

        assertThat(usersWithPrefix).hasSize(2);
    }
}

