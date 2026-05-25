package com.lp.conference.conference_service.repository;

import com.lp.conference.conference_service.model.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Long> 
{
    
}