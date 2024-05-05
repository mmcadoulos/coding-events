package org.launchcode.codingevents.models;

public class Event {

    private String name;
    private String info;

    public Event(String name) {
        this.name = name;
    }

    public Event(String name, String info) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Event name: " + name + ", Description: " + info;
    }
}
