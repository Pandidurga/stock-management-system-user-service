package com.stocks.user_service.service;
import com.stocks.user_service.model.User;
import com.stocks.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Service layer for managing User entities.
 */
@Service
public class UserService {
    
    @Autowired
    private final UserRepository userRepository;
    
    @Autowired
    private RoleService roleService;

    public UserService(UserRepository userRepository,RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }
    
    // Inject RoleService to fetch roles

    public User saveUser(User user) {
        // Assign a default role for new users (e.g., "customer")
        user.setRole(roleService.getDefaultRole()); // Modify to fetch the appropriate role

        // Save the user
        return userRepository.save(user);
    }
    
    

    /**
     * Retrieves a user entity by its ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return An Optional containing the user entity if found, otherwise empty.
     */
    public Optional<User> getUserById(Integer userId) {
        return userRepository.findById(userId);
    }

    /**
     * Retrieves a user entity by its username.
     *
     * @param username The unique username of the user to retrieve.
     * @return An Optional containing the user entity if found, otherwise empty.
     */
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Retrieves all user entities.
     *
     * @return A list of all user entities.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Deletes a user entity by its ID.
     *
     * @param userId The ID of the user to delete.
     * @return True if the user was deleted, false if the user did not exist.
     */
    public boolean deleteUserById(Integer userId) {
       
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                userRepository.deleteById(userId);
                return true;
            } else {
                return false;
            }
    }

    /**
     * Checks if a user with the given username exists.
     *
     * @param username The username to check.
     * @return True if a user with the username exists, otherwise false.
     */
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    /**
     * Retrieves a user entity by its email address.
     *
     * @param email The email address of the user to retrieve.
     * @return An Optional containing the user entity if found, otherwise empty.
     */
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Checks if a user with the given email exists.
     *
     * @param email The email address to check.
     * @return True if a user with the email exists, otherwise false.
     */
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    /**
     * Authenticates a user by email and password.
     *
     * @param email The email address of the user.
     * @param password The password of the user.
     * @return An Optional containing the authenticated user if credentials are valid, otherwise empty.
     */
    public Optional<User> authenticateUserByEmail(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(password)) { // Assuming passwords are stored in plain text
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
    
    /**
     * Updates a user entity.
     *
     * @param userId The ID of the user to update.
     * @param updatedUser The user entity with updated information.
     * @return An Optional containing the updated user entity if found, otherwise empty.
     */
    public Optional<User> updateUser(Integer userId, User updatedUser) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());
            // Update other fields as needed

            userRepository.save(existingUser);
            return Optional.of(existingUser);
        } else {
            return Optional.empty();
        }
    }

}
