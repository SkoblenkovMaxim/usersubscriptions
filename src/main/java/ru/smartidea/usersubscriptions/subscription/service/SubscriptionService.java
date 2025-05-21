package ru.smartidea.usersubscriptions.subscription.service;

import ru.smartidea.usersubscriptions.subscription.dto.SubscriptionDto;
import ru.smartidea.usersubscriptions.subscription.dto.TopSubscriptionDto;
import ru.smartidea.usersubscriptions.subscription.dto.UpdateSubscriptionDto;
import ru.smartidea.usersubscriptions.subscription.model.Subscription;

import java.util.List;

public interface SubscriptionService {

    /**
     * Создание подписки
     * @param userId id пользователя
     * @param subscriptionDto данные подписки
     * @return SubscriptionDto
     */
    SubscriptionDto createSubscription(Long userId, SubscriptionDto subscriptionDto);

    /**
     * Обновление подписки
     * @param userId id пользователя
     * @param subscriptionId id подписки
     * @param updateSubscriptionDto обновленные данные подписки
     * @return SubscriptionDto
     */
    SubscriptionDto updateSubscription(Long userId, Long subscriptionId, UpdateSubscriptionDto updateSubscriptionDto);

    /**
     * Получение списка подписок пользователя
     * @param userId id пользователя
     * @return List<Subscription>
     */
    List<Subscription> getSubscriptionsByUserId(Long userId);

    /**
     * Удаление подписки пользователя
     * @param userId id пользователя
     * @param subscriptionId id подписки
     */
    void deleteSubscription(Long userId, Long subscriptionId);

    /**
     * Получение топ-3 подписок
     * @return List<TopSubscriptionDto>
     */
    List<TopSubscriptionDto> getTop3Subscriptions();

}
