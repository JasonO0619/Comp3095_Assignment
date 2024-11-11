package ca.gbc.eventservice;

import ca.gbc.eventservice.client.UserServiceClient;
import ca.gbc.eventservice.dto.EventDTO;
import ca.gbc.eventservice.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EventServiceIntegrationTest extends MongoDBBaseIntegrationTest {

    @Autowired
    private EventService eventService;

    @MockBean
    private UserServiceClient userServiceClient;

    @BeforeEach
    public void setUpMock() {
        Mockito.when(userServiceClient.findUserRoleById(anyString())).thenReturn("staff");
    }

    @Test
    public void testCreateEvent() {
        EventDTO eventDTO = new EventDTO("Tech Conference", "organizer123", "Conference", 100);
        EventDTO createdEvent = eventService.createEvent(eventDTO);

        assertThat(createdEvent).isNotNull();
        assertThat(createdEvent.getEventName()).isEqualTo("Tech Conference");
        assertThat(createdEvent.getOrganizerId()).isEqualTo("organizer123");
        assertThat(createdEvent.getEventType()).isEqualTo("Conference");
        assertThat(createdEvent.getExpectedAttendees()).isEqualTo(100);
    }

    @Test
    public void testGetAllEvents() {
        EventDTO eventDTO1 = new EventDTO("Workshop on AI", "organizer124", "Workshop", 30);
        EventDTO eventDTO2 = new EventDTO("Networking Event", "organizer125", "Social", 20);

        eventService.createEvent(eventDTO1);
        eventService.createEvent(eventDTO2);

        List<EventDTO> events = eventService.getAllEvents();

        assertThat(events).hasSizeGreaterThanOrEqualTo(2);
        assertThat(events.stream().anyMatch(event -> "Workshop on AI".equals(event.getEventName()))).isTrue();
        assertThat(events.stream().anyMatch(event -> "Networking Event".equals(event.getEventName()))).isTrue();
    }

    @Test
    public void testGetEventById() {

        EventDTO eventDTO = new EventDTO("Coding Bootcamp", "organizer126", "Training", 50);
        EventDTO createdEvent = eventService.createEvent(eventDTO);


        EventDTO retrievedEvent = eventService.getEventById(createdEvent.getId());

        assertThat(retrievedEvent).isNotNull();
        assertThat(retrievedEvent.getEventName()).isEqualTo("Coding Bootcamp");
        assertThat(retrievedEvent.getOrganizerId()).isEqualTo("organizer126");
        assertThat(retrievedEvent.getEventType()).isEqualTo("Training");
        assertThat(retrievedEvent.getExpectedAttendees()).isEqualTo(50);
    }
}
