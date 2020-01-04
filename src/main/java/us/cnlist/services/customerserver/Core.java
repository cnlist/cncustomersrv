package us.cnlist.services.customerserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import us.cnlist.services.customerserver.events.Event;

public class Core {
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void event(Event e) {
        eventPublisher.publishEvent(e);
    }
}
