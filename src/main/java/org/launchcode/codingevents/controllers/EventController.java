package org.launchcode.codingevents.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {

    private static List<String> events = new ArrayList<>();

    @GetMapping()
    public String getEvent(Model model){
//        List<String> events = new ArrayList<>();
//        events.add("Event 1");
//        events.add("Event 2");
//        events.add("Event 3");
//        events.add("Event 4");

        model.addAttribute("events", events);

        return "events/index";
    }

    // lives at /events/create
    @GetMapping("create")
    public String renderCreateEventForm(){
        return "events/create";
    }

@PostMapping("create")
    public String createEvent(@RequestParam String eventName){
        events.add(eventName);
        return "redirect:/events";
}
}
