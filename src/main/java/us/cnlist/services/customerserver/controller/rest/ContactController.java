package us.cnlist.services.customerserver.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import us.cnlist.objects.contacts.Contact;
import us.cnlist.objects.messages.rq.ContactToCitizenRq;
import us.cnlist.objects.people.Citizen;
import us.cnlist.services.customerserver.services.CitizenContactService;

import java.util.List;

@Controller
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private CitizenContactService contactService;

    @GetMapping
    @ResponseBody
    public List<Contact> getContacts(@RequestParam(name = "citizen") Long citizenId) {
        Citizen c = new Citizen();
        c.setId(citizenId);
        return contactService.convertList(contactService.getCitizenContacts(c));
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void createContact(@RequestBody ContactToCitizenRq rq) {
        contactService.addContactToCitizen(rq.getContact(), rq.getCitizen());
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void editContact(@RequestBody Contact contact) {
        contactService.editCitizenContact(contact);
    }

    @GetMapping("/delete")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void deleteContact(@RequestParam("id") Long contactId) {
        contactService.deleteContact(contactId);
    }

}
