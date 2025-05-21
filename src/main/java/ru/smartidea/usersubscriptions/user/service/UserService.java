package ru.smartidea.usersubscriptions.user.service;

import ru.smartidea.usersubscriptions.user.dto.NewUserDto;
import ru.smartidea.usersubscriptions.user.dto.UpdateUserDto;
import ru.smartidea.usersubscriptions.user.dto.UserDto;

public interface UserService {

    UserDto createUser(NewUserDto newUserDto);

    UserDto getUserById(Long id);

    void deleteUser(Long id);

    UserDto updateUser(Long id, UpdateUserDto updateUserDto);

}
