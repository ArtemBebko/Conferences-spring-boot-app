package com.lp.conference.registration_service.service;

import com.lp.conference.registration_service.client.ConferenceClient;
import com.lp.conference.registration_service.dto.ConferenceResponseDTO;
import com.lp.conference.registration_service.dto.RegistrationResponseDTO;
import com.lp.conference.registration_service.model.Participant;
import com.lp.conference.registration_service.model.Registration;
import com.lp.conference.registration_service.repository.ParticipantRepository;
import com.lp.conference.registration_service.repository.RegistrationRepository;
import feign.FeignException;
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
    private final ParticipantRepository participantRepository;
    private final ConferenceClient conferenceClient;

    public RegistrationService(RegistrationRepository registrationRepository,
                               ParticipantRepository participantRepository,
                               ConferenceClient conferenceClient) {
        this.registrationRepository = registrationRepository;
        this.participantRepository = participantRepository;
        this.conferenceClient = conferenceClient;
    }

    @Transactional
    public RegistrationResponseDTO registerParticipant(Long participantId, Long conferenceId) {

        Participant participant = participantRepository.findById(participantId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Participant not found"));


        ConferenceResponseDTO conference;
        try {
            conference = conferenceClient.getConferenceById(conferenceId);
        } catch (FeignException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conference not found in Conference Service");
        } catch (FeignException e) {

            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Conference Service is temporarily unavailable");
        }


        boolean alreadyRegistered = registrationRepository.findByParticipant_Id(participantId).stream()
                .anyMatch(r -> r.getConferenceID().equals(conferenceId));
        if (alreadyRegistered) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Participant already registered for this conference");
        }


        if (conference.getTotalSeats() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No available seats");
        }


        try {
            conferenceClient.decreaseSeats(conferenceId);
        } catch (FeignException e) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Failed to reserve a seat. Conference Service is unavailable");
        }


        Registration registration = new Registration(null, "confirmed", LocalDate.now(), LocalTime.now(), participant, conferenceId);
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
                registration.getParticipant().getId(), registration.getConferenceID());
    }
}