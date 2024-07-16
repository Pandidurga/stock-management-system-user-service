package com.stocks.user_service.controller;
import com.stocks.user_service.model.User;
import com.stocks.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-service/users")
public class UserController {
	
	@Autowired
    private final UserService userService;
	

  
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint to sign up a new user.
     *
     * @param user The User object containing user details to create.
     * @return ResponseEntity with a success message and status 201 if user created successfully,
     *         or an error message and status 400 if failed (username or email already exists).
     */
    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody User user) {
        try {
            // Validate if the username or email already exists
            if (userService.existsByUsername(user.getUsername())) {
                return ResponseEntity.badRequest().body("Username already exists");
            }
            if (userService.existsByEmail(user.getEmail())) {
                return ResponseEntity.badRequest().body("Email already exists");
            }
            
            // Save the user and handle the returned createdUser
            User createdUser = userService.saveUser(user);
            
            if (createdUser != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
            } else {
                return ResponseEntity.badRequest().body("Failed to create user");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Failed to create user: " + e.getMessage());
        }
    }

    
    /**
     * Endpoint for user login using email and password.
     *
     * @param email    The email address of the user.
     * @param password The password of the user.
     * @return ResponseEntity with authenticated user information and status 200 if successful,
     * or error message and status 401 if authentication fails.
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String email, @RequestParam String password) {
        try {  
            // Perform authentication logic
            Optional<User> authenticatedUser = userService.authenticateUserByEmail(email, password);
            if (authenticatedUser.isPresent()) {
                return ResponseEntity.ok(authenticatedUser.get());
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Endpoint to retrieve a user by ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return ResponseEntity with retrieved user and status 200 if found, or error message and status 404 if not found.
     */
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Integer id) {
        try {
            Optional<User> userOptional = userService.getUserById(id);
            if (userOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(userOptional.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID " + id + " not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch user: " + e.getMessage());
        }
    }

    /**
     * Endpoint to retrieve a user by username.
     *
     * @param username The unique username of the user to retrieve.
     * @return ResponseEntity with retrieved user and status 200 if found, or error message and status 404 if not found.
     */
    @GetMapping("/get-by-username/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        try {
            return userService.getUserByUsername(username)
                    .map(ResponseEntity.ok()::body)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Endpoint to retrieve a user by email.
     *
     * @param email The email address of the user to retrieve.
     * @return ResponseEntity with retrieved user and status 200 if found, or error message and status 404 if not found.
     */
    @GetMapping("/get-by-email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        try {
            return userService.getUserByEmail(email)
                    .map(ResponseEntity.ok()::body)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Endpoint to retrieve all users.
     *
     * @return ResponseEntity with list of users and status 200 if found, or empty list and status 404 if no users found.
     */
    @GetMapping("/get-all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (!users.isEmpty()) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * Endpoint to delete a user by ID.
     *
     * @param userId The ID of the user to delete.
     * @return ResponseEntity with a success message and status 200 if deleted,
     *         or an error message and status 404 if not found.
     */
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable Integer userId) {
        try {
            boolean isDeleted = userService.deleteUserById(userId);
            if (isDeleted) {
                return ResponseEntity.ok("User deleted successfully with ID: " + userId);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with ID: " + userId);
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to delete user: " + e.getMessage());
        }
     }

    /**
     * Updates a user by ID.
     *
     * @param userId The ID of the user to update.
     * @param updatedUser The user entity with updated information.
     * @return ResponseEntity containing the updated user or a descriptive error message.
     */
    @PutMapping("/update/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable Integer userId, @RequestBody User updatedUser) {
        try {
            Optional<User> userOptional = userService.updateUser(userId, updatedUser);
            if (userOptional.isPresent()) {
                return ResponseEntity.ok("User updated successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user: " + e.getMessage());
        }
    }
    
}
