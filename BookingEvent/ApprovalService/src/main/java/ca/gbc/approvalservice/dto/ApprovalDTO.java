package ca.gbc.approvalservice.dto;

public class ApprovalDTO {

    private String eventId;
    private String approverId;
    private boolean approved; // true for approval, false for rejection
    private String comments;

    // Constructors, getters, and setters
    public ApprovalDTO() {}

    public ApprovalDTO(String eventId, String approverId, boolean approved, String comments) {
        this.eventId = eventId;
        this.approverId = approverId;
        this.approved = approved;
        this.comments = comments;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getApproverId() {
        return approverId;
    }

    public void setApproverId(String approverId) {
        this.approverId = approverId;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
