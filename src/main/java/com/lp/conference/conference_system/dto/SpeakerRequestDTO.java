package com.lp.conference.conference_system.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public class SpeakerRequestDTO {

    @NotBlank(message = "Speaker name cannot be empty")
    private String name;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    private String bio;
    private String specialization;

    public SpeakerRequestDTO(
            @NotBlank(message = "Speaker name cannot be empty") String name,
            @NotBlank(message = "Email cannot be empty") @Email(message = "Invalid email format") String email,
            String bio,
            String specialization) {
        this.name = name;
        this.email = email;
        this.bio = bio;
        this.specialization = specialization;
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
