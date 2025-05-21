package ru.smartidea.usersubscriptions.subscription.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.smartidea.usersubscriptions.exception.NotFoundException;
import ru.smartidea.usersubscriptions.subscription.dto.SubscriptionDto;
import ru.smartidea.usersubscriptions.subscription.dto.TopSubscriptionDto;
import ru.smartidea.usersubscriptions.subscription.dto.UpdateSubscriptionDto;
import ru.smartidea.usersubscriptions.subscription.mapper.SubscriptionMapper;
import ru.smartidea.usersubscriptions.subscription.model.Subscription;
import ru.smartidea.usersubscriptions.subscription.repository.SubscriptionRepository;
import ru.smartidea.usersubscriptions.user.repository.UserRepository;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final UserRepository userRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final SubscriptionRepository subscriptionRepository;

    /**
     * Создание подписки
     * @param userId id пользователя
     * @param subscriptionDto данные подписки
     * @return SubscriptionDto
     */
    @Override
    public SubscriptionDto createSubscription(Long userId, SubscriptionDto subscriptionDto) {
        isUserExistById(userId);
        Subscription subscription = subscriptionMapper.toSubscription(userId, subscriptionDto);
        log.debug("Подписка добавлена");
        return subscriptionMapper.toSubscriptionDto(subscriptionRepository.save(subscription));
    }

    /**
     * Обновление подписки
     * @param userId id пользователя
     * @param subscriptionId id подписки
     * @param updateSubscriptionDto обновленные данные подписки
     * @return SubscriptionDto
     */
    @Override
    public SubscriptionDto updateSubscription(Long userId, Long subscriptionId, UpdateSubscriptionDto updateSubscriptionDto) {
        log.debug("Получен запрос на обновление даты подписки пользователя");
        Subscription subscription = subscriptionMapper.toUpdateSubscription(userId, subscriptionId,  updateSubscriptionDto);
        log.debug("Подписка обновлена");
        return subscriptionMapper.toSubscriptionDto(subscriptionRepository.save(subscription));
    }


    @Override
    public List<Subscription> getSubscriptionsByUserId(Long userId) {
        log.debug("Получен запрос на получение подписок пользователя");
        isUserExistById(userId);
        log.debug("Подписки пользователя получены");
        return subscriptionRepository.findAllByUserId(userId);
    }

    /**
     * Удаление подписки пользователя
     * @param userId id пользователя
     * @param subscriptionId id подписки
     */
    @Override
    public void deleteSubscription(Long userId, Long subscriptionId) {
        log.debug("Получен запрос на удаление подписки пользователя");
        isUserExistById(userId);
        if (!subscriptionRepository.existsById(subscriptionId)) {
            throw new NotFoundException("Подписка с id=" + subscriptionId + " не найдена");
        }
        List<Subscription> subscriptions = getSubscriptionsByUserId(userId);
        for(Subscription subscription : subscriptions) {
            if (!subscription.getId().equals(subscriptionId)) {
                throw new NotFoundException("Подписка с id=" + subscriptionId + " не найдена у пользователя с id=" + userId);
            }
        }
        log.debug("Подписка удалена");
        subscriptionRepository.deleteById(subscriptionId);
    }

    /**
     * Получение топ-3 подписок
     * @return List<TopSubscriptionDto>
     */
    @Override
    public List<TopSubscriptionDto> getTop3Subscriptions() {
        log.debug("Топ-3 подписок получены");
        return subscriptionRepository.findTop3By();
    }

    // Валидация существования пользователя
    public void isUserExistById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("Пользователь с id=" + id + " не найден");
        }
    }
}
