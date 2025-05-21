package ru.smartidea.usersubscriptions.user.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.smartidea.usersubscriptions.user.dto.NewUserDto;
import ru.smartidea.usersubscriptions.user.dto.UpdateUserDto;
import ru.smartidea.usersubscriptions.user.dto.UserDto;
import ru.smartidea.usersubscriptions.user.model.User;
import ru.smartidea.usersubscriptions.user.repository.UserRepository;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class UserMapper {

    private final UserRepository userRepository;

    public User toUser(NewUserDto newUserDto) {
        return User.builder()
                .id(userRepository.count() + 1)
                .name(newUserDto.getName())
                .email(newUserDto.getEmail())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public User toUser(Long userId,UpdateUserDto updateUserDto) {
        return User.builder()
                .id(userRepository.findById(userId).orElseThrow().getId())
                .name(updateUserDto.getName())
                .email(updateUserDto.getEmail())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public UserDto toUserDto(User user) {
        return UserDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public User toUser(UserDto userDto) {
        return User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .build();
    }
}
