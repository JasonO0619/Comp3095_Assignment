package ca.gbc.userservice.service;

import ca.gbc.userservice.dto.UserDTO;
import ca.gbc.userservice.model.User;
import ca.gbc.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private UserDTO convertToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getUserType()
        );
    }

    private User convertToEntity(UserDTO userDTO) {
        return new User(
                userDTO.getName(),
                userDTO.getEmail(),
                userDTO.getRole(),
                userDTO.getUserType()
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

    public List<UserDTO> getAllUsers() {

        List<User> users = userRepository.findAll();

        return users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public String findUserRoleById(Long userId) {
        User user = userRepository.findById(String.valueOf(userId))
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getRole();
    }
}
