package com.lp.conference.registration_service.client;

import com.lp.conference.registration_service.dto.ConferenceResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "conference-service", url = "${conference-service.url:http://localhost:8081/api/v1/conferences}")
public interface ConferenceClient {

    @GetMapping("/{id}")
    ConferenceResponseDTO getConferenceById(@PathVariable("id") Long id);

    @PutMapping("/{id}/decrease-seats")
    void decreaseSeats(@PathVariable("id") Long id);
}