package com.stocks.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stocks.user_service.model.User;

import java.util.Optional;

/**
 * Repository interface for managing User entities.
 * Provides CRUD operations and custom queries for User entities.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Retrieves a User entity by its username.
     *
     * @param username The unique username of the user to retrieve.
     * @return An Optional containing the User entity if found, otherwise empty.
     */
    Optional<User> findByUsername(String username);

    /**
     * Retrieves a User entity by its email address.
     *
     * @param email The unique email address of the user to retrieve.
     * @return An Optional containing the User entity if found, otherwise empty.
     */
    Optional<User> findByEmail(String email);

    /**
     * Checks if a user with the given username exists.
     *
     * @param username The username to check.
     * @return True if a user with the username exists, otherwise false.
     */
    boolean existsByUsername(String username);

    /**
     * Checks if a user with the given email exists.
     *
     * @param email The email address to check.
     * @return True if a user with the email exists, otherwise false.
     */
    boolean existsByEmail(String email);

    // Other custom queries and methods can be added as needed
}
