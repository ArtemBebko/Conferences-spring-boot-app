package com.lp.conference.conference_service.repository;

import com.lp.conference.conference_service.model.Session;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByConference_Id(Long conferenceId);
}