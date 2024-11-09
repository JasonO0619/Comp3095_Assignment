package ca.gbc.userservice.service;

import ca.gbc.userservice.dto.UserDTO;
import ca.gbc.userservice.model.User;
import ca.gbc.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Convert User entity to UserDTO
    private UserDTO convertToDTO(User user) {
        return new UserDTO(
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }

    // Convert UserDTO to User entity
    private User convertToEntity(UserDTO userDTO) {
        return new User(
                userDTO.getName(),
                userDTO.getEmail(),
                userDTO.getRole(),
                null // Assuming userType is optional, and can be null when creating a User
        );
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    public Optional<UserDTO> getUserByEmail(String email) {
        return userRepository.findByEmail(email).map(this::convertToDTO);
    }

    public boolean isUserAuthorizedToApprove(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.map(user -> "staff".equalsIgnoreCase(user.getRole())).orElse(false);
    }
}
