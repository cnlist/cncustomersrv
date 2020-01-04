package us.cnlist.services.customerserver.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import us.cnlist.objects.people.Citizen;
import us.cnlist.services.customerserver.database.entity.CitizenContact;
import us.cnlist.services.customerserver.services.CitizenContactService;

import java.util.List;

@Controller
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private CitizenContactService contactService;

    @GetMapping
    @ResponseBody
    public List<CitizenContact> getContacts(@RequestParam(name = "citizen") Long citizenId) {
        Citizen c = new Citizen();
        c.setId(citizenId);
        return contactService.getCitizenContacts(c);
    }

}
