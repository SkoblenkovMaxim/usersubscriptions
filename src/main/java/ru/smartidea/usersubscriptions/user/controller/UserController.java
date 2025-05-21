package ru.smartidea.usersubscriptions.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.smartidea.usersubscriptions.user.dto.NewUserDto;
import ru.smartidea.usersubscriptions.user.dto.UpdateUserDto;
import ru.smartidea.usersubscriptions.user.dto.UserDto;
import ru.smartidea.usersubscriptions.user.service.UserService;

@Slf4j
@RequiredArgsConstructor
@Validated
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    /**
     * Создание пользователя
     * @param newUserDto данные о новом пользователе
     * @return UserDto
     */
    @PostMapping
    public UserDto createUser(@Valid @RequestBody NewUserDto newUserDto) {
        log.info("Получен запрос на добавление пользователя");
        return userService.createUser(newUserDto);
    }

    /**
     * Получение данных о пользователе
     * @param id id пользователя
     * @return UserDto
     */
    @GetMapping("/{id}")
    public UserDto getUserById(@Valid @PathVariable Long id) {
        log.info("Получен запрос на поиск пользователя");
        return userService.getUserById(id);
    }

    /**
     * Удаление пользователя
     * @param id id пользователя
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@Valid @PathVariable Long id) {
        log.info("Получен запрос на удаленипе пользователя");
        userService.deleteUser(id);
    }

    /**
     * Обновление данных о пользователе
     * @param id id пользователя
     * @param updateUserDto обновляемые данные
     * @return UserDto
     */
    @PutMapping("/{id}")
    public UserDto updateUser(@Valid @PathVariable Long id,
                              @Valid @RequestBody UpdateUserDto updateUserDto) {
        log.info("Получен запрос на обновление пользователя");
        return userService.updateUser(id, updateUserDto);
    }
}
