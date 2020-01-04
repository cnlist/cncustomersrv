package us.cnlist.services.customerserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Controller;
import us.cnlist.objects.contacts.Contact;
import us.cnlist.services.customerserver.rq.AddContactToCitizenRq;
import us.cnlist.services.customerserver.services.CitizenContactService;

@Controller
public class ContactJmsController {

    @Autowired
    private CitizenContactService citizenContactService;

    @JmsListener(destination = "citizen.contact.add")
    public void addContactToCitizen (AddContactToCitizenRq rq){
        citizenContactService.addContactToCitizen(rq.getContact(), rq.getCitizen());
    }

}
