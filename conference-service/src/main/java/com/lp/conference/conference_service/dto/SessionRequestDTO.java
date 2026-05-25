package com.lp.conference.conference_service.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;


public class SessionRequestDTO {

    @NotBlank(message = "Session title cannot be empty")
    private String title;

    @NotNull(message = "Start time is required")
    private LocalTime startTime;

    @NotNull(message = "End time is required")
    private LocalTime endTime;

    @NotNull(message = "Schedule ID is required")
    private Long conferenceID;

    @NotNull(message = "Speaker ID is required")
    private Long speakerID;

    public SessionRequestDTO(
            @NotBlank(message = "Session title cannot be empty") String title,
            @NotNull(message = "Start time is required") LocalTime startTime,
            @NotNull(message = "End time is required") LocalTime endTime,
            @NotNull(message = "Schedule ID is required") Long conferenceID,
            @NotNull(message = "Speaker ID is required") Long speakerID) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.conferenceID = conferenceID;
        this.speakerID = speakerID;
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
