package com.lp.conference.conference_system.model;


import java.time.LocalDate;


public class Schedule {

    private Long id;
    private LocalDate generatedAt;
    private Long conferenceID;
    
    public Schedule(Long id, LocalDate generatedAt, Long conferenceID) {
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
