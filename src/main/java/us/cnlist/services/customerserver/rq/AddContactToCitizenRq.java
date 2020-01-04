package us.cnlist.services.customerserver.rq;

import us.cnlist.objects.contacts.Contact;
import us.cnlist.objects.people.Citizen;

public class AddContactToCitizenRq {

    private Citizen citizen;
    private Contact contact;

    public AddContactToCitizenRq(Citizen citizen, Contact contact) {
        this.citizen = citizen;
        this.contact = contact;
    }

    public AddContactToCitizenRq() {
    }

    public Citizen getCitizen() {
        return citizen;
    }

    public Contact getContact() {
        return contact;
    }

    public void setCitizen(Citizen citizen) {
        this.citizen = citizen;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
