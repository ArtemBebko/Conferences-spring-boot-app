package com.lp.conference.conference_system.repository;


import com.lp.conference.conference_system.model.Conference;
import org.springframework.stereotype.Repository;
import java.util.*;


@Repository
public class ConferenceRepository {

    private final Map<Long, Conference> conferences = new HashMap<>();
    private long idCounter = 1;

    
    public Conference save(Conference conference) {
        if (conference.getId() == null) {
            conference.setId(idCounter++);
        }
        conferences.put(conference.getId(), conference);
        return conference;
    }

    public Optional<Conference> findById(Long id) {
        return Optional.ofNullable(conferences.get(id));
    }

    public List<Conference> findAll() {
        return new ArrayList<>(conferences.values());
    }

    public void deleteById(Long id) {
        conferences.remove(id);
    }

    public boolean existsById(Long id) {
        return conferences.containsKey(id);
    }
}
