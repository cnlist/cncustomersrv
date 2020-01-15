package us.cnlist.services.customerserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.cnlist.objects.contacts.Contact;
import us.cnlist.objects.people.Citizen;
import us.cnlist.services.customerserver.database.entity.CitizenContact;
import us.cnlist.services.customerserver.database.repository.CitizenContactRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitizenContactService {

    @Autowired
    private CitizenContactRepository citizenContactRepository;

    public List<CitizenContact> getCitizenContacts(Citizen citizen) {
        return citizenContactRepository.loadContacts(citizen.getId());
    }

    public void addContactToCitizen(Contact contact, Long citizenId) {
        Citizen citizen = new Citizen();
        citizen.setId(citizenId);
        this.addContactToCitizen(contact, citizen);
    }

    @Transactional
    public void addContactToCitizen(Contact contact, Citizen citizen) {
        CitizenContact existent = citizenContactRepository
                .findCitizenContactByCitizenIdAndContactTypeAndValue(citizen.getId(),
                        contact.getContactType(),
                        contact.getValue());
        if (existent == null) {
            CitizenContact citizenContact = new CitizenContact();
            citizenContact.setCitizenId(citizen.getId());
            citizenContact.setContactType(contact.getContactType());
            citizenContact.setValue(contact.getValue());
            citizenContactRepository.save(citizenContact);
        } else {
            //todo: what to do when exists
        }
    }

    @Transactional
    public void editCitizenContact(Contact contact) {
        CitizenContact citizenContact = citizenContactRepository.findById(contact.getId()).get();
        citizenContact.setContactType(contact.getContactType());
        citizenContact.setValue(contact.getValue());
        citizenContactRepository.save(citizenContact);
    }

    public Contact convert(CitizenContact citizenContact) {
        Contact contact = new Contact();
        contact.setId(citizenContact.getId());
        contact.setContactType(citizenContact.getContactType());
        contact.setValue(citizenContact.getValue());
        return contact;
    }

    public List<Contact> convertList(List<CitizenContact> cx) {
        return cx.stream().map(this::convert).collect(Collectors.toList());
    }

    @Transactional
    public void deleteContact (Long id) {
        citizenContactRepository.deleteById(id);
    }
}
