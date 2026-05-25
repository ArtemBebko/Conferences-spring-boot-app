package com.lp.conference.registration_service.repository;

import com.lp.conference.registration_service.model.Participant;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> 
{
    Optional<Participant> findByEmail(String email);
}