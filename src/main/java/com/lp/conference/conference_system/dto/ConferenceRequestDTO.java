package com.lp.conference.conference_system.dto;


import jakarta.validation.constraints.*;
import java.time.LocalDate;


public class ConferenceRequestDTO {

    @NotBlank(message = "Conference name cannot be empty")
    private String name;

    @NotNull(message = "Start date is required")
    @FutureOrPresent(message = "Conference cannot start in the past")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    @Min(value = 1, message = "Number of seats must be at least 1")
    private Integer totalSeats;

    public ConferenceRequestDTO(@NotBlank(message = "Conference name cannot be empty") String name,
            @NotNull(message = "Start date is required") @FutureOrPresent(message = "Conference cannot start in the past") LocalDate startDate,
            @NotNull(message = "End date is required") LocalDate endDate,
            @Min(value = 1, message = "Number of seats must be at least 1") Integer totalSeats) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalSeats = totalSeats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }
    
}