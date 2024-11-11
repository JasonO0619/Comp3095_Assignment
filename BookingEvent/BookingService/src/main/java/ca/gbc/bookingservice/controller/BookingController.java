package ca.gbc.bookingservice.controller;

import ca.gbc.bookingservice.dto.BookingDTO;
import ca.gbc.bookingservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public BookingDTO createBooking(@RequestBody BookingDTO bookingDTO) {
        return bookingService.createBooking(bookingDTO);
    }

    @GetMapping
    public List<BookingDTO> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public BookingDTO getBookingById(@PathVariable String id) {
        return bookingService.getBookingById(id);
    }

    @GetMapping("/availability")
    public boolean isRoomAvailable(@RequestParam String roomId,
                                   @RequestParam String startTime,
                                   @RequestParam String endTime) {
        return bookingService.isRoomAvailable(roomId, startTime, endTime);
    }
}
