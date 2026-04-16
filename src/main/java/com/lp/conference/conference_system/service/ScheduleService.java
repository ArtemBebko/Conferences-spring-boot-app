package com.lp.conference.conference_system.service;


import com.lp.conference.conference_system.dto.ScheduleResponseDTO;
import com.lp.conference.conference_system.dto.SessionResponseDTO;
import com.lp.conference.conference_system.model.Schedule;
import com.lp.conference.conference_system.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final SessionService sessionService;

    public ScheduleService(ScheduleRepository scheduleRepository, SessionService sessionService) {
        this.scheduleRepository = scheduleRepository;
        this.sessionService = sessionService;
    }

    public ScheduleResponseDTO generateSchedule(Long conferenceId) {
        Optional<Schedule> existing = scheduleRepository.findByConferenceId(conferenceId);
        if (existing.isPresent()) {
            throw new IllegalStateException("Schedule for this conference already exists");
        }

        Schedule schedule = new Schedule(
                null,
                LocalDate.now(),
                conferenceId
        );

        Schedule saved = scheduleRepository.save(schedule);
        return mapToResponseDTO(saved);
    }

    public Optional<ScheduleResponseDTO> getScheduleByConference(Long conferenceId) {
        return scheduleRepository.findByConferenceId(conferenceId)
                .map(this::mapToResponseDTO);
    }

    public List<ScheduleResponseDTO> getAllSchedules() {
        return scheduleRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    private ScheduleResponseDTO mapToResponseDTO(Schedule schedule) {
        ScheduleResponseDTO dto = new ScheduleResponseDTO();
        dto.setId(schedule.getId());
        dto.setGeneratedAt(schedule.getGeneratedAt());
        dto.setConferenceID(schedule.getConferenceID());
        
        List<SessionResponseDTO> sessions = sessionService.getSessionsByConference(schedule.getConferenceID());
        dto.setSessions(sessions);
        
        return dto;
    }
}