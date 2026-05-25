package com.lp.conference.conference_service.repository;

import com.lp.conference.conference_service.model.Schedule;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Optional<Schedule> findByConference_Id(Long conferenceId);
}