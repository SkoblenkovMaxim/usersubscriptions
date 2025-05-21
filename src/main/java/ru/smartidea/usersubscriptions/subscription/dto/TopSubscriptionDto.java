package ru.smartidea.usersubscriptions.subscription.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TopSubscriptionDto {
    private String serviceName;
    private Long count;
}
