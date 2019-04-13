package ru.bellintegrator.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bellintegrator.domain.User;

public interface UserRepo extends JpaRepository<User, Long>  {
    User findByUsername(String username);
}
