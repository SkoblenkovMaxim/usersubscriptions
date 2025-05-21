package ru.smartidea.usersubscriptions.subscription.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import ru.smartidea.usersubscriptions.user.model.User;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subscriptions")
@Data
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column
    private String serviceName;
    @URL
    @Column
    private String serviceUrl;
    @Column
    private String serviceDescription;
    @Column
    private LocalDateTime expiresAt;
    @Column
    private LocalDateTime createdAt;
}
