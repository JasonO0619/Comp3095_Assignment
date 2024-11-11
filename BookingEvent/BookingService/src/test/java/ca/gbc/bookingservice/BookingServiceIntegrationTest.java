package ca.gbc.bookingservice;

import ca.gbc.bookingservice.client.RoomServiceClient;
import ca.gbc.bookingservice.dto.BookingDTO;
import ca.gbc.bookingservice.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BookingServiceIntegrationTest extends MongoDBBaseIntegrationTest {

    @Autowired
    private BookingService bookingService;

    @MockBean
    private RoomServiceClient roomServiceClient;

    @BeforeEach
    public void setUpMock() {
        Mockito.when(roomServiceClient.isRoomAvailable(Long.valueOf(anyString()), anyString(), anyString())).thenReturn(true);
    }

    @Test
    public void testCreateBooking() {
        BookingDTO bookingDTO = new BookingDTO("1","user123", "room456",
                LocalDateTime.of(2024, 11, 15, 10, 0),
                LocalDateTime.of(2024, 11, 15, 12, 0),
                "Project Meeting");

        BookingDTO createdBooking = bookingService.createBooking(bookingDTO);

        assertThat(createdBooking).isNotNull();
        assertThat(createdBooking.getBookingId()).isEqualTo("1");
        assertThat(createdBooking.getUserId()).isEqualTo("user123");
        assertThat(createdBooking.getRoomId()).isEqualTo("room456");
        assertThat(createdBooking.getStartTime()).isEqualTo(LocalDateTime.of(2024, 11, 15, 10, 0));
        assertThat(createdBooking.getEndTime()).isEqualTo(LocalDateTime.of(2024, 11, 15, 12, 0));
        assertThat(createdBooking.getPurpose()).isEqualTo("Project Meeting");
    }

    @Test
    public void testCreateBookingRoomUnavailable() {
        Mockito.when(roomServiceClient.isRoomAvailable(Long.valueOf(anyString()), anyString(), anyString())).thenReturn(false);

        BookingDTO bookingDTO = new BookingDTO("2","user123", "room456",
                LocalDateTime.of(2024, 11, 15, 10, 0),
                LocalDateTime.of(2024, 11, 15, 12, 0),
                "Project Meeting");

        assertThrows(IllegalArgumentException.class, () -> bookingService.createBooking(bookingDTO),
                "Room is not available for the specified time range.");
    }

    @Test
    public void testGetAllBookings() {
        BookingDTO bookingDTO1 = new BookingDTO("3","user123", "room456",
                LocalDateTime.of(2024, 11, 15, 10, 0),
                LocalDateTime.of(2024, 11, 15, 12, 0),
                "Project Meeting");
        BookingDTO bookingDTO2 = new BookingDTO("4","user456", "room789",
                LocalDateTime.of(2024, 11, 16, 9, 0),
                LocalDateTime.of(2024, 11, 16, 10, 0),
                "Team Standup");

        bookingService.createBooking(bookingDTO1);
        bookingService.createBooking(bookingDTO2);

        List<BookingDTO> bookings = bookingService.getAllBookings();

        assertThat(bookings).hasSizeGreaterThanOrEqualTo(2);
        assertThat(bookings.stream().anyMatch(b -> "Project Meeting".equals(b.getPurpose()))).isTrue();
        assertThat(bookings.stream().anyMatch(b -> "Team Standup".equals(b.getPurpose()))).isTrue();
    }


    @Test
    public void testGetBookingById() {
        BookingDTO bookingDTO = new BookingDTO(null, "user123", "room456",
                LocalDateTime.of(2024, 11, 15, 10, 0),
                LocalDateTime.of(2024, 11, 15, 12, 0),
                "Project Meeting");

        BookingDTO createdBooking = bookingService.createBooking(bookingDTO);

        BookingDTO retrievedBooking = bookingService.getBookingById(createdBooking.getBookingId());

        assertThat(retrievedBooking).isNotNull();
        assertThat(retrievedBooking.getUserId()).isEqualTo("user123");
        assertThat(retrievedBooking.getRoomId()).isEqualTo("room456");
        assertThat(retrievedBooking.getStartTime()).isEqualTo(LocalDateTime.of(2024, 11, 15, 10, 0));
        assertThat(retrievedBooking.getEndTime()).isEqualTo(LocalDateTime.of(2024, 11, 15, 12, 0));
        assertThat(retrievedBooking.getPurpose()).isEqualTo("Project Meeting");
    }
}

