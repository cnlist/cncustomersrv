package us.cnlist.services.customerserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public class Core {
    @Autowired
    private ApplicationEventPublisher eventPublisher;


}
