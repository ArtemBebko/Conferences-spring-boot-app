package com.lp.conference.conference_service.dto;


import java.time.LocalDate;
import java.util.List;


public class ScheduleResponseDTO {

    private Long id;
    private LocalDate generatedAt;
    private Long conferenceID;
    private List<SessionResponseDTO> sessions;

    public List<SessionResponseDTO> getSessions() {
        return sessions;
    }

    public void setSessions(List<SessionResponseDTO> sessions) {
        this.sessions = sessions;
    }

    public ScheduleResponseDTO() {
    }

    public ScheduleResponseDTO(Long id, LocalDate generatedAt, Long conferenceID) {
        this.id = id;
        this.generatedAt = generatedAt;
        this.conferenceID = conferenceID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDate generatedAt) {
        this.generatedAt = generatedAt;
    }

    public Long getConferenceID() {
        return conferenceID;
    }

    public void setConferenceID(Long conferenceID) {
        this.conferenceID = conferenceID;
    }
}
