package com.web.liveshades.Service;

import com.web.liveshades.DTO.LoginRequestDTO;
import com.web.liveshades.DTO.LoginResponceDTO;
import com.web.liveshades.Model.User;
import com.web.liveshades.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Create User
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // Get All Users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get User By ID
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    // Update User
    public User updateUser(Integer id, User user) {

        User existingUser = userRepository.findById(id).orElse(null);

        if (existingUser != null) {

            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());

            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
            }

            existingUser.setRole(user.getRole());
            existingUser.setEnabled(user.isEnabled());

            return userRepository.save(existingUser);
        }

        return null;
    }

    // LOGIN (FIXED)
    public LoginResponceDTO login(LoginRequestDTO request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (user == null) {
            return new LoginResponceDTO("User not found", false, null);
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new LoginResponceDTO("Invalid password", false, null);
        }

        return new LoginResponceDTO(
                "Login successful",
                true,
                user.getRole().name()
        );
    }

    // Delete User
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}