package ru.smartidea.usersubscriptions.subscription.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.smartidea.usersubscriptions.exception.NotFoundException;
import ru.smartidea.usersubscriptions.subscription.dto.SubscriptionDto;
import ru.smartidea.usersubscriptions.subscription.dto.UpdateSubscriptionDto;
import ru.smartidea.usersubscriptions.subscription.model.Subscription;
import ru.smartidea.usersubscriptions.subscription.repository.SubscriptionRepository;
import ru.smartidea.usersubscriptions.user.repository.UserRepository;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class SubscriptionMapper {

    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;

    public Subscription toSubscription(Long userId, SubscriptionDto subscriptionDto) {
        return Subscription.builder()
                .id(userRepository.count())
                .user(userRepository.getUserById(userId))
                .serviceName(subscriptionDto.getServiceName())
                .serviceUrl(subscriptionDto.getServiceUrl())
                .serviceDescription(subscriptionDto.getServiceDescription())
                .expiresAt(subscriptionDto.getExpiresAt())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public SubscriptionDto toSubscriptionDto(Subscription subscription) {
        return SubscriptionDto.builder()
                .serviceName(subscription.getServiceName())
                .serviceUrl(subscription.getServiceUrl())
                .serviceDescription(subscription.getServiceDescription())
                .expiresAt(subscription.getExpiresAt())
                .build();
    }

    public Subscription toUpdateSubscription(Long userId, Long subscriptionId, UpdateSubscriptionDto updateSubscriptionDto) {
        return Subscription.builder()
                .id(subscriptionRepository.findById(subscriptionId).orElseThrow(
                        () -> new NotFoundException("Подписка с id=" + subscriptionId + " не найдена"))
                        .getId())
                .user(userRepository.getUserById(userId))
                .expiresAt(updateSubscriptionDto.getExpiresAt())
                .build();
    }
}
