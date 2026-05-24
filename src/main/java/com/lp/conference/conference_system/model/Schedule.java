package com.lp.conference.conference_system.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDate generatedAt;

    @OneToOne
    @JoinColumn(name = "conference_id")
    private Conference conference;
    
    public Schedule() {}

    public Schedule(Long id, LocalDate generatedAt, Conference conference) {
        this.id = id;
        this.generatedAt = generatedAt;
        this.conference = conference;
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

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

}