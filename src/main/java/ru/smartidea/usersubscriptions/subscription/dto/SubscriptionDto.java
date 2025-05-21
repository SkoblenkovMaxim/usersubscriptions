package ru.smartidea.usersubscriptions.subscription.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscriptionDto {
    private String serviceName;
    private String serviceUrl;
    private String serviceDescription;
    private LocalDateTime expiresAt;
}
