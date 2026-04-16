package com.lp.conference.conference_system.service;


import com.lp.conference.conference_system.dto.SessionRequestDTO;
import com.lp.conference.conference_system.dto.SessionResponseDTO;
import com.lp.conference.conference_system.model.Session;
import com.lp.conference.conference_system.repository.SessionRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public SessionResponseDTO createSession(SessionRequestDTO dto) {
        Session session = new Session(
                null,
                dto.getTitle(),
                dto.getStartTime(),
                dto.getEndTime(),
                dto.getConferenceID(),
                dto.getSpeakerID()
        );

        Session saved = sessionRepository.save(session);
        return mapToResponseDTO(saved);
    }

    public Optional<SessionResponseDTO> getSessionById(Long id) {
        return sessionRepository.findById(id)
                .map(this::mapToResponseDTO);
    }

    public List<SessionResponseDTO> getSessionsByConference(Long conferenceID) {
        return sessionRepository.findByConferenceId(conferenceID).stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    private SessionResponseDTO mapToResponseDTO(Session session) {
        return new SessionResponseDTO(
                session.getId(),
                session.getTitle(),
                session.getStartTime(),
                session.getEndTime(),
                session.getConferenceID(),
                session.getSpeakerID()
        );
    }
}