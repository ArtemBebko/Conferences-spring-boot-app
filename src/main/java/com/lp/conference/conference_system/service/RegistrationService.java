package com.lp.conference.conference_system.service;

import com.lp.conference.conference_system.dto.RegistrationResponseDTO;
import com.lp.conference.conference_system.model.Conference;
import com.lp.conference.conference_system.model.Participant;
import com.lp.conference.conference_system.model.Registration;
import com.lp.conference.conference_system.repository.ConferenceRepository;
import com.lp.conference.conference_system.repository.ParticipantRepository;
import com.lp.conference.conference_system.repository.RegistrationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final ConferenceRepository conferenceRepository;
    private final ParticipantRepository participantRepository;

    public RegistrationService(RegistrationRepository registrationRepository, ConferenceRepository conferenceRepository, ParticipantRepository participantRepository) {
        this.registrationRepository = registrationRepository;
        this.conferenceRepository = conferenceRepository;
        this.participantRepository = participantRepository;
    }

    @Transactional
    public RegistrationResponseDTO registerParticipant(Long participantId, Long conferenceId) {
        Conference conference = conferenceRepository.findById(conferenceId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conference not found"));
        
        Participant participant = participantRepository.findById(participantId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Participant not found"));

        boolean alreadyRegistered = registrationRepository.findByParticipant_Id(participantId).stream()
                .anyMatch(r -> r.getConference().getId().equals(conferenceId));
        if (alreadyRegistered) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Participant already registered for this conference");
        }
        if (conference.getTotalSeats() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No available seats");
        }

        conference.setTotalSeats(conference.getTotalSeats() - 1);
        conferenceRepository.save(conference);

        Registration registration = new Registration(null, "confirmed", LocalDate.now(), LocalTime.now(), participant, conference);
        return mapToResponseDTO(registrationRepository.save(registration));
    }

    public List<RegistrationResponseDTO> getRegistrationsByParticipant(Long participantId) {
        return registrationRepository.findByParticipant_Id(participantId).stream()
                .map(this::mapToResponseDTO).toList();
    }

    @Transactional
    public boolean updateStatus(Long registrationId, String newStatus) {
        return registrationRepository.findById(registrationId).map(reg -> {
            reg.setStatus(newStatus);
            registrationRepository.save(reg);
            return true;
        }).orElse(false);
    }

    private RegistrationResponseDTO mapToResponseDTO(Registration registration) {
        return new RegistrationResponseDTO(registration.getId(), registration.getStatus(),
                registration.getRegistrationDate(), registration.getRegistrationTime(),
                registration.getParticipant().getId(), registration.getConference().getId());
    }
}