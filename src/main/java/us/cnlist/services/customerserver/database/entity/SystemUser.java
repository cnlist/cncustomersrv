package us.cnlist.services.customerserver.database.entity;

import us.cnlist.objects.people.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "System_User")
public class SystemUser extends User {

    private Long id;
    private Date registrationDate;
    private boolean enabled;
    private DbUserProfile userProfile;

    public SystemUser() {

    }

    @Column(name = "user_login")
    @Override
    public String getLogin() {
        return super.getLogin();
    }

    @Column(name = "user_password")
    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "registration_date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Column(name = "account_enabled")
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @JoinColumn(name = "profile")
    @OneToOne(fetch = FetchType.LAZY)
    public DbUserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(DbUserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
