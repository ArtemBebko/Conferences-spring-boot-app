package com.lp.conference.conference_system.repository;


import com.lp.conference.conference_system.model.Session;
import org.springframework.stereotype.Repository;
import java.util.*;


@Repository
public class SessionRepository {

    private final Map<Long, Session> sessions = new HashMap<>();
    private long idCounter = 1;


    public Session save(Session session) {
        if (session.getId() == null) {
            session.setId(idCounter++);
        }
        sessions.put(session.getId(), session);
        return session;
    }

    public Optional<Session> findById(Long id) {
        return Optional.ofNullable(sessions.get(id));
    }

    public void deleteById(Long id) {
        sessions.remove(id);
    }

    public List<Session> findByConferenceId(Long scheduleId) {
        List<Session> result = new ArrayList<>();
        for (Session s : sessions.values()) {
            if (Objects.equals(s.getConferenceID(), scheduleId)) {
                result.add(s);
            }
        }
        return result;
    }
}