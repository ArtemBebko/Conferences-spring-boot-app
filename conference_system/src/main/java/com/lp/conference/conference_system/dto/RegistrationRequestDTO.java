package com.lp.conference.conference_system.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;


public class RegistrationRequestDTO {

    @NotBlank(message = "Registration status cannot be empty")
    private String status;

    @NotNull(message = "Registration date is required")
    private LocalDate registrationDate;

    @NotNull(message = "Registration time is required")
    private LocalTime registrationTime;

    @NotNull(message = "Participant ID is required")
    private Long participantID;

    @NotNull(message = "Conference ID is required")
    private Long conferenceID;

    public RegistrationRequestDTO(@NotBlank(message = "Registration status cannot be empty") String status,
            @NotNull(message = "Registration date is required") LocalDate registrationDate,
            @NotNull(message = "Registration time is required") LocalTime registrationTime,
            @NotNull(message = "Participant ID is required") Long participantID,
            @NotNull(message = "Conference ID is required") Long conferenceID) {
        this.status = status;
        this.registrationDate = registrationDate;
        this.registrationTime = registrationTime;
        this.participantID = participantID;
        this.conferenceID = conferenceID;
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
