package ru.smartidea.usersubscriptions.subscription.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.smartidea.usersubscriptions.subscription.dto.TopSubscriptionDto;
import ru.smartidea.usersubscriptions.subscription.model.Subscription;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    List<Subscription> findAllByUserId(Long userId);

    @Query("SELECT new ru.smartidea.usersubscriptions.subscription.dto.TopSubscriptionDto(s.serviceName, COUNT(s.id)) FROM Subscription s GROUP BY s.serviceName ORDER BY COUNT(s.id) DESC")
    List<TopSubscriptionDto> findTop3By();
}
