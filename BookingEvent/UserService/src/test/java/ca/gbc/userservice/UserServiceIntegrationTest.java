package ca.gbc.userservice;

import ca.gbc.userservice.dto.UserDTO;
import ca.gbc.userservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceIntegrationTest extends PostgreSQLBaseIntegrationTest {

    @Autowired
    private UserService userService;

    @Test
    public void testCreateUser() {
        UserDTO userDTO = new UserDTO(null, "John Doe", "john@example.com", "staff", "admin");
        UserDTO createdUser = userService.createUser(userDTO);
        assertThat(createdUser).isNotNull();
        assertThat(createdUser.getId()).isNotNull();
    }

    @Test
    public void testGetUserByEmail() {

        UserDTO userDTO = new UserDTO(null, "Jane Doe", "jane@example.com", "student", "user");
        UserDTO createdUser = userService.createUser(userDTO);


        Optional<UserDTO> retrievedUser = userService.getUserByEmail("jane@example.com");


        assertThat(retrievedUser).isPresent();
        assertThat(retrievedUser.get().getId()).isEqualTo(createdUser.getId());
        assertThat(retrievedUser.get().getName()).isEqualTo(createdUser.getName());
        assertThat(retrievedUser.get().getEmail()).isEqualTo(createdUser.getEmail());
        assertThat(retrievedUser.get().getRole()).isEqualTo(createdUser.getRole());
        assertThat(retrievedUser.get().getUserType()).isEqualTo(createdUser.getUserType());
    }

}