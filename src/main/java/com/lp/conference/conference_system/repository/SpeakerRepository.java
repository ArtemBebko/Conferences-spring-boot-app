package com.lp.conference.conference_system.repository;


import com.lp.conference.conference_system.model.Speaker;
import org.springframework.stereotype.Repository;
import java.util.*;


@Repository
public class SpeakerRepository {

    private final Map<Long, Speaker> speakers = new HashMap<>();
    private long idCounter = 1;


    public Speaker save(Speaker speaker) {
        if (speaker.getId() == null) {
            speaker.setId(idCounter++);
        }
        speakers.put(speaker.getId(), speaker);
        return speaker;
    }

    public Optional<Speaker> findById(Long id) {
        return Optional.ofNullable(speakers.get(id));
    }
}