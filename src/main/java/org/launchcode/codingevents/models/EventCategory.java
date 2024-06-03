package org.launchcode.codingevents.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class EventCategory extends AbstractEntity {

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 25, message = "Name must be between 3 and 25 characters long")
    private String name;

    @OneToMany(mappedBy = "eventCategory")
    private final List<Event> eventList = new ArrayList<Event>();

    public EventCategory() {
    }

    public EventCategory(/*@Size(min = 3, message = "Name must be at least 3 characters long")*/ String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    @Override
    public String toString() {
        return name;
    }
}
