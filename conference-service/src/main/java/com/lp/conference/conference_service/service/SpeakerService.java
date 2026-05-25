package com.lp.conference.conference_service.service;

import com.lp.conference.conference_service.dto.SpeakerRequestDTO;
import com.lp.conference.conference_service.dto.SpeakerResponseDTO;
import com.lp.conference.conference_service.model.Speaker;
import com.lp.conference.conference_service.repository.SpeakerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class SpeakerService {

    private final SpeakerRepository speakerRepository;

    public SpeakerService(SpeakerRepository speakerRepository) {
        this.speakerRepository = speakerRepository;
    }

    @Transactional
    public SpeakerResponseDTO createSpeaker(SpeakerRequestDTO dto) {
        Speaker speaker = new Speaker(null, dto.getName(), dto.getEmail(), dto.getBio(), dto.getSpecialization());
        return mapToResponseDTO(speakerRepository.save(speaker));
    }

    public Optional<SpeakerResponseDTO> getSpeakerById(Long id) {
        return speakerRepository.findById(id).map(this::mapToResponseDTO);
    }

    private SpeakerResponseDTO mapToResponseDTO(Speaker speaker) {
        return new SpeakerResponseDTO(speaker.getId(), speaker.getName(), speaker.getEmail(),
                speaker.getBio(), speaker.getSpecialization());
    }
}