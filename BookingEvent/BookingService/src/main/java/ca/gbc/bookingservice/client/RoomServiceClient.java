package ca.gbc.bookingservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "RoomService", url = "http://room-service:8085")
public interface RoomServiceClient {
    @GetMapping("/rooms/{id}/availability")
    boolean isRoomAvailable(@PathVariable("id") String roomId,
                            @RequestParam("startTime") String startTime,
                            @RequestParam("endTime") String endTime);
}