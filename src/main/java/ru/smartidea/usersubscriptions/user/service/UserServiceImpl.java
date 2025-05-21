package ru.smartidea.usersubscriptions.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.smartidea.usersubscriptions.exception.NotFoundException;
import ru.smartidea.usersubscriptions.user.dto.NewUserDto;
import ru.smartidea.usersubscriptions.user.dto.UpdateUserDto;
import ru.smartidea.usersubscriptions.user.dto.UserDto;
import ru.smartidea.usersubscriptions.user.mapper.UserMapper;
import ru.smartidea.usersubscriptions.user.model.User;
import ru.smartidea.usersubscriptions.user.repository.UserRepository;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * Создание пользователя
     * @param newUserDto новый пользователь
     * @return UserDto
     */
    @Override
    public UserDto createUser(NewUserDto newUserDto) {
        log.debug("Получен запрос на добавление нового пользователя");
        if (userRepository.existsUserByEmail(newUserDto.getEmail())) {
            throw new IllegalArgumentException("Пользователь с email: " + newUserDto.getEmail() + " уже существует");
        }
        return userMapper.toUserDto(userRepository.save(userMapper.toUser(newUserDto)));
    }

    /**
     * Получение данных о пользователе по его id
     * @param id id пользователя
     * @return UserDto
     */
    @Override
    public UserDto getUserById(Long id) {
        log.debug("Получен запрос на поиск пользователя по id");
        if (isUserExistById(id)) {
            return userMapper.toUserDto(userRepository.getUserById(id));
        }
        throw new NotFoundException("Пользователь с id=" + id + " не найден");
    }

    /**
     * Удаление пользователя
     * @param id польователя
     */
    @Override
    public void deleteUser(Long id) {
        log.debug("Получен запрос на удаление пользователя");
        if (isUserExistById(id)) {
            log.info("Пользователь удален");
            userRepository.deleteById(id);
        } else {
            log.info("Пользователь не найден или недоступен");
            throw new NotFoundException("Пользователь с id=" + id + " не найден");
        }
    }

    /**
     * Обновление данных о пользователе
     * @param id пользователя
     * @param updateUserDto обновляемые данные
     * @return UserDto обновленного пользователя
     */
    @Override
    public UserDto updateUser(Long id, UpdateUserDto updateUserDto) {
        log.debug("Обновление пользователя с id={}", id);
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("Пользователь с id=" + id + " не найден");
        }

        User user = userMapper.toUser(id, updateUserDto);
        user.setId(id);

        if (updateUserDto.getName() != null) {
            user.setName(updateUserDto.getName());
        }
        if (updateUserDto.getEmail() != null) {
            if (!userRepository.existsUserByEmail(user.getEmail())) {
                throw new IllegalArgumentException("Пользователь с email: " + user.getEmail() + " уже существует");
            }
            user.setEmail(updateUserDto.getEmail());
        }
        user.setUpdatedAt(LocalDateTime.now());

        log.info("Данные пользователя обновлены");
        return userMapper.toUserDto(userRepository.save(user));
    }

    // Проверка наличия пользователя с указанным id
    boolean isUserExistById(Long id) {
        return userRepository.existsById(id);
    }
}
