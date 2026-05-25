package com.lp.conference.registration_service.service;

import com.lp.conference.registration_service.dto.ParticipantRequestDTO;
import com.lp.conference.registration_service.dto.ParticipantResponseDTO;
import com.lp.conference.registration_service.model.Participant;
import com.lp.conference.registration_service.model.Registration;
import com.lp.conference.registration_service.repository.ParticipantRepository;
import com.lp.conference.registration_service.repository.RegistrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;
    private final RegistrationRepository registrationRepository;

    public ParticipantService(ParticipantRepository participantRepository, RegistrationRepository registrationRepository) {
        this.participantRepository = participantRepository;
        this.registrationRepository = registrationRepository;
    }

    public List<ParticipantResponseDTO> getParticipantsByConference(Long confId) {
        return registrationRepository.findByConferenceID(confId).stream()
                .map(Registration::getParticipant)
                .map(this::mapToResponseDTO)
                .toList();
    }

    @Transactional
    public ParticipantResponseDTO createParticipant(ParticipantRequestDTO dto) {
        Optional<Participant> existing = participantRepository.findByEmail(dto.getEmail());
        if (existing.isPresent()) {
            throw new IllegalArgumentException("Participant with this email already exists");
        }
        Participant participant = new Participant(null, dto.getName(), dto.getEmail());
        return mapToResponseDTO(participantRepository.save(participant));
    }

    public Optional<ParticipantResponseDTO> getParticipantById(Long id) {
        return participantRepository.findById(id).map(this::mapToResponseDTO);
    }

    private ParticipantResponseDTO mapToResponseDTO(Participant participant) {
        return new ParticipantResponseDTO(participant.getId(), participant.getName(), participant.getEmail());
    }
}