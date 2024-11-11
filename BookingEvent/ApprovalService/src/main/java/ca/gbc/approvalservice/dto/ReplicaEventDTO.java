package ca.gbc.approvalservice.dto;


public record ReplicaEventDTO(String id,String eventName, String organizerId,
                              String eventType, int expectedAttendees) {

    }

