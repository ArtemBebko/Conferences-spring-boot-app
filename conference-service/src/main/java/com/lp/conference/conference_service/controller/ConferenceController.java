package com.lp.conference.conference_service.controller;

import com.lp.conference.conference_service.dto.ConferenceRequestDTO;
import com.lp.conference.conference_service.dto.ConferenceResponseDTO;
import com.lp.conference.conference_service.service.ConferenceService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/conferences")
public class ConferenceController {

    private final ConferenceService conferenceService;

    public ConferenceController(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @PostMapping
    public ResponseEntity<ConferenceResponseDTO> createConference(@Valid @RequestBody ConferenceRequestDTO dto) {
        ConferenceResponseDTO created = conferenceService.createConference(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConferenceResponseDTO> getConferenceById(@PathVariable Long id) {
        return conferenceService.getConferenceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConference(@PathVariable Long id) {
        conferenceService.deleteConference(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConferenceResponseDTO> updateConference(
            @PathVariable Long id, 
            @Valid @RequestBody ConferenceRequestDTO dto) {
        
        ConferenceResponseDTO updated = conferenceService.updateConference(id, dto);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/{id}/decrease-seats")
    public ResponseEntity<Void> decreaseSeats(@PathVariable Long id) {
        conferenceService.decreaseSeats(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<ConferenceResponseDTO>> getAllConferences(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(conferenceService.getAllConferences(PageRequest.of(page, size)));
    }
}