package ru.smartidea.usersubscriptions.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateUserDto {
    private String name;
    private String email;
    private LocalDateTime updatedAt;
//    private List<Long> subscriptions;
}
