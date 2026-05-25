package com.lp.conference.registration_service.controller;

import com.lp.conference.registration_service.dto.ParticipantRequestDTO;
import com.lp.conference.registration_service.dto.ParticipantResponseDTO;
import com.lp.conference.registration_service.service.ParticipantService;
import com.lp.conference.registration_service.client.ConferenceClient; 
import feign.FeignException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ParticipantController {

    private final ParticipantService participantService;
    private final ConferenceClient conferenceClient;

    public ParticipantController(ParticipantService participantService, ConferenceClient conferenceClient) {
        this.participantService = participantService;
        this.conferenceClient = conferenceClient;
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
        try {
            conferenceClient.getConferenceById(confId); 
        } catch (FeignException.NotFound e) {
            return ResponseEntity.notFound().build();
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }
        
        return ResponseEntity.ok(participantService.getParticipantsByConference(confId));
    }
}