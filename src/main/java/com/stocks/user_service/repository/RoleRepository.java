package com.stocks.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.stocks.user_service.model.Role;

/**
 * Repository interface for managing Role entities.
 * Provides CRUD operations and custom queries for Role entities.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    // Custom query methods can be defined here if needed

}
