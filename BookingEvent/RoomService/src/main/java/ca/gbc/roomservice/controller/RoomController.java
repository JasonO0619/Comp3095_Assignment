package ca.gbc.roomservice.controller;

import ca.gbc.roomservice.dto.RoomDTO;
import ca.gbc.roomservice.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    // Add a new room (accepts RoomDTO and returns RoomDTO)
    @PostMapping
    public RoomDTO addRoom(@RequestBody RoomDTO roomDTO) {
        return roomService.addRoom(roomDTO);
    }

    // Get all rooms (returns a list of RoomDTOs)
    @GetMapping
    public List<RoomDTO> getAllRooms() {
        return roomService.getAllRooms();
    }

    // Get available rooms (returns a list of RoomDTOs)
    @GetMapping("/available")
    public List<RoomDTO> getAvailableRooms() {
        return roomService.getAvailableRooms();
    }

    // Update room availability (accepts roomId and availability, returns updated RoomDTO)
    @PutMapping("/{id}/availability")
    public RoomDTO updateRoomAvailability(@PathVariable Long id, @RequestParam boolean availability) {
        return roomService.updateRoomAvailability(id, availability);
    }
}
