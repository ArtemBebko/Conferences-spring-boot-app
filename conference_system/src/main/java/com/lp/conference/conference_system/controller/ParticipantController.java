package com.lp.conference.conference_system.controller;


import com.lp.conference.conference_system.dto.ParticipantRequestDTO;
import com.lp.conference.conference_system.dto.ParticipantResponseDTO;
import com.lp.conference.conference_system.service.ConferenceService;
import com.lp.conference.conference_system.service.ParticipantService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class ParticipantController {

    private final ParticipantService participantService;
    private final ConferenceService conferenceService;

    public ParticipantController(ParticipantService participantService, ConferenceService conferenceService) {
        this.participantService = participantService;
        this.conferenceService = conferenceService;
    }

    @PostMapping("/participants")
    public ResponseEntity<ParticipantResponseDTO> createParticipant(@Valid @RequestBody ParticipantRequestDTO dto) {
        ParticipantResponseDTO created = participantService.createParticipant(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/participants/{id}")
    public ResponseEntity<ParticipantResponseDTO> getParticipantById(@PathVariable Long id) {
        return participantService.getParticipantById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/conferences/{confId}/participants/export")
    public ResponseEntity<List<ParticipantResponseDTO>> exportParticipants(@PathVariable Long confId) {
        if (conferenceService.getConferenceById(confId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(participantService.getParticipantsByConference(confId));
    }
}