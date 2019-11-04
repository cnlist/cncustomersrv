package us.cnlist.services.customerserver.events;

import org.springframework.context.ApplicationEvent;

public class Event extends ApplicationEvent {
    public Event(Object source) {
        super(source);
    }
}
