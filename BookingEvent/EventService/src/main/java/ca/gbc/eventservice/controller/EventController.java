package ca.gbc.eventservice.controller;

import ca.gbc.eventservice.model.Event;
import ca.gbc.eventservice.dto.EventDTO;
import ca.gbc.eventservice.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;


    @PostMapping
    public EventDTO createEvent(@RequestBody EventDTO eventDTO) {
        return eventService.createEvent(eventDTO);
    }


    @GetMapping
    public List<EventDTO> getAllEvents() {
        return eventService.getAllEvents();
    }


    @GetMapping("/{id}")
    public EventDTO getEventById(@PathVariable String id) {
        return eventService.getEventById(id);
    }
}
