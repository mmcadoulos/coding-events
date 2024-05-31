package org.launchcode.codingevents.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Entity
public class Event extends AbstractEntity {

    @Size(max = 500, message = "Character limit of 500 characters")
    private String info;

//    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid email address")
    private String email;

    private EventType type;

    public Event(String name, String info, EventType type, String email) {
        super(name);
        this.info = info;
        this.type = type;
        this.email = email;
    }

    public Event() {
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

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "Event name: " + this.getName() + ", Description: " + info;
    }

}
