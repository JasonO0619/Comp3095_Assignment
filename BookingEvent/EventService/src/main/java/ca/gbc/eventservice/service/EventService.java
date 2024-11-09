package ca.gbc.eventservice.service;

import ca.gbc.eventservice.dto.EventDTO;
import ca.gbc.eventservice.model.Event;
import ca.gbc.eventservice.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    // Convert Event entity to EventDTO
    private EventDTO convertToDTO(Event event) {
        return new EventDTO(
                event.getEventName(),
                event.getOrganizerId(),
                event.getEventType(),
                event.getExpectedAttendees()
        );
    }

    // Convert EventDTO to Event entity
    private Event convertToEntity(EventDTO eventDTO) {
        return new Event(
                eventDTO.getEventName(),
                eventDTO.getOrganizerId(),
                eventDTO.getEventType(),
                eventDTO.getExpectedAttendees()
        );
    }

    // Create an Event from EventDTO
    public EventDTO createEvent(EventDTO eventDTO) {
        // Convert DTO to entity for saving to the database
        Event event = convertToEntity(eventDTO);

        // Save the event and return it as a DTO
        Event savedEvent = eventRepository.save(event);
        return convertToDTO(savedEvent);
    }

    // Get all events as DTOs
    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get event by ID and return as DTO
    public EventDTO getEventById(String id) {
        Event event = eventRepository.findById(id).orElse(null);
        if (event == null) {
            return null;  // or throw an exception if needed
        }
        return convertToDTO(event);
    }
}
