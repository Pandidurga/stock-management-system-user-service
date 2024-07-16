package com.stocks.user_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Represents a User entity that stores user information.
 * This entity is mapped to the 'Users' table in the database.
 */
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId; // Unique identifier for the user entity

    @Column(name = "email" , unique=true,nullable = false)
    private String email; // Email address of the user

    @Column(name = "password",nullable = false)
    private String password; // Password of the user (hashed and stored securely)

    @Column(name = "contact_number",nullable = false)
    private String contactNumber; // Contact number of the user

    @Column(name = "state",nullable = false)
    private String state; // State of the user (if applicable)

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role; // Role assigned to the user, linked via role_id in the database

    @Column(name = "username" , unique=true)
    private String username; // Username chosen by the user for login

    // Constructors, getters, setters, and other methods can be added here.

    /**
     * Getter for userId.
     * @return The userId.
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Setter for userId.
     * @param userId The userId to set.
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * Getter for email.
     * @return The email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for email.
     * @param email The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for password.
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for password.
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Getter for contactNumber.
     * @return The contactNumber.
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * Setter for contactNumber.
     * @param contactNumber The contactNumber to set.
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * Getter for state.
     * @return The state.
     */
    public String getState() {
        return state;
    }

    /**
     * Setter for state.
     * @param state The state to set.
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Getter for role.
     * @return The role.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Setter for role.
     * @param role The role to set.
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Getter for username.
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for username.
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
