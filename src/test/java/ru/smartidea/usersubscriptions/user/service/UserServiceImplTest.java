package ru.smartidea.usersubscriptions.user.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.smartidea.usersubscriptions.user.dto.NewUserDto;
import ru.smartidea.usersubscriptions.user.dto.UpdateUserDto;
import ru.smartidea.usersubscriptions.user.dto.UserDto;
import ru.smartidea.usersubscriptions.user.repository.UserRepository;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserRepository userRepository;
    private NewUserDto user1;
    private NewUserDto user2existEmail;
    private UpdateUserDto updateUserDto;

    @BeforeEach
    void setUp() {
        user1 = NewUserDto.builder()
                .name("User1")
                .email("user1@test.com")
                .createdAt(LocalDateTime.of(2025, 1, 1, 0, 0, 0))
                .build();
        user2existEmail = NewUserDto.builder()
                .name("User2")
                .email("user1@test.com")
                .createdAt(LocalDateTime.of(2025, 1, 2, 0, 0, 0))
                .build();
        updateUserDto = UpdateUserDto.builder()
                .name("User2")
                .email("user2@test.com")
                .build();
    }

    @Test
    void createUser() {
        userService.createUser(user1);
        assertEquals(1, userRepository.findAll().size());
        assertEquals(user1.getEmail(), userRepository.findAll().get(0).getEmail());
        assertEquals(user1.getName(), userRepository.findAll().get(0).getName());
    }

    @Test
    void createUser_shouldThrowException() {
        userService.createUser(user1);
        assertThrows(IllegalArgumentException.class, () -> userService.createUser(user2existEmail));
    }

    @Test
    void getUserById() {
        userService.createUser(user1);
        UserDto userDto = userService.getUserById(1L);
        assertEquals(user1.getEmail(), userDto.getEmail());
        assertEquals(user1.getName(), userDto.getName());
    }

    @Test
    void deleteUser() {
        userService.createUser(user1);
        userService.deleteUser(1L);
        assertEquals(0, userRepository.findAll().size());
    }

    @Test
    void updateUser() {
        userService.createUser(user1);
        UserDto userDto = userService.updateUser(1L, updateUserDto);
        assertEquals(updateUserDto.getEmail(), userDto.getEmail());
        assertEquals(updateUserDto.getName(), userDto.getName());
    }
}