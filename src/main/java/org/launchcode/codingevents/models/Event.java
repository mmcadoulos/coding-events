package org.launchcode.codingevents.models;

import com.fasterxml.jackson.databind.annotation.EnumNaming;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import jakarta.persistence.Entity;
import java.util.Objects;

@Entity
public class Event {

    @Id
    @GeneratedValue
    private int id;

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 25, message = "Name must be between 3 and 25 characters long")
    private String name;

    @Size(max = 500, message = "Character limit of 500 characters")
    private String info;

//    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid email address")
    private String email;

    private EventType type;

    public Event(String name, String info, EventType type, String email) {
        this.name = name;
        this.info = info;
        this.type = type;
        this.email = email;
    }

    public Event() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Event name: " + name + ", Description: " + info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
