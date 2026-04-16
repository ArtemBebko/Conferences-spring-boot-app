package com.lp.conference.conference_system.repository;


import com.lp.conference.conference_system.model.Schedule;
import org.springframework.stereotype.Repository;
import java.util.*;


@Repository
public class ScheduleRepository {

    private final Map<Long, Schedule> schedules = new HashMap<>();
    private long idCounter = 1;


    public Schedule save(Schedule schedule) {
        if (schedule.getId() == null) {
            schedule.setId(idCounter++);
        }
        schedules.put(schedule.getId(), schedule);
        return schedule;
    }

    public Optional<Schedule> findById(Long id) {
        return Optional.ofNullable(schedules.get(id));
    }

    public void deleteById(Long id) {
        schedules.remove(id);
    }

    public List<Schedule> findAll() {
        return new ArrayList<>(schedules.values());
    }

    public Optional<Schedule> findByConferenceId(Long conferenceId) {
        return schedules.values().stream()
                .filter(s -> Objects.equals(s.getConferenceID(), conferenceId))
                .findFirst();
    }
}