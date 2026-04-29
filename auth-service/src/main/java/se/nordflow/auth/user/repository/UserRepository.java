package se.nordflow.auth.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.nordflow.auth.user.model.user;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository <user, UUID>{
    Boolean existsByEmail(String email);
}
