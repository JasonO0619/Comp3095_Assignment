package ca.gbc.approvalservice.controller;

import ca.gbc.approvalservice.dto.ApprovalDTO;
import ca.gbc.approvalservice.service.ApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/approvals")
public class ApprovalController {

    @Autowired
    private ApprovalService approvalService;

    @PostMapping
    public ApprovalDTO approveOrRejectEvent(@RequestBody ApprovalDTO approvalDTO) {
        return approvalService.approveOrRejectEvent(approvalDTO);
    }

    @GetMapping
    public List<ApprovalDTO> getAllApprovals() {
        return approvalService.getAllApprovals();
    }
}
