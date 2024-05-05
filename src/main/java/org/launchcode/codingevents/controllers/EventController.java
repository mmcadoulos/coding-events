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

    private static HashMap<Event, String> events = new HashMap<>();

    @GetMapping()
    public String getEvent(Model model) {
//        List<String> events = new ArrayList<>();
//        events.add("Event 1");
//        events.add("Event 2");
//        events.add("Event 3");
//        events.add("Event 4");
        Event event1 = new Event("Nap-Time");
        Event event2 = new Event("Coffee and Code");
        Event event3 = new Event("Bar-Be-Code");
        events.put(event1, "You get to take a nap guilt-free!");
        events.put(event2, "Code with some people and drink nasty beverages");
        events.put(event3, "Code with fingers stained with delicious bbq");

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
        events.put(new Event(eventName), eventInfo);
        return "redirect:/events";
    }
}
