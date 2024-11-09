package ca.gbc.bookingservice.repository;

import ca.gbc.bookingservice.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {

    // Find overlapping bookings for a specific room
    List<Booking> findByRoomIdAndEndTimeAfterAndStartTimeBefore(String roomId, LocalDateTime startTime, LocalDateTime endTime);
}