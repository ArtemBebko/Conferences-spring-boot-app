package com.lp.conference.conference_system.service;

import com.lp.conference.conference_system.model.Registration;
import com.lp.conference.conference_system.dto.RegistrationResponseDTO;
import com.lp.conference.conference_system.model.Conference;
import com.lp.conference.conference_system.repository.RegistrationRepository;
import com.lp.conference.conference_system.repository.ConferenceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final ConferenceRepository conferenceRepository;

    public RegistrationService(RegistrationRepository registrationRepository,
                               ConferenceRepository conferenceRepository) {
        this.registrationRepository = registrationRepository;
        this.conferenceRepository = conferenceRepository;
    }

    public RegistrationResponseDTO registerParticipant(Long participantId, Long conferenceId) {
        Conference conference = conferenceRepository.findById(conferenceId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conference not found"));

        boolean alreadyRegistered = registrationRepository.findByParticipantId(participantId).stream()
                .anyMatch(r -> r.getConferenceID().equals(conferenceId));
        if (alreadyRegistered) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Participant already registered for this conference");
        }

        if (conference.getTotalSeats() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No available seats");
        }

        conference.setTotalSeats(conference.getTotalSeats() - 1);

        Registration registration = new Registration(
                null,
                "confirmed",
                LocalDate.now(),
                LocalTime.now(),
                participantId,
                conferenceId
        );

        Registration saved = registrationRepository.save(registration);
        return mapToResponseDTO(saved);
    }

    public List<RegistrationResponseDTO> getRegistrationsByParticipant(Long participantId) {
        return registrationRepository.findByParticipantId(participantId).stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    public boolean updateStatus(Long registrationId, String newStatus) {
        if (registrationRepository.findById(registrationId).isEmpty()) {
            return false;
        }
        registrationRepository.updateStatus(registrationId, newStatus);
        return true;
    }

    private RegistrationResponseDTO mapToResponseDTO(Registration registration) {
        return new RegistrationResponseDTO(
                registration.getId(),
                registration.getStatus(),
                registration.getRegistrationDate(),
                registration.getRegistrationTime(),
                registration.getParticipantID(),
                registration.getConferenceID()
        );
    }
}