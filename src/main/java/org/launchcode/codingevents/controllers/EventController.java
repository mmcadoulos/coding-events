package org.launchcode.codingevents.controllers;

import jakarta.validation.Valid;
import org.launchcode.codingevents.data.DeletedData;
//import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping()
    public String displayAllEvents(Model model) {
        model.addAttribute("title", "All Events");
        model.addAttribute("events", eventRepository.findAll());
        model.addAttribute("deletedEvents", DeletedData.getAll());
        return "events/index";
    }

    @GetMapping("create")
    public String renderCreateEventForm(Model model) {
        model.addAttribute(new Event());
        model.addAttribute("title", "Create Event");
        model.addAttribute("types", EventType.values());
        return "events/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent,
                                         Errors errors,
                                         Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            model.addAttribute("types", EventType.values());
            return "events/create";
        } else {
            eventRepository.save(newEvent);
            return "redirect:/events";
        }
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", eventRepository.findAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventForm(@RequestParam(required = false) int[] eventIds) { // could use int[] or ArrayList<Integer> or Collection<Integer>
        if (eventIds != null) {
            for (int id : eventIds) {
//                DeletedData.addEvent(EventData.getById(id));
//                EventData.removeEvent(id);
                eventRepository.deleteById(id);
            }
        }
        return "redirect:/events";
    }

    @PostMapping("deleteEvent")
    public String processDeleteEventButton(@RequestParam int eventId) {
//        DeletedData.addEvent(EventData.getById(eventId));
        eventRepository.deleteById(eventId);
        return "redirect:/events";
    }

//    @GetMapping("edit/{eventId}")
//    public String displayEditForm(Model model, @PathVariable int eventId) {
//        Event eventToEdit = EventData.getById(eventId);
//        model.addAttribute("event", eventToEdit);
//        String title = "Edit Event: " + eventToEdit.getName() + " (id=" + eventToEdit.getId() + ")";
//        model.addAttribute("title", title);
//        model.addAttribute("types", EventType.values());
//        return "events/edit";
//    }
//
//    @PostMapping("edit")
//    public String processEditForm(int eventId, String name, String info, String email, EventType type) {
//        Event eventToEdit = EventData.getById(eventId);
//        if (!eventToEdit.getName().equals(name) && !name.isBlank()) {
//            eventToEdit.setName(name);
//        }
//        if (!eventToEdit.getInfo().equals(info) && !info.isBlank()) {
//            eventToEdit.setInfo(info);
//        }
//        if (!email.isBlank()) {
//            eventToEdit.setEmail(email);
//        }
//        eventToEdit.setType(type);
//        return "redirect:/events";
//    }
//
//    @GetMapping("restore")
//    public String displayRestoreForm(Model model) {
//        model.addAttribute("title", "Deleted Events");
//        model.addAttribute("events", DeletedData.getAll());
//        return "events/restore";
//    }
//
//    @PostMapping("restore")
//    public String processRestoreEvent(@RequestParam int eventId){
//        EventData.addEvent(DeletedData.getById(eventId));
//        DeletedData.removeEvent(eventId);
//        return "redirect:/events/restore";
//    }
}
