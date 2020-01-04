package us.cnlist.services.customerserver.database.entity;

import us.cnlist.objects.people.UserProfile;

import javax.persistence.*;

@Table(name = "user_profile")
@Entity
public class DbUserProfile extends UserProfile {
    private Long id;
    private CitizenProfile citizenProfile;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JoinColumn(name = "citizen")
    @OneToOne(fetch = FetchType.LAZY)
    public CitizenProfile getCitizenProfile() {
        return this.citizenProfile;
    }

    public void setCitizenProfile(CitizenProfile citizenProfile) {
        this.citizenProfile = citizenProfile;
    }
}
