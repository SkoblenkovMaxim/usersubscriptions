package ru.smartidea.usersubscriptions.user.service;

import ru.smartidea.usersubscriptions.user.dto.NewUserDto;
import ru.smartidea.usersubscriptions.user.dto.UpdateUserDto;
import ru.smartidea.usersubscriptions.user.dto.UserDto;

public interface UserService {

    /**
     * Создание пользователя
     * @param newUserDto новый пользователь
     * @return UserDto
     */
    UserDto createUser(NewUserDto newUserDto);

    /**
     * Получение данных о пользователе по его id
     * @param id id пользователя
     * @return UserDto
     */
    UserDto getUserById(Long id);

    /**
     * Удаление пользователя
     * @param id id пользователя
     */
    void deleteUser(Long id);

    /**
     * Обновление данных о пользователе
     * @param id id пользователя
     * @param updateUserDto обновляемые данные
     * @return UserDto
     */
    UserDto updateUser(Long id, UpdateUserDto updateUserDto);

}
