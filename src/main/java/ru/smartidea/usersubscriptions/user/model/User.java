package ru.smartidea.usersubscriptions.user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.smartidea.usersubscriptions.subscription.model.Subscription;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Size(min = 2, max = 50)
    @Column
    private String name;
    @Size(min = 2, max = 100)
    @Email
    @Column
    private String email;
    @Column
    private LocalDateTime createdAt;
    @Column
    private LocalDateTime updatedAt;
//    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
//    private List<Subscription> subscriptions;
}
