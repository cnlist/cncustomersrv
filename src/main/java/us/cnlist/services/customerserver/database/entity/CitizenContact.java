package us.cnlist.services.customerserver.database.entity;

import us.cnlist.objects.contacts.Contact;
import us.cnlist.objects.contacts.ContactType;
import us.cnlist.objects.people.Citizen;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "citizen_contact")
public class CitizenContact extends Contact implements Serializable {

    private Long citizenId;

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return super.getId();
    }

    @Override
    @Column(name = "contact_type")
    @Enumerated(value = EnumType.STRING)
    public ContactType getContactType() {
        return super.getContactType();
    }

    @Override
    @Column(name = "CONTACT_VALUE")
    public String getValue() {
        return super.getValue();
    }

    @Column(name = "CITIZEN")
    public Long getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(Long citizenId) {
        this.citizenId = citizenId;
    }
}
