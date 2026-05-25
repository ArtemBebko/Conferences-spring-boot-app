package com.lp.conference.registration_service.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "registrations")
public class Registration {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String status;
    private LocalDate registrationDate;
    private LocalTime registrationTime; 

    @ManyToOne
    @JoinColumn(name = "participant_id")
    private Participant participant;

    private Long conferenceID;
    
    public Registration() {}

    public Registration(Long id, String status, LocalDate registrationDate, LocalTime registrationTime,
            Participant participant, Long conferenceID) {
        this.id = id;
        this.status = status;
        this.registrationDate = registrationDate;
        this.registrationTime = registrationTime;
        this.participant = participant;
        this.conferenceID = conferenceID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalTime getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(LocalTime registrationTime) {
        this.registrationTime = registrationTime;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public Long getConferenceID() {
        return conferenceID;
    }

    public void setConferenceID(Long conferenceID) {
        this.conferenceID = conferenceID;
    }
}