package ru.smartidea.usersubscriptions.subscription.service;

import ru.smartidea.usersubscriptions.subscription.dto.SubscriptionDto;
import ru.smartidea.usersubscriptions.subscription.dto.TopSubscriptionDto;
import ru.smartidea.usersubscriptions.subscription.dto.UpdateSubscriptionDto;
import ru.smartidea.usersubscriptions.subscription.model.Subscription;

import java.util.List;

public interface SubscriptionService {

    SubscriptionDto createSubscription(Long userId, SubscriptionDto subscriptionDto);

    SubscriptionDto updateSubscription(Long userId, Long subscriptionId, UpdateSubscriptionDto updateSubscriptionDto);

    List<Subscription> getSubscriptionsByUserId(Long userId);

    void deleteSubscription(Long userId, Long subscriptionId);

    List<TopSubscriptionDto> getTop3Subscriptions();

}
