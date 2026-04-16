package com.lp.conference.conference_system.service;


import com.lp.conference.conference_system.dto.ParticipantRequestDTO;
import com.lp.conference.conference_system.dto.ParticipantResponseDTO;
import com.lp.conference.conference_system.model.Participant;
import com.lp.conference.conference_system.model.Registration;
import com.lp.conference.conference_system.repository.ParticipantRepository;
import com.lp.conference.conference_system.repository.RegistrationRepository;
import org.springframework.stereotype.Service;
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
        List<Registration> registrations = registrationRepository.findByConferenceId(confId);
        
        return registrations.stream()
                .map(Registration::getParticipantID)
                .map(participantRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(this::mapToResponseDTO)
                .toList();
    }

    public ParticipantResponseDTO createParticipant(ParticipantRequestDTO dto) {
        Optional<Participant> existing = participantRepository.findByEmail(dto.getEmail());
        if (existing.isPresent()) {
            throw new IllegalArgumentException("Participant with this email already exists");
        }

        Participant participant = new Participant(null, dto.getName(), dto.getEmail());
        Participant saved = participantRepository.save(participant);
        return mapToResponseDTO(saved);
    }

    public Optional<ParticipantResponseDTO> getParticipantById(Long id) {
        return participantRepository.findById(id)
                .map(this::mapToResponseDTO);
    }

    private ParticipantResponseDTO mapToResponseDTO(Participant participant) {
        return new ParticipantResponseDTO(
            participant.getId(),
            participant.getName(),
            participant.getEmail()
        );
    }
}