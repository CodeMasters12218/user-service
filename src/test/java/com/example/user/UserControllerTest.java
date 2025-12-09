package com.example.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.user.domain.model.User;
import com.example.user.infrastructure.repository.UserEntity;
import com.example.user.domain.ports.UserServicePort;
import com.example.user.infrastructure.controller.UserController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import java.util.Collections;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServicePort userService;

    @Test
    void testGetUserById() throws Exception {
        User user = new User();
        user.setId(1);
        user.setName("Alice");

        when(userService.findById(1)).thenReturn(user);

        mockMvc.perform(get("/api/users/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(1))
               .andExpect(jsonPath("$.name").value("Alice"));
    }

    @Test
    void testCreateUser() throws Exception {
        User user = new User();
        user.setId(1);
        user.setName("Alice");

        when(userService.save(any(User.class))).thenReturn(user);

        String jsonPayload = """
            {
              "id": 1,
              "name": "Alice"
            }
            """;

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(1))
               .andExpect(jsonPath("$.name").value("Alice"));
    }

    @Test
    void testUpdateUser() throws Exception {
        User updatedUser = new User();
        updatedUser.setId(1);
        updatedUser.setName("Bob");

        when(userService.update(eq(1), any(User.class))).thenReturn(updatedUser);

        String jsonPayload = """
            {
              "id": 1,
              "name": "Bob"
            }
            """;

        mockMvc.perform(put("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("Bob"));
    }

    @Test
    void testDeleteUser() throws Exception {
        doNothing().when(userService).deleteById(1);

        mockMvc.perform(delete("/api/users/1"))
               .andExpect(status().isNoContent());

        verify(userService).deleteById(1);
    }

    @Test
    void testGetAllUsers() throws Exception {
        User user = new User();
        user.setId(1);
        user.setName("Alice");

        when(userService.findAll()).thenReturn(Collections.singletonList(user));

        mockMvc.perform(get("/api/users"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].name").value("Alice"));
    }

    @Test
    void testGetUsersByExactName() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setName("Ismael");

        when(userService.findByName("Ismael")).thenReturn(Collections.singletonList(userEntity));

        mockMvc.perform(get("/api/users/nombre/Ismael"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].name").value("Ismael"));
    }

    @Test
    void testGetUsersByNameContaining() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setName("Ismael Muñoz");

        when(userService.findByNameContaining("ism")).thenReturn(Collections.singletonList(userEntity));

        mockMvc.perform(get("/api/users/contiene/ism"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].name").value("Ismael Muñoz"));
    }

    @Test
    void testGetUsersByPrefix() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setName("Ismael");

        when(userService.buscarPorPrefijo("Is")).thenReturn(Collections.singletonList(userEntity));

        mockMvc.perform(get("/api/users/prefijo/Is"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].name").value("Ismael"));
    }
}
