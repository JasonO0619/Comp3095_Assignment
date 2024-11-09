package ca.gbc.userservice.dto;

public class UserDTO {

    private String name;
    private String email;
    private String role; // e.g., "staff", "student", "faculty"

    // Constructors, getters, and setters
    public UserDTO() {}

    public UserDTO(String name, String email, String role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
