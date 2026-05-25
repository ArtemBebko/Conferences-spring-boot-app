package com.lp.conference.conference_service.dto;


import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;


public class ScheduleRequestDTO {

    @NotNull(message = "Schedule generation date is required")
    private LocalDate generatedAt;

    @NotNull(message = "Conference ID is required")
    private Long conferenceID;

    public ScheduleRequestDTO(
            @NotNull(message = "Schedule generation date is required") LocalDate generatedAt,
            @NotNull(message = "Conference ID is required") Long conferenceID) {
        this.generatedAt = generatedAt;
        this.conferenceID = conferenceID;
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
