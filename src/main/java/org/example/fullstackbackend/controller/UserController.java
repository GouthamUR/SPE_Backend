package org.example.fullstackbackend.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.fullstackbackend.exception.UserNotFoundException;
import org.example.fullstackbackend.model.User;
import org.example.fullstackbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin("http://localhost:3000")
@CrossOrigin("http://192.168.49.2:30007")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser) {
        logger.info("Creating a new user: {}", newUser);
        User savedUser = userRepository.save(newUser);
        logger.info("User created successfully with ID: {}", savedUser.getId());
        return savedUser;
    }

    @GetMapping("/users")
    List<User> getAllUsers() {
        logger.info("Fetching all users.");
        List<User> users = userRepository.findAll();
        logger.info("Total users fetched: {}", users.size());
        return users;
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id) {
        logger.info("Fetching user with ID: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("User with ID: {} not found.", id);
                    return new UserNotFoundException(id);
                });
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        logger.info("Updating user with ID: {}", id);
        return userRepository.findById(id)
                .map(user -> {
                    logger.info("User found with ID: {}. Updating details.", id);
                    user.setUsername(newUser.getUsername());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    User updatedUser = userRepository.save(user);
                    logger.info("User updated successfully with ID: {}", id);
                    return updatedUser;
                }).orElseThrow(() -> {
                    logger.error("User with ID: {} not found for update.", id);
                    return new UserNotFoundException(id);
                });
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id) {
        logger.info("Deleting user with ID: {}", id);
        if (!userRepository.existsById(id)) {
            logger.error("User with ID: {} not found for deletion.", id);
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        logger.info("User with ID: {} deleted successfully.", id);
        return "User with id " + id + " has been deleted success.";
    }
}
