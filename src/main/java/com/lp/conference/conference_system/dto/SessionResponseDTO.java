package com.lp.conference.conference_system.dto;


import java.time.LocalTime;


public class SessionResponseDTO {

    private Long id;
    private String title;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long conferenceID;
    private Long speakerID;

    public SessionResponseDTO(Long id, String title, LocalTime startTime, LocalTime endTime,
                              Long conferenceID, Long speakerID) {
        this.id = id;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.conferenceID = conferenceID;
        this.speakerID = speakerID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Long getConferenceID() {
        return conferenceID;
    }

    public void setConferenceID(Long scheduleID) {
        this.conferenceID = scheduleID;
    }

    public Long getSpeakerID() {
        return speakerID;
    }

    public void setSpeakerID(Long speakerID) {
        this.speakerID = speakerID;
    }
}
