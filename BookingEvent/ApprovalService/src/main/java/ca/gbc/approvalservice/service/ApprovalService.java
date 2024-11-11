package ca.gbc.approvalservice.service;

import ca.gbc.approvalservice.client.EventServiceClient;
import ca.gbc.approvalservice.client.UserServiceClient;
import ca.gbc.approvalservice.dto.ApprovalDTO;
import ca.gbc.approvalservice.dto.ReplicaEventDTO;
import ca.gbc.approvalservice.model.Approval;
import ca.gbc.approvalservice.repository.ApprovalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApprovalService {

    private final ApprovalRepository approvalRepository;
    private final EventServiceClient eventServiceClient;
    private final UserServiceClient userServiceClient;

    @Autowired
    public ApprovalService(ApprovalRepository approvalRepository,
                           EventServiceClient eventServiceClient,
                           UserServiceClient userServiceClient) {
        this.approvalRepository = approvalRepository;
        this.eventServiceClient = eventServiceClient;
        this.userServiceClient = userServiceClient;
    }

    // Convert Approval entity to ApprovalDTO
    private ApprovalDTO convertToDTO(Approval approval) {
        return new ApprovalDTO(
                approval.getId(),
                approval.getEventId(),
                approval.getApproverId(),
                approval.isApproved(),
                approval.getComments()
        );
    }

    // Convert ApprovalDTO to Approval entity
    private Approval convertToEntity(ApprovalDTO approvalDTO) {
        return new Approval(
                approvalDTO.getApproverId(),
                approvalDTO.getEventId(),
                approvalDTO.getApproverId(),
                approvalDTO.isApproved(),
                approvalDTO.getComments()
        );
    }

    // Approve or reject an event, using DTO
    public ApprovalDTO approveOrRejectEvent(ApprovalDTO approvalDTO) {
        // Fetch event details
        ReplicaEventDTO event = eventServiceClient.getEventById(approvalDTO.getEventId());

        // Fetch approver's role
        String approverRole = userServiceClient.findUserRoleById(approvalDTO.getApproverId());

        // Validate role
        if (!"staff".equalsIgnoreCase(approverRole)) {
            throw new IllegalArgumentException("Only staff members are authorized to approve or reject events.");
        }

        // Convert DTO to entity and save
        Approval approval = convertToEntity(approvalDTO);
        Approval savedApproval = approvalRepository.save(approval);

        return convertToDTO(savedApproval);
    }

    // Get all approvals as DTOs
    public List<ApprovalDTO> getAllApprovals() {
        return approvalRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
