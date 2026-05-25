package com.lp.conference.conference_system.controller;


import com.lp.conference.conference_system.dto.SessionRequestDTO;
import com.lp.conference.conference_system.dto.SessionResponseDTO;
import com.lp.conference.conference_system.service.SessionService;

import jakarta.validation.Valid;

import com.lp.conference.conference_system.service.ConferenceService;
import com.lp.conference.conference_system.service.ScheduleService;
import com.lp.conference.conference_system.dto.ScheduleResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/conferences")
public class SessionController {

    private final SessionService sessionService;
    private final ScheduleService scheduleService;
    private final ConferenceService conferenceService;

    public SessionController(SessionService sessionService, ScheduleService scheduleService, ConferenceService conferenceService) {
        this.sessionService = sessionService;
        this.scheduleService = scheduleService;
        this.conferenceService = conferenceService;
    }

    @PostMapping("/{confId}/sessions")
    public ResponseEntity<SessionResponseDTO> createSession(@PathVariable Long confId,@Valid @RequestBody SessionRequestDTO dto) {
        
        if (conferenceService.getConferenceById(confId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        dto.setConferenceID(confId); 
        SessionResponseDTO created = sessionService.createSession(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{confId}/schedule")
    public ResponseEntity<ScheduleResponseDTO> getSchedule(@PathVariable Long confId) {
        
        if (conferenceService.getConferenceById(confId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return scheduleService.getScheduleByConference(confId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}