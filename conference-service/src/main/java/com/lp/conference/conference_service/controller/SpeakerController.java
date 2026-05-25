package com.lp.conference.conference_service.controller;


import com.lp.conference.conference_service.dto.SpeakerRequestDTO;
import com.lp.conference.conference_service.dto.SpeakerResponseDTO;
import com.lp.conference.conference_service.service.SpeakerService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakerController {

    private final SpeakerService speakerService;

    public SpeakerController(SpeakerService speakerService) {
        this.speakerService = speakerService;
    }

    @PostMapping
    public ResponseEntity<SpeakerResponseDTO> createSpeaker(@Valid @RequestBody SpeakerRequestDTO dto) {
        SpeakerResponseDTO created = speakerService.createSpeaker(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}