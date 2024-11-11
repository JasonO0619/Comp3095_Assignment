package ca.gbc.approvalservice;

import ca.gbc.approvalservice.client.EventServiceClient;
import ca.gbc.approvalservice.client.UserServiceClient;
import ca.gbc.approvalservice.dto.ApprovalDTO;
import ca.gbc.approvalservice.dto.ReplicaEventDTO;
import ca.gbc.approvalservice.service.ApprovalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ApprovalServiceIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private ApprovalService approvalService;

    @MockBean
    private EventServiceClient eventServiceClient;

    @MockBean
    private UserServiceClient userServiceClient;

    @BeforeEach
    public void setUpMocks() {
        Mockito.when(eventServiceClient.getEventById(anyString()))
                .thenReturn(new ReplicaEventDTO("10", "Tech Conference",
                        "organizer123", "Conference", 100));

        Mockito.when(userServiceClient.findUserRoleById(anyString())).thenReturn("student");
    }

    @Test
    public void testApproveEventSuccess() {
        ApprovalDTO approvalDTO = new ApprovalDTO("1", "approver123",
                true, "Approved successfully");


        ApprovalDTO result = approvalService.approveOrRejectEvent(approvalDTO);

        assertThat(result).isNotNull();
        assertThat(result.getApproverId()).isEqualTo("approver123");
        assertThat(result.getEventId()).isEqualTo("1");
        assertThat(result.isApproved()).isTrue();
        assertThat(result.getComments()).isEqualTo("Approved successfully");
    }

    @Test
    public void testApproveEventUnauthorizedRole() {

        Mockito.when(userServiceClient.findUserRoleById("10")).thenReturn("student");

        ApprovalDTO approvalDTO = new ApprovalDTO("unauthorizedUser", "1",
                true, "Trying to approve");


        assertThrows(IllegalArgumentException.class, () -> approvalService.approveOrRejectEvent(approvalDTO),
                "Only staff members are authorized to approve or reject events.");
    }
}