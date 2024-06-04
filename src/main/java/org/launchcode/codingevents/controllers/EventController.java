package org.launchcode.codingevents.controllers;

import jakarta.validation.Valid;
import org.launchcode.codingevents.data.DeletedData;
import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.data.TagRepository;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventCategory;
import org.launchcode.codingevents.models.Tag;
import org.launchcode.codingevents.models.dto.EventTagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @Autowired
    private TagRepository tagRepository;


    @GetMapping()
    public String displayEvents(@RequestParam(required = false) Integer categoryId, Model model) {
        if (categoryId == null) {
            model.addAttribute("title", "All Events");
            model.addAttribute("events", eventRepository.findAll());
            model.addAttribute("deletedEvents", DeletedData.getAll());
        } else {
            Optional<EventCategory> result = eventCategoryRepository.findById(categoryId);
            if (result.isEmpty()) {
                model.addAttribute("title", "Invalid category ID: " + categoryId);
            } else {
                EventCategory category = result.get();
                model.addAttribute("title", "Events with the " + category.getName() + " category");
                model.addAttribute("events", category.getEventList());
            }
        }
        return "events/index";
    }

    @GetMapping("create")
    public String renderCreateEventForm(Model model) {
        model.addAttribute(new Event());
        model.addAttribute("title", "Create Event");
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "events/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent,
                                         Errors errors,
                                         Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            model.addAttribute("categories", eventCategoryRepository.findAll());
            return "events/create";
        } else {
            eventRepository.save(newEvent);
            //
            return "redirect:/events";
        }
    }

    @GetMapping("detail")
    public String displayEventDetails(@RequestParam Integer eventId, Model model) {

        Optional<Event> result = eventRepository.findById(eventId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Event ID: " + eventId);
        } else {
            Event event = result.get();
            model.addAttribute("title", event.getName() + " Details");
            model.addAttribute("event", event);
            model.addAttribute("tags", event.getTags());
        }

        return "events/detail";
    }

    @GetMapping("add-tag")
    public String displayAddTagForm(@RequestParam Integer eventId, Model model) {
        Optional<Event> result = eventRepository.findById(eventId);
        Event event = result.get();
        model.addAttribute("title", "Add Tag to: " + event.getName());
        model.addAttribute("tags", tagRepository.findAll());
        EventTagDTO eventTag = new EventTagDTO();
        eventTag.setEvent(event);
        model.addAttribute("eventTag", eventTag);
        return "events/add-tag";
    }

    @PostMapping("add-tag")
    public String processAddTagForm(@ModelAttribute @Valid EventTagDTO eventTag,
                                    Errors errors,
                                    Model model) {
        if (!errors.hasErrors()) {
            Event event = eventTag.getEvent();
            Tag tag = eventTag.getTag();
            if (!event.getTags().contains(tag)) {
                event.addTag(tag);
                eventRepository.save(event);
            }
            return "redirect:/events/detail?eventId=" + event.getId();
        }
        return "redirect:/events/add-tag";
    }

    /* ******My views****** */

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

    @GetMapping("edit/{eventId}")
    public String processEditButtonInProgress() {
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

//    @GetMapping("restore")
//    public String displayRestoreForm(Model model) {
//        model.addAttribute("title", "Deleted Events");
//        model.addAttribute("events", DeletedData.getAll());
//        return "events/restore";
//    }

//    @PostMapping("restore")
//    public String processRestoreEvent(@RequestParam int eventId){
//        EventData.addEvent(DeletedData.getById(eventId));
//        DeletedData.removeEvent(eventId);
//        return "redirect:/events/restore";
//    }
}
