package ca.gbc.roomservice.service;

import ca.gbc.roomservice.dto.RoomDTO;
import ca.gbc.roomservice.model.Room;
import ca.gbc.roomservice.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    // Convert Room entity to RoomDTO
    private RoomDTO convertToDTO(Room room) {
        return new RoomDTO(
                room.getRoomName(),
                room.getCapacity(),
                room.getFeatures(),
                room.isAvailability()
        );
    }

    // Convert RoomDTO to Room entity
    private Room convertToEntity(RoomDTO roomDTO) {
        return new Room(
                roomDTO.getRoomName(),
                roomDTO.getCapacity(),
                roomDTO.getFeatures(),
                roomDTO.isAvailability()
        );
    }

    public RoomDTO addRoom(RoomDTO roomDTO) {
        Room room = convertToEntity(roomDTO);
        Room savedRoom = roomRepository.save(room);
        return convertToDTO(savedRoom);
    }

    public List<RoomDTO> getAllRooms() {
        return roomRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<RoomDTO> getAvailableRooms() {
        return roomRepository.findByAvailabilityTrue().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public RoomDTO updateRoomAvailability(Long roomId, boolean availability) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));
        room.setAvailability(availability);
        Room updatedRoom = roomRepository.save(room);
        return convertToDTO(updatedRoom);
    }
}
