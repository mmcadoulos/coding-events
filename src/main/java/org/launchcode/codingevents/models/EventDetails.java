package org.launchcode.codingevents.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class EventDetails extends AbstractEntity {

    @Size(max = 500, message = "Character limit of 500 characters")
    private String info;

    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid email address")
    private String email;

//    To set up inverse relationship
//    @OneToOne(mappedBy = "eventDetails")
//    private Event event;

    public EventDetails() {
    }

    public EventDetails(String info, String email) {
        this.info = info;
        this.email = email;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
