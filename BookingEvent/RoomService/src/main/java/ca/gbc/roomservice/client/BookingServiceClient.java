package ca.gbc.roomservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "BookingService", url = "http://booking-service:8086")
public interface BookingServiceClient {

    @GetMapping("/bookings/availability")
    boolean isRoomAvailable(@RequestParam("roomId") String roomId,
                            @RequestParam("startTime") String startTime,
                            @RequestParam("endTime") String endTime);
}
