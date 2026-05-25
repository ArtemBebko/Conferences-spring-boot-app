package com.lp.conference.conference_system.repository;

import com.lp.conference.conference_system.model.Schedule;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Optional<Schedule> findByConference_Id(Long conferenceId);
}