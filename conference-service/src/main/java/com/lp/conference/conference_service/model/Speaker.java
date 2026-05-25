package com.lp.conference.conference_service.model;

import jakarta.persistence.*;

@Entity
@Table(name = "speakers")
public class Speaker {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String email;
    private String bio;
    private String specialization;

    public Speaker() {}

    public Speaker(Long id, String name, String email, String bio, String specialization) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.bio = bio;
        this.specialization = specialization;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

}