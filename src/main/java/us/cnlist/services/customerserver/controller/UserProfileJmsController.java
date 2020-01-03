package us.cnlist.services.customerserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Controller;
import us.cnlist.objects.people.UserProfile;
import us.cnlist.services.customerserver.database.entity.CitizenProfile;
import us.cnlist.services.customerserver.database.entity.DbUserProfile;
import us.cnlist.services.customerserver.database.repository.CitizenRepository;
import us.cnlist.services.customerserver.database.repository.ProfileRepository;

import javax.transaction.Transactional;

@Controller
public class UserProfileJmsController {

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private CitizenRepository citizenRepository;

    @JmsListener(destination = "userserv.profile.edit")
    @Transactional
    public void editProfile(UserProfile profile) {
        DbUserProfile prf = profileRepository
                .findById(profile.getId()).get(); //todo: orelse throw exception
        CitizenProfile citizenProfile = prf.getCitizenProfile();
        citizenProfile.setFirstName(profile.getCitizenData().getFirstName());
        citizenProfile.setSecondName(profile.getCitizenData().getSecondName());
        citizenProfile.setMiddleName(profile.getCitizenData().getMiddleName());
        citizenProfile.setMale(profile.getCitizenData().isMale());
        citizenRepository.save(citizenProfile);
    }

}
