package us.cnlist.services.customerserver.database.entity;

import us.cnlist.objects.geo.Address;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class DbAddress extends Address {

    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
