package com.lp.conference.conference_system.repository;

import com.lp.conference.conference_system.model.Registration;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class RegistrationRepository {

    private final Map<Long, Registration> registrations = new HashMap<>();
    private long idCounter = 1;

    public Registration save(Registration registration) {
        if (registration.getId() == null) {
            registration.setId(idCounter++);
        }
        registrations.put(registration.getId(), registration);
        return registration;
    }

    public Optional<Registration> findById(Long id) {
        return Optional.ofNullable(registrations.get(id));
    }

    public List<Registration> findByParticipantId(Long participantId) {
        List<Registration> result = new ArrayList<>();
        for (Registration r : registrations.values()) {
            if (Objects.equals(r.getParticipantID(), participantId)) {
                result.add(r);
            }
        }
        return result;
    }

    public List<Registration> findByConferenceId(Long conferenceId) {
        List<Registration> result = new ArrayList<>();
        for (Registration r : registrations.values()) {
            if (Objects.equals(r.getConferenceID(), conferenceId)) { 
                result.add(r);
            }
        }
        return result;
    }

    public void updateStatus(Long id, String newStatus) {
        Registration registration = registrations.get(id);
        if (registration != null) {
            registration.setStatus(newStatus);
        }
    }
}