//package org.launchcode.codingevents.data;
//
//import org.launchcode.codingevents.models.Event;
//
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//
//public class EventData {
//
//    private static final Map<Integer, Event> events = new HashMap<>();
//
//    public static Collection<Event> getAll() {
//        return events.values();
//    }
//
//    public static Event getById(int id) {
//        return events.get(id);
//    }
//
//    public static void addEvent(Event anEvent) {
//        events.put(anEvent.getId(), anEvent);
//    }
//
//    public static void removeEvent(int id) {
//        if (events.containsKey(id)){events.remove(id);}
//    }
//}
