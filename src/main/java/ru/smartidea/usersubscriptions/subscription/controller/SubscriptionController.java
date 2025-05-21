package ru.smartidea.usersubscriptions.subscription.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.smartidea.usersubscriptions.subscription.dto.SubscriptionDto;
import ru.smartidea.usersubscriptions.subscription.dto.TopSubscriptionDto;
import ru.smartidea.usersubscriptions.subscription.dto.UpdateSubscriptionDto;
import ru.smartidea.usersubscriptions.subscription.model.Subscription;
import ru.smartidea.usersubscriptions.subscription.service.SubscriptionService;

import java.util.List;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping("/users/{userId}/subscriptions")
    public SubscriptionDto createSubscription(@Valid @PathVariable Long userId,
                                              @Valid @RequestBody SubscriptionDto subscriptionDto) {
        log.debug("Получен запрос на добавление подписки");
        return subscriptionService.createSubscription(userId, subscriptionDto);
    }

    @PutMapping("/users/{userId}/subscriptions/{subscriptionId}")
    public SubscriptionDto updateSubscription(@Valid @PathVariable Long userId,
                                              @Valid @PathVariable Long subscriptionId,
                                              @Valid @RequestBody UpdateSubscriptionDto updateSubscriptionDto) {
        log.debug("Получен запрос на обновление подписки");
        return subscriptionService.updateSubscription(userId, subscriptionId, updateSubscriptionDto);
    }

    @GetMapping("/users/{userId}/subscriptions")
    public List<Subscription> getSubscriptionsByUserId(@Valid @PathVariable Long userId) {
        log.debug("Получен запрос на получение подписок пользователя");
        return subscriptionService.getSubscriptionsByUserId(userId);
    }

    @DeleteMapping("/users/{userId}/subscriptions/{subscriptionId}")
    public void deleteSubscription(@Valid @PathVariable Long userId,
                                   @Valid @PathVariable Long subscriptionId) {
        log.debug("Получен запрос на удаление подписки пользователя");
        subscriptionService.deleteSubscription(userId, subscriptionId);
    }

    @GetMapping("/subscriptions/top")
    public List<TopSubscriptionDto> getTop3Subscriptions() {
        log.debug("Получен запрос на получение топ-3 подписок");
        return subscriptionService.getTop3Subscriptions();
    }
}
