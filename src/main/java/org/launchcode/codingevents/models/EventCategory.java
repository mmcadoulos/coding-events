package org.launchcode.codingevents.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;

@Entity
public class EventCategory extends AbstractEntity {

    public EventCategory() {
    }

    public EventCategory(@Size(min = 3, message = "Name must be at least 3 characters long") String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Name: " + this.getName();
    }
}
