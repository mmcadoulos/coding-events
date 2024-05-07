package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collection;


@Controller
@RequestMapping("events")
public class EventController {

    @GetMapping()
    public String displayAllEvents(Model model) {
        model.addAttribute("title", "All Events");
        model.addAttribute("events", EventData.getAll());
        return "events/index";
    }

    @GetMapping("create")
    public String renderCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        return "events/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@RequestParam String eventName, @RequestParam String eventInfo) {
        if (eventName.isBlank()) {
            return "redirect:/events";
        } else if (eventInfo.isBlank()) {
            EventData.addEvent(new Event(eventName));
        } else {
            EventData.addEvent(new Event(eventName, eventInfo));
        }
        return "redirect:/events";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventForm(@RequestParam(required = false) Collection<Integer> eventIds) { // could use int[] or ArrayList<Integer>
        if (eventIds != null) {
            for (Integer id : eventIds) {
                EventData.removeEvent(id);
            }
        }
        return "redirect:/events";
    }
}
