package us.cnlist.services.customerserver.controller;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Controller;
import us.cnlist.objects.messages.rq.ContactToCitizenRq;
import us.cnlist.services.customerserver.services.CitizenContactService;

@Controller
public class ContactJmsController {

    private final CitizenContactService citizenContactService;

    public ContactJmsController(CitizenContactService citizenContactService) {
        this.citizenContactService = citizenContactService;
    }

    @JmsListener(destination = "citizen.contact.add")
    public void addContactToCitizen(ContactToCitizenRq rq) {
        citizenContactService.addContactToCitizen(rq.getContact(), rq.getCitizen());
    }

    @JmsListener(destination = "citizen.contact.edit")
    public void editContact(ContactToCitizenRq rq) {
        citizenContactService.editCitizenContact(rq.getContact());
    }
}
