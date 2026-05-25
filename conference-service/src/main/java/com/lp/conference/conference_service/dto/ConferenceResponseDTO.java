package com.lp.conference.conference_service.dto;


import java.time.LocalDate;


public class ConferenceResponseDTO {
    
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer totalSeats;

    public ConferenceResponseDTO(Long id, String name, LocalDate startDate, LocalDate endDate, Integer totalSeats) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalSeats = totalSeats;
    }

    public ConferenceResponseDTO()
    {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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