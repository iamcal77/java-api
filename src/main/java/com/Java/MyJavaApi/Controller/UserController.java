package com.Java.MyJavaApi.Controller;

import com.Java.MyJavaApi.Models.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final String JSON_FILE_PATH = "json/users.json";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private List<User> users = new ArrayList<>();
    private long idCounter = 1;

    // Constructor - Load users from JSON file on startup
    public UserController() {
        loadUsersFromFile();
        if (!users.isEmpty()) {
            idCounter = users.get(users.size() - 1).getId() + 1;
        }
    }

    // Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }

    // Add a new user
    @PostMapping
    public User addUser(@RequestBody User user) {
        user.setId(idCounter++);
        users.add(user);
        saveUsersToFile();
        return user;
    }

    // Get a user by ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable long id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Update a user
    @PutMapping("/{id}")
    public User updateUser(@PathVariable long id, @RequestBody User updatedUser) {
        for (User user : users) {
            if (user.getId() == id) {
                user.setFirstName(updatedUser.getFirstName());
                user.setLastName(updatedUser.getLastName());
                user.setActive(updatedUser.isActive());
                user.setBalance(updatedUser.getBalance());
                saveUsersToFile();
                return user;
            }
        }
        throw new RuntimeException("User not found");
    }

    // Delete a user
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable long id) {
        users.removeIf(user -> user.getId() == id);
        saveUsersToFile();
        return "User deleted successfully";
    }

    // Save users to JSON file
    private void saveUsersToFile() {
        try {
            objectMapper.writeValue(new File(JSON_FILE_PATH), users);
        } catch (IOException e) {
            throw new RuntimeException("Error saving users to file", e);
        }
    }

    // Load users from JSON file
    private void loadUsersFromFile() {
        try {
            File file = new File(JSON_FILE_PATH);
            if (file.exists()) {
                users = objectMapper.readValue(file, new TypeReference<>() {});
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading users from file", e);
        }
    }
}
