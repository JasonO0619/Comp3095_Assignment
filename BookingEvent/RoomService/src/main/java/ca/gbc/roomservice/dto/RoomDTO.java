package ca.gbc.roomservice.dto;

public class RoomDTO {

    private Long id;
    private String roomName;
    private int capacity;
    private String features;
    private boolean availability;

    public RoomDTO() {}

    public RoomDTO(String roomName, int capacity, String features, boolean availability) {
        this.roomName = roomName;
        this.capacity = capacity;
        this.features = features;
        this.availability = availability;
    }
    public RoomDTO(Long id, String roomName, int capacity, String features, boolean availability) {
        this.roomName = roomName;
        this.id = id;
        this.capacity = capacity;
        this.features = features;
        this.availability = availability;
    }


    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public Long getId() {
        return id;
    }

}
