package se.nordflow.auth.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.nordflow.auth.user.model.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository <User, UUID>{
    Boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}
