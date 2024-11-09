package ca.gbc.eventservice.dto;

public class EventDTO {

    private String eventName;
    private String organizerId;
    private String eventType;  // e.g., "meeting", "seminar", "workshop"
    private int expectedAttendees;

    // Constructors, getters, and setters
    public EventDTO() {}

    public EventDTO(String eventName, String organizerId, String eventType, int expectedAttendees) {
        this.eventName = eventName;
        this.organizerId = organizerId;
        this.eventType = eventType;
        this.expectedAttendees = expectedAttendees;
    }
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(String organizerId) {
        this.organizerId = organizerId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public int getExpectedAttendees() {
        return expectedAttendees;
    }

    public void setExpectedAttendees(int expectedAttendees) {
        this.expectedAttendees = expectedAttendees;
    }
}
