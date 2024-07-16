package com.stocks.user_service.service;

import com.stocks.user_service.model.Role;
import com.stocks.user_service.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Service layer for managing Role entities.
 */
@Service
public class RoleService {
    
	@Autowired
    private final RoleRepository roleRepository;

    
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    
    
    /**
     * Retrieves the default role "customer" from the role repository.
     * Assumes that "customer" is a predefined role in the database.
     *
     * @return The Role object representing the "customer" role, or null if not found.
     */

	public Role getDefaultRole() {
	    Optional<Role> roleOptional = roleRepository.findById(2);
	    return roleOptional.orElseThrow(() -> new RuntimeException("Default role 'customer' not found."));
	}

    /**
     * Saves a role entity.
     *
     * @param role The role entity to save.
     * @return The saved role entity.
     */
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    /**
     * Retrieves a role entity by its ID.
     *
     * @param roleId The ID of the role to retrieve.
     * @return An Optional containing the role entity if found, otherwise empty.
     */
    public Optional<Role> getRoleById(Integer roleId) {
        return roleRepository.findById(roleId);
    }

    /**
     * Retrieves all role entities.
     *
     * @return A list of all role entities.
     */
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    /**
     * Deletes a role entity by its ID.
     *
     * @param roleId The ID of the role to delete.
     * @return true if deletion was successful, false otherwise.
     */
    public boolean deleteRoleById(Integer roleId) {
        Optional<Role> roleOptional = getRoleById(roleId);
        if (roleOptional.isPresent()) {
            roleRepository.deleteById(roleId);
            return true;  // Deletion successful
        }
        return false;  // Role with given ID not found
    }
    
    /**
     * Updates a role entity.
     *
     * @param roleId The ID of the role to update.
     * @param updatedRole The role entity with updated information.
     * @return An Optional containing the updated role entity if found, otherwise empty.
     */
    public Optional<Role> updateRole(Integer roleId, Role updatedRole) {
        Optional<Role> roleOptional = roleRepository.findById(roleId);
        if (roleOptional.isPresent()) {
            Role existingRole = roleOptional.get();
            existingRole.setRoleName(updatedRole.getRoleName());
            // Update other fields as needed

            roleRepository.save(existingRole);
            return Optional.of(existingRole);
        } else {
            return Optional.empty();
        }
    }

}
