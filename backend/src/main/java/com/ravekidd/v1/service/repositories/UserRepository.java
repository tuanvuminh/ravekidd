package com.ravekidd.v1.service.repositories;

import com.ravekidd.v1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for accessing and managing User entities in the database.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsernameIn(List<String> usernames);
    List<User> findUserById(Long id);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
}

