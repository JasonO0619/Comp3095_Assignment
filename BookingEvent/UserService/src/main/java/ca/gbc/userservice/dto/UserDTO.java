package ca.gbc.userservice.dto;

public class UserDTO {

    private Long userId;
    private String name;
    private String email;
    private String role;
    private String userType;


    public UserDTO() {}

    public UserDTO(Long userId, String name, String email, String role, String userType) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.role = role;
        this.userType = userType;
    }

    public Long getId() {
        return userId;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
