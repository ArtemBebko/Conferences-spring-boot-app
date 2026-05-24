package com.lp.conference.conference_system.service;

import com.lp.conference.conference_system.dto.ScheduleResponseDTO;
import com.lp.conference.conference_system.dto.SessionResponseDTO;
import com.lp.conference.conference_system.model.Conference;
import com.lp.conference.conference_system.model.Schedule;
import com.lp.conference.conference_system.repository.ConferenceRepository;
import com.lp.conference.conference_system.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ConferenceRepository conferenceRepository;
    private final SessionService sessionService;

    public ScheduleService(ScheduleRepository scheduleRepository, ConferenceRepository conferenceRepository, SessionService sessionService) {
        this.scheduleRepository = scheduleRepository;
        this.conferenceRepository = conferenceRepository;
        this.sessionService = sessionService;
    }

    @Transactional
    public ScheduleResponseDTO generateSchedule(Long conferenceId) {
        if (scheduleRepository.findByConference_Id(conferenceId).isPresent()) {
            throw new IllegalStateException("Schedule for this conference already exists");
        }
        Conference conference = conferenceRepository.findById(conferenceId)
                .orElseThrow(() -> new IllegalArgumentException("Conference not found"));

        Schedule schedule = new Schedule(null, LocalDate.now(), conference);
        return mapToResponseDTO(scheduleRepository.save(schedule));
    }

    public Optional<ScheduleResponseDTO> getScheduleByConference(Long conferenceId) {
        return scheduleRepository.findByConference_Id(conferenceId).map(this::mapToResponseDTO);
    }

    public List<ScheduleResponseDTO> getAllSchedules() {
        return scheduleRepository.findAll().stream().map(this::mapToResponseDTO).toList();
    }

    private ScheduleResponseDTO mapToResponseDTO(Schedule schedule) {
        ScheduleResponseDTO dto = new ScheduleResponseDTO();
        dto.setId(schedule.getId());
        dto.setGeneratedAt(schedule.getGeneratedAt());
        dto.setConferenceID(schedule.getConference().getId());
        
        List<SessionResponseDTO> sessions = sessionService.getSessionsByConference(schedule.getConference().getId());
        dto.setSessions(sessions);
        return dto;
    }
}