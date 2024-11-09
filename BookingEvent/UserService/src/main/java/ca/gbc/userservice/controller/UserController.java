package ca.gbc.userservice.controller;

import ca.gbc.userservice.dto.UserDTO;
import ca.gbc.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Create a new user (receive UserDTO and return UserDTO)
    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    // Get user details by email (return UserDTO)
    @GetMapping("/{email}")
    public Optional<UserDTO> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    // Check if a user is authorized to approve events (returns boolean)
    @GetMapping("/{email}/can-approve")
    public boolean canUserApprove(@PathVariable String email) {
        return userService.isUserAuthorizedToApprove(email);
    }
}
