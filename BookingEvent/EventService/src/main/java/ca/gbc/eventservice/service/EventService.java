package ca.gbc.eventservice.service;

import ca.gbc.eventservice.client.UserServiceClient;
import ca.gbc.eventservice.dto.EventDTO;
import ca.gbc.eventservice.model.Event;
import ca.gbc.eventservice.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final UserServiceClient userServiceClient;

    @Autowired
    public EventService(EventRepository eventRepository, UserServiceClient userServiceClient) {
        this.eventRepository = eventRepository;
        this.userServiceClient = userServiceClient;
    }

    private EventDTO convertToDTO(Event event) {
        return new EventDTO(
                event.getId(),
                event.getEventName(),
                event.getOrganizerId(),
                event.getEventType(),
                event.getExpectedAttendees()
        );
    }


    private Event convertToEntity(EventDTO eventDTO) {
        return new Event(
                eventDTO.getId() != null ? eventDTO.getId() : null,
                eventDTO.getEventName(),
                eventDTO.getOrganizerId(),
                eventDTO.getEventType(),
                eventDTO.getExpectedAttendees()
        );
    }


    public EventDTO createEvent(EventDTO eventDTO) {
        // Validate organizer's role
        String userRole = userServiceClient.findUserRoleById(eventDTO.getOrganizerId());
        if ("student".equalsIgnoreCase(userRole) && eventDTO.getExpectedAttendees() > 50) {
            throw new IllegalArgumentException("Students are not allowed to organize events with more than 50 attendees.");
        }

        Event event = convertToEntity(eventDTO);
        Event savedEvent = eventRepository.save(event);

        return convertToDTO(savedEvent);
    }


    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EventDTO getEventById(String id) {
        Event event = eventRepository.findById(id).orElse(null);
        if (event == null) {
            return null;  // or throw an exception if needed
        }
        return convertToDTO(event);
    }
}
