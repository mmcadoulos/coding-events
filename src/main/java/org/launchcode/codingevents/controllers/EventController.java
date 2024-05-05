package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {

    private static ArrayList<Event> events = new ArrayList<>();

    @GetMapping()
    public String getEvent(Model model) {
//        List<String> events = new ArrayList<>();
//        events.add("Event 1");
//        events.add("Event 2");
//        events.add("Event 3");
//        events.add("Event 4");
//        Event event1 = new Event("Nap-Time", "You get to take a nap guilt-free!");
//        Event event2 = new Event("Coffee and Code", "Code with some people and drink nasty beverages");
//        Event event3 = new Event("Bar-Be-Code", "Code with fingers stained with delicious bbq");
//        events.add(event1);
//        events.add(event2);
//        events.add(event3);

        model.addAttribute("events", events);

        return "events/index";
    }

    // lives at /events/create
    @GetMapping("create")
    public String renderCreateEventForm() {
        return "events/create";
    }

    @PostMapping("create")
    public String createEvent(@RequestParam String eventName, @RequestParam String eventInfo) {

        if (eventName.isBlank()) {
            return "redirect:/events";
        } else if (eventInfo.isBlank()) {
            events.add(new Event(eventName));
        } else {
            events.add(new Event(eventName, eventInfo));
        }
        return "redirect:/events";
    }
}
