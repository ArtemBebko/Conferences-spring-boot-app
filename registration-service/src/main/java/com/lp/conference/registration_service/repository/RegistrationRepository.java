package com.lp.conference.registration_service.repository;

import com.lp.conference.registration_service.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> 
{
    List<Registration> findByConferenceID(Long conferenceID);
    List<Registration> findByParticipant_Id(Long participantId);
}