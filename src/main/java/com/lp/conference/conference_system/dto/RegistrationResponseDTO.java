package com.lp.conference.conference_system.dto;


import java.time.LocalDate;
import java.time.LocalTime;


public class RegistrationResponseDTO {

    private Long id;
    private String status;
    private LocalDate registrationDate;
    private LocalTime registrationTime;
    private Long participantID;
    private Long conferenceID;

    public RegistrationResponseDTO(Long id, String status, LocalDate registrationDate, LocalTime registrationTime,
                                   Long participantID, Long conferenceID) {
        this.id = id;
        this.status = status;
        this.registrationDate = registrationDate;
        this.registrationTime = registrationTime;
        this.participantID = participantID;
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

    public Long getParticipantID() {
        return participantID;
    }

    public void setParticipantID(Long participantID) {
        this.participantID = participantID;
    }

    public Long getConferenceID() {
        return conferenceID;
    }

    public void setConferenceID(Long conferenceID) {
        this.conferenceID = conferenceID;
    }
}
