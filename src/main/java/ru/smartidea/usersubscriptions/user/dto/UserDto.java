package ru.smartidea.usersubscriptions.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.smartidea.usersubscriptions.subscription.dto.SubscriptionDto;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private String name;
    private String email;
//    List<Long> subscriptions;
}
