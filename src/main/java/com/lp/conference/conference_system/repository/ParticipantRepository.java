package com.lp.conference.conference_system.repository;


import com.lp.conference.conference_system.model.Participant;
import org.springframework.stereotype.Repository;
import java.util.*;


@Repository
public class ParticipantRepository {

    private final Map<Long, Participant> participants = new HashMap<>();
    private long idCounter = 1;

    
    public Participant save(Participant participant) {
        if (participant.getId() == null) {
            participant.setId(idCounter++);
        }
        participants.put(participant.getId(), participant);
        return participant;
    }

    public Optional<Participant> findById(Long id) {
        return Optional.ofNullable(participants.get(id));
    }

    public void deleteById(Long id) {
        participants.remove(id);
    }

    public Optional<Participant> findByEmail(String email) {
        return participants.values().stream()
                .filter(p -> p.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }
}
