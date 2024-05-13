package org.launchcode.codingevents.models;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.Objects;

public class Event {

    private int id;
    private static int nextId = 1;

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 25, message = "Name must be between 3 and 25 characters long")
    private String name;

    @Size(max = 500, message = "Character limit of 500 characters")
    private String info;

    @NotBlank(message = "Location is required")
    @NotNull(message = "Location is required")
    private String location;

    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid email address")
    private String email;

    @AssertTrue(message = "Must be 'True'")
    private boolean mustRegister;

    @Min(value = 1, message = "Number of attendees must be more than zero")
    private int numOfAttendees;

    @NotNull(message = "Date is required")
    @FutureOrPresent(message = "Date cannot be in the past")
    private LocalDate date;

    private String color = "#8024db";

    private EventType type;

    public Event(LocalDate date, String name, String info, String location, EventType type, String email, boolean mustRegister, int numOfAttendees, String color) {
        this();
        this.date = date;
        this.name = name;
        this.info = info;
        this.location = location;
        this.type = type;
        this.email = email;
        this.mustRegister = mustRegister;
        this.numOfAttendees = numOfAttendees;
        this.color = color;
    }

    public Event() {
        this.id = nextId;
        nextId++;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isMustRegister() {
        return mustRegister;
    }

    public void setMustRegister(boolean mustRegister) {
        this.mustRegister = mustRegister;
    }

    public int getNumOfAttendees() {
        return numOfAttendees;
    }

    public void setNumOfAttendees(int numOfAttendees) {
        this.numOfAttendees = numOfAttendees;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
