package ca.gbc.bookingservice.dto;

import java.time.LocalDateTime;

public class BookingDTO {

    private String bookingId;
    private String userId;
    private String roomId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String purpose;


    public BookingDTO() {}

    public BookingDTO(String bookingId, String userId, String roomId, LocalDateTime startTime, LocalDateTime endTime, String purpose) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.roomId = roomId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.purpose = purpose;
    }

    public BookingDTO(String userId, String roomId, LocalDateTime startTime, LocalDateTime endTime, String purpose) {
        this.userId = userId;
        this.roomId = roomId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.purpose = purpose;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
