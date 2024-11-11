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

    @PostMapping
    public RoomDTO addRoom(@RequestBody RoomDTO roomDTO) {
        return roomService.addRoom(roomDTO);
    }


    @GetMapping
    public List<RoomDTO> getAllRooms() {
        return roomService.getAllRooms();
    }


    @GetMapping("/available")
    public List<RoomDTO> getAvailableRooms() {
        return roomService.getAvailableRooms();
    }


    @PutMapping("/{id}/availability")
    public RoomDTO updateRoomAvailability(@PathVariable Long id, @RequestParam boolean availability) {
        return roomService.updateRoomAvailability(id, availability);
    }
    @GetMapping("/{id}/availability")
    public boolean isRoomAvailable(@PathVariable Long id,
                                   @RequestParam String startTime,
                                   @RequestParam String endTime) {
        return roomService.isRoomAvailable(id, startTime, endTime);
    }
}
