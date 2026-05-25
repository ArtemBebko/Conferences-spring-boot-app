package com.lp.conference.conference_system.repository;

import com.lp.conference.conference_system.model.Session;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByConference_Id(Long conferenceId);
}