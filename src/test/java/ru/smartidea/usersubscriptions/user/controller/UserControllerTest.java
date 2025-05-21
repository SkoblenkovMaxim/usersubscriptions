package ru.smartidea.usersubscriptions.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.smartidea.usersubscriptions.user.dto.NewUserDto;
import ru.smartidea.usersubscriptions.user.dto.UpdateUserDto;
import ru.smartidea.usersubscriptions.user.dto.UserDto;
import ru.smartidea.usersubscriptions.user.mapper.UserMapper;
import ru.smartidea.usersubscriptions.user.service.UserService;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
class UserControllerTest {

    @MockitoBean
    private UserService userService;
    @InjectMocks
    private UserController userController;
    @MockitoBean
    private UserMapper userMapper;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    private NewUserDto newUserDto;
    private UpdateUserDto updateUserDto;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        newUserDto = NewUserDto.builder()
                .name("User1")
                .email("user1@test.com")
                .createdAt(LocalDateTime.of(2025, 1, 1, 0, 0, 0))
                .build();

        updateUserDto = UpdateUserDto.builder()
                .name("User2")
                .email("user2@test.com")
                .build();

        userDto = UserDto.builder()
                .name("User1")
                .email("user1@test.com")
                .build();
    }

    @Test
    void createUser() throws Exception {
        when(userService.createUser(any())).thenReturn(userDto);

        mockMvc.perform(post("/users")
                    .characterEncoding(StandardCharsets.UTF_8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(newUserDto)))
                .andExpect(status().isOk())
                .andExpect(result -> assertEquals(
                        objectMapper.writeValueAsString(userDto),
                        result.getResponse().getContentAsString()));
    }

    @Test
    void getUserById() throws Exception {
        when(userService.getUserById(any())).thenReturn(userDto);
        mockMvc.perform(get("/users/1")
                    .characterEncoding(StandardCharsets.UTF_8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(result -> assertEquals(
                        objectMapper.writeValueAsString(userDto),
                        result.getResponse().getContentAsString()));
    }

    @Test
    void deleteUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/1"))
                .andExpect(status().isOk());
    }

    @Test
    void updateUser() throws Exception {
        when(userService.updateUser(any(), any())).thenReturn(userDto);
        mockMvc.perform(MockMvcRequestBuilders.put("/users/1")
                    .characterEncoding(StandardCharsets.UTF_8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(updateUserDto)))
                .andExpect(status().isOk());
    }
}