package com.lp.conference.conference_system.service;

import com.lp.conference.conference_system.dto.ConferenceRequestDTO;
import com.lp.conference.conference_system.dto.ConferenceResponseDTO;
import com.lp.conference.conference_system.model.Conference;
import com.lp.conference.conference_system.repository.ConferenceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class ConferenceService {

    private final ConferenceRepository conferenceRepository;

    public ConferenceService(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    @Transactional
    public ConferenceResponseDTO createConference(ConferenceRequestDTO dto) {
        Conference conference = new Conference(null, dto.getName(), dto.getStartDate(), dto.getEndDate(), dto.getTotalSeats());
        Conference savedConference = conferenceRepository.save(conference);
        return mapToResponseDTO(savedConference);
    }

    @Transactional
    public ConferenceResponseDTO updateConference(Long id, ConferenceRequestDTO dto) {
        return conferenceRepository.findById(id).map(existing -> {
            existing.setName(dto.getName());
            existing.setStartDate(dto.getStartDate());
            existing.setEndDate(dto.getEndDate());
            existing.setTotalSeats(dto.getTotalSeats());
            return mapToResponseDTO(conferenceRepository.save(existing));
        }).orElseThrow(() -> new IllegalArgumentException("Conference not found with id: " + id));
    }
    
    public Optional<ConferenceResponseDTO> getConferenceById(Long id) {
        return conferenceRepository.findById(id).map(this::mapToResponseDTO);
    }

    public Page<ConferenceResponseDTO> getAllConferences(Pageable pageable) {
        return conferenceRepository.findAll(pageable).map(this::mapToResponseDTO);
    }

    @Transactional
    public void deleteConference(Long id) {
        if (!conferenceRepository.existsById(id)) {
            throw new IllegalArgumentException("Cannot delete: Conference not found with id: " + id);
        }
        conferenceRepository.deleteById(id);
    }

    public boolean hasAvailableSeats(Long conferenceId) {
        return conferenceRepository.findById(conferenceId)
                .map(conf -> conf.getTotalSeats() > 0)
                .orElse(false);
    }

    private ConferenceResponseDTO mapToResponseDTO(Conference conference) {
        return new ConferenceResponseDTO(conference.getId(), conference.getName(), conference.getStartDate(),
            conference.getEndDate(), conference.getTotalSeats());
    }
}