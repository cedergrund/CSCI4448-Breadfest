package org.example.breadfest;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class EventBus {

    private static final EventBus instance = new EventBus();
    private final EnumMap<EventType, List<IObserver>> event_subscribers = new EnumMap<>(EventType.class);

    private EventBus() {
    }
    public static EventBus getInstance() {
        return instance;
    }

    public List<IObserver> getObserversForEvent(EventType desired_type){
        return event_subscribers.get(desired_type);
    }

    public void attach(IObserver observer, List<EventType> event_types){

        for (EventType curr_event : event_types){
            // this code adds the observer to the list at key 'event'
            event_subscribers.computeIfAbsent(curr_event, k -> new ArrayList<>()).add(observer);
        }
    }
    public void postMessage(EventType event_type, String event_description){

        List<IObserver> observers_listening_to_event = event_subscribers.get(event_type);

        if (observers_listening_to_event != null)
            for (IObserver curr_observer : observers_listening_to_event){
                curr_observer.update(event_description);
            }


    }

    public void clearObservers(){

        event_subscribers.clear();
    }


}
