package org.launchcode.codingevents.controllers;

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

    private static HashMap<String, String> events = new HashMap<>();

    @GetMapping()
    public String getEvent(Model model) {
//        List<String> events = new ArrayList<>();
//        events.add("Event 1");
//        events.add("Event 2");
//        events.add("Event 3");
//        events.add("Event 4");
        events.put("Nap-Time", "You get to take a nap guilt-free!");
        events.put("Coffee and Code", "Code with some people and drink nasty beverages");
        events.put("Bar-Be-Code", "Code with fingers stained with delicious bbq");

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
        events.put(eventName, eventInfo);
        return "redirect:/events";
    }
}
