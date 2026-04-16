package com.lp.conference.conference_system.controller;

import com.lp.conference.conference_system.dto.RegistrationRequestDTO;
import com.lp.conference.conference_system.dto.RegistrationResponseDTO;
import com.lp.conference.conference_system.service.RegistrationService;
import com.lp.conference.conference_system.service.ParticipantService; 
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RegistrationController {

    private final RegistrationService registrationService;
    private final ParticipantService participantService;

    public RegistrationController(RegistrationService registrationService, ParticipantService participantService) {
        this.registrationService = registrationService;
        this.participantService = participantService;
    }

    @PostMapping("/registrations")
    public ResponseEntity<RegistrationResponseDTO> registerParticipant(@Valid @RequestBody RegistrationRequestDTO dto) {
        RegistrationResponseDTO registration = registrationService.registerParticipant(
                dto.getParticipantID(), 
                dto.getConferenceID()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(registration);
    }

    @GetMapping("/participants/{id}/registrations")
    public ResponseEntity<List<RegistrationResponseDTO>> getRegistrationsByParticipant(@PathVariable Long id) {
        if (participantService.getParticipantById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(registrationService.getRegistrationsByParticipant(id));
    }

    @PatchMapping("/registrations/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable Long id, @RequestParam String status) {
        boolean updated = registrationService.updateStatus(id, status);
        if (!updated) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}