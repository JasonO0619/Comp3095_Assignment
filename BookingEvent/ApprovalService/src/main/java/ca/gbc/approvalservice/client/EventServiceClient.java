package ca.gbc.approvalservice.client;

import ca.gbc.approvalservice.dto.ReplicaEventDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "EventService", url = "http://event-service:8095")
public interface EventServiceClient {

    @GetMapping("/events/{id}")
    ReplicaEventDTO getEventById(@PathVariable("id") String id);}
