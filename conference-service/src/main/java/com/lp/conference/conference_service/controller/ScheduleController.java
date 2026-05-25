package com.lp.conference.conference_service.controller;


import com.lp.conference.conference_service.dto.ScheduleResponseDTO;
import com.lp.conference.conference_service.service.ConferenceService;
import com.lp.conference.conference_service.service.ScheduleService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final ConferenceService conferenceService;

    public ScheduleController(ScheduleService scheduleService, ConferenceService conferenceService) {
        this.scheduleService = scheduleService;
        this.conferenceService = conferenceService;
    }

    @PostMapping("/conferences/{conferenceId}/schedule")
    public ResponseEntity<ScheduleResponseDTO> generateSchedule(@PathVariable Long conferenceId) {
        if (conferenceService.getConferenceById(conferenceId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        ScheduleResponseDTO schedule = scheduleService.generateSchedule(conferenceId);
        return ResponseEntity.status(HttpStatus.CREATED).body(schedule);
    }

    @GetMapping("/conferences/{conferenceId}/schedule")
    public ResponseEntity<ScheduleResponseDTO> getScheduleByConference(@PathVariable Long conferenceId) {
        if (conferenceService.getConferenceById(conferenceId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        return scheduleService.getScheduleByConference(conferenceId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDTO>> getAllSchedules() {
        return ResponseEntity.ok(scheduleService.getAllSchedules());
    }
}