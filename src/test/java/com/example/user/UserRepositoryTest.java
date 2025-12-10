package com.example.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.user.infrastructure.repository.JpaUserRepository;
import com.example.user.infrastructure.repository.UserEntity;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private JpaUserRepository userRepository;

    @Test
    void testSaveAndFindById() {
        UserEntity user = new UserEntity();
        user.setName("Ismael");

        UserEntity saved = userRepository.save(user);

        assertThat(saved.getId()).isNotNull();

        UserEntity found = userRepository.findById(saved.getId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("Ismael");
    }
}
