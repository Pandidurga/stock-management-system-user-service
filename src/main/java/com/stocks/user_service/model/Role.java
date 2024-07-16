package com.stocks.user_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a Role entity that defines user roles in the application.
 * This entity is mapped to the 'Roles' table in the database.
 */
@Entity
@Table(name = "Roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId; // Unique identifier for the role entity

    @Column(name = "role_name", nullable = false, unique = true)
    private String roleName; // Name of the role, cannot be null and must be unique

    /**
     * Default constructor for Role.
     * Initializes an empty Role object.
     */
    public Role() {
    }

    /**
     * Constructor for Role with roleName parameter.
     * Initializes a Role object with the specified roleName.
     *
     * @param roleName The name of the role.
     */
    public Role(String roleName) {
        this.roleName = roleName;
    }

    /**
     * Retrieves the role ID.
     *
     * @return The role ID.
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * Sets the role ID.
     *
     * @param roleId The role ID to set.
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * Retrieves the role name.
     *
     * @return The role name.
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Sets the role name.
     *
     * @param roleName The role name to set.
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    /**
     * Returns a string representation of the Role object.
     *
     * @return A string representation including roleId and roleName.
     */
    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }

}
