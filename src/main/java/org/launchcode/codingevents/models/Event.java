package org.launchcode.codingevents.models;

import java.util.Objects;

public class Event {

    private final int id;
    private static int nextId = 1;

    private String name;
    private String info;

    public Event(String name) {
        this.name = name;
        this.id = nextId;
        nextId++;
    }

    public Event(String name, String info) {
        this(name);
        this.info = info;
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
