package us.cnlist.services.customerserver.database.entity;

import us.cnlist.objects.people.Citizen;

import javax.persistence.*;

@Entity
@Table(name = "CITIZEN")
public class CitizenProfile extends Citizen {

    private Long id;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "first_name")
    @Override
    public String getFirstName() {
        return super.getFirstName();
    }

    @Column(name = "second_name")
    @Override
    public String getSecondName() {
        return super.getSecondName();
    }
}
