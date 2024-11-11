package ca.gbc.bookingservice.service;

import ca.gbc.bookingservice.client.RoomServiceClient;
import ca.gbc.bookingservice.model.Booking;
import ca.gbc.bookingservice.repository.BookingRepository;
import ca.gbc.bookingservice.dto.BookingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    private final RoomServiceClient roomServiceClient;

    @Autowired
    public BookingService(RoomServiceClient roomServiceClient) {
        this.roomServiceClient = roomServiceClient;
    }


    private BookingDTO convertToDTO(Booking booking) {
        return new BookingDTO(
                booking.getId(),
                booking.getUserId(),
                booking.getRoomId(),
                booking.getStartTime(),
                booking.getEndTime(),
                booking.getPurpose()
        );
    }

    private Booking convertToEntity(BookingDTO bookingDTO) {
        Booking booking = new Booking(
                bookingDTO.getUserId(),
                bookingDTO.getRoomId(),
                bookingDTO.getStartTime(),
                bookingDTO.getEndTime(),
                bookingDTO.getPurpose()
        );
        booking.setId(bookingDTO.getBookingId());
        return booking;
    }

    public BookingDTO createBooking(BookingDTO bookingDTO) {
        boolean isAvailable = roomServiceClient.isRoomAvailable(
                bookingDTO.getRoomId(),
                bookingDTO.getStartTime().toString(),
                bookingDTO.getEndTime().toString()
        );

        if (!isAvailable) {
            throw new IllegalArgumentException("Room is not available for the specified time range.");
        }

        Booking booking = convertToEntity(bookingDTO);

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
