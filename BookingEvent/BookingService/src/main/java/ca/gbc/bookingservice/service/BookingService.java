package ca.gbc.bookingservice.service;

import ca.gbc.bookingservice.model.Booking;
import ca.gbc.bookingservice.repository.BookingRepository;
import ca.gbc.bookingservice.dto.BookingDTO;  // Import DTO class
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    // Convert a Booking entity to BookingDTO
    private BookingDTO convertToDTO(Booking booking) {
        return new BookingDTO(
                booking.getUserId(),
                booking.getRoomId(),
                booking.getStartTime(),
                booking.getEndTime(),
                booking.getPurpose()
        );
    }

    private Booking convertToEntity(BookingDTO bookingDTO) {
        return new Booking(
                bookingDTO.getUserId(),
                bookingDTO.getRoomId(),
                bookingDTO.getStartTime(),
                bookingDTO.getEndTime(),
                bookingDTO.getPurpose()
        );
    }

    public BookingDTO createBooking(BookingDTO bookingDTO) {
        // Convert DTO to entity for database operations
        Booking booking = convertToEntity(bookingDTO);

        // Validate that the room is available for the requested time
        List<Booking> overlappingBookings = bookingRepository.findByRoomIdAndEndTimeAfterAndStartTimeBefore(
                booking.getRoomId(), booking.getStartTime(), booking.getEndTime());

        if (!overlappingBookings.isEmpty()) {
            throw new IllegalArgumentException("Room is already booked for the specified time range.");
        }

        // Save the booking and convert it back to a DTO
        Booking savedBooking = bookingRepository.save(booking);
        return convertToDTO(savedBooking);
    }

    public List<BookingDTO> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BookingDTO getBookingById(String id) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking == null) {
            return null;
        }
        return convertToDTO(booking);
    }
}
