package com.lp.conference.conference_system.repository;

import com.lp.conference.conference_system.model.Registration;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> 
{
    List<Registration> findByConference_Id(Long conferenceId);
    List<Registration> findByParticipant_Id(Long participantId);
}