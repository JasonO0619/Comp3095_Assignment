package ca.gbc.eventservice.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "UserService", url = "http://user-service:8087")
public interface UserServiceClient {
    @GetMapping("/users/{id}/role")
    String findUserRoleById(@PathVariable("id") String id);
}