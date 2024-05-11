package org.launchcode.codingevents.controllers;

import jakarta.validation.Valid;
import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

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
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent,
                                         Errors errors,
                                         Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            model.addAttribute("errorMsg", "Bad data!!");
            model.addAttribute("errors", errors.getAllErrors());
            return "events/create";
        }
        EventData.addEvent(newEvent);
        return "redirect:/events";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventForm(@RequestParam(required = false) int[] eventIds) { // could use int[] or ArrayList<Integer> or Collection<Integer>
        if (eventIds != null) {
            for (int id : eventIds) {
                EventData.removeEvent(id);
            }
        }
        return "redirect:/events";
    }

    @PostMapping("deleteEvent")
    public String processDeleteEventButton(@RequestParam int eventId) { // could use int[] or ArrayList<Integer> or Collection<Integer>
        EventData.removeEvent(eventId);
        return "redirect:/events";
    }

    @GetMapping("edit/{eventId}")
    public String displayEditForm(Model model, @PathVariable int eventId) {
        Event eventToEdit = EventData.getById(eventId);
        model.addAttribute("event", eventToEdit);
        String title = "Edit Event: " + eventToEdit.getName() + " (id=" + eventToEdit.getId() + ")";
        model.addAttribute("title", title);
        return "events/edit";
    }

    @PostMapping("edit")
    public String processEditForm(int eventId, String name, String info, String email) {
        Event eventToEdit = EventData.getById(eventId);
        if (!eventToEdit.getName().equals(name) && !name.isBlank()) {
            eventToEdit.setName(name);
        }
        if (!eventToEdit.getInfo().equals(info) && !info.isBlank()) {
            eventToEdit.setInfo(info);
        }
        if (!email.isBlank()){
            eventToEdit.setEmail(email);
        }
        return "redirect:/events";
    }
}
