package com.stocks.user_service.controller;

import com.stocks.user_service.model.Role;
import com.stocks.user_service.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-service/roles")
public class RoleController {

    @Autowired
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * Endpoint to create a new role.
     *
     * @param role The role entity to create.
     * @return ResponseEntity with a success message and status 201 if successful,
     *         or an error message and status 400 if failed.
     */
    @PostMapping("/add")
    public ResponseEntity<?> createRole(@RequestBody Role role) {
        try {
            Role createdRole = roleService.saveRole(role);
            return ResponseEntity.status(HttpStatus.CREATED).body("Role created successfully: " + createdRole);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Failed to create role: " + e.getMessage());
        }
    }

    /**
     * Endpoint to retrieve a role by ID.
     *
     * @param roleId The ID of the role to retrieve.
     * @return ResponseEntity with the retrieved role and status 200 if found,
     *         or an error message and status 404 if not found.
     */
    @GetMapping("/get-by-id/{roleId}")
    public ResponseEntity<?> getRoleById(@PathVariable Integer roleId) {
        try {
            Optional<Role> role = roleService.getRoleById(roleId);
            return role.map(r -> ResponseEntity.ok("Role found: " + r))
                       .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role not found with ID: " + roleId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Failed to retrieve role: " + e.getMessage());
        }
    }

    /**
     * Endpoint to retrieve all roles.
     *
     * @return ResponseEntity with the list of roles and status 200 if found,
     *         or an error message and status 404 if no roles found.
     */
    @GetMapping("/get-all")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        if (!roles.isEmpty()) {
            return ResponseEntity.ok(roles);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
    /**
     * Endpoint to delete a role by ID.
     *
     * @param roleId The ID of the role to delete.
     * @return ResponseEntity with a success message and status 200 if deleted,
     *         or an error message and status 404 if not found.
     */
    @DeleteMapping("/delete/{roleId}")
    public ResponseEntity<?> deleteRoleById(@PathVariable Integer roleId) {
        try {
            boolean deleted = roleService.deleteRoleById(roleId);
            if (deleted) {
                return ResponseEntity.ok("Role deleted successfully with ID: " + roleId);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role not exist with role ID: " + roleId);
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to delete role with ID: " + roleId + ". " + e.getMessage());
        }
    }
    
   
    /**
     * Updates a role by ID.
     *
     * @param roleId The ID of the role to update.
     * @param updatedRole The role entity with updated information.
     * @return ResponseEntity containing the updated role or a descriptive error message.
     */
    @PutMapping("/update/{roleId}")
    public ResponseEntity<String> updateRole(@PathVariable Integer roleId, @RequestBody Role updatedRole) {
        try {
            Optional<Role> roleOptional = roleService.updateRole(roleId, updatedRole);
            if (roleOptional.isPresent()) {
                return ResponseEntity.ok("Role updated successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating role: " + e.getMessage());
        }
    }


   }
