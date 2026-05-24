package com.lp.conference.conference_system.service;

import com.lp.conference.conference_system.dto.SessionRequestDTO;
import com.lp.conference.conference_system.dto.SessionResponseDTO;
import com.lp.conference.conference_system.model.Conference;
import com.lp.conference.conference_system.model.Session;
import com.lp.conference.conference_system.model.Speaker;
import com.lp.conference.conference_system.repository.ConferenceRepository;
import com.lp.conference.conference_system.repository.SessionRepository;
import com.lp.conference.conference_system.repository.SpeakerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final ConferenceRepository conferenceRepository;
    private final SpeakerRepository speakerRepository;

    public SessionService(SessionRepository sessionRepository, ConferenceRepository conferenceRepository, SpeakerRepository speakerRepository) {
        this.sessionRepository = sessionRepository;
        this.conferenceRepository = conferenceRepository;
        this.speakerRepository = speakerRepository;
    }

    @Transactional
    public SessionResponseDTO createSession(SessionRequestDTO dto) {
        Conference conference = conferenceRepository.findById(dto.getConferenceID())
                .orElseThrow(() -> new IllegalArgumentException("Conference not found"));
        Speaker speaker = speakerRepository.findById(dto.getSpeakerID())
                .orElseThrow(() -> new IllegalArgumentException("Speaker not found"));

        Session session = new Session(null, dto.getTitle(), dto.getStartTime(), dto.getEndTime(), conference, speaker);
        return mapToResponseDTO(sessionRepository.save(session));
    }

    public Optional<SessionResponseDTO> getSessionById(Long id) {
        return sessionRepository.findById(id).map(this::mapToResponseDTO);
    }

    public List<SessionResponseDTO> getSessionsByConference(Long conferenceID) {
        return sessionRepository.findByConference_Id(conferenceID).stream()
                .map(this::mapToResponseDTO).toList();
    }

    private SessionResponseDTO mapToResponseDTO(Session session) {
        return new SessionResponseDTO(session.getId(), session.getTitle(), session.getStartTime(),
                session.getEndTime(), session.getConference().getId(), session.getSpeaker().getId());
    }
}