package ca.gbc.approvalservice.service;

import ca.gbc.approvalservice.model.Approval;
import ca.gbc.approvalservice.dto.ApprovalDTO;  // Import DTO
import ca.gbc.approvalservice.repository.ApprovalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApprovalService {

    @Autowired
    private ApprovalRepository approvalRepository;

    // Convert Approval entity to ApprovalDTO
    private ApprovalDTO convertToDTO(Approval approval) {
        return new ApprovalDTO(
                approval.getEventId(),
                approval.getApproverId(),
                approval.isApproved(),
                approval.getComments()
        );
    }

    // Convert ApprovalDTO to Approval entity
    private Approval convertToEntity(ApprovalDTO approvalDTO) {
        return new Approval(
                approvalDTO.getEventId(),
                approvalDTO.getApproverId(),
                approvalDTO.isApproved(),
                approvalDTO.getComments()
        );
    }

    // Approve or reject an event, using DTO
    public ApprovalDTO approveOrRejectEvent(ApprovalDTO approvalDTO, String role) {
        if (!"staff".equalsIgnoreCase(role)) {
            throw new IllegalArgumentException("Only staff members are authorized to approve or reject events.");
        }
        // Convert DTO to entity for saving
        Approval approval = convertToEntity(approvalDTO);

        // Save the approval and return the result as a DTO
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
