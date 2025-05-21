package ru.smartidea.usersubscriptions.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.smartidea.usersubscriptions.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getUserById(Long id);

    User getUserByEmail(String email);

    boolean existsUserByEmail(String email);
}
