package us.cnlist.services.customerserver.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import us.cnlist.objects.people.UserProfile;
import us.cnlist.services.customerserver.database.entity.DbUserProfile;
import us.cnlist.services.customerserver.database.repository.ProfileRepository;

@Controller
@RequestMapping("/profile")
public class UserProfileController {

    @Autowired
    private ProfileRepository profileRepository;

    @GetMapping("/email/{userEmail}")
    @ResponseBody
    public UserProfile getUserProfileByEmail(@PathVariable("userEmail") String userEmail) {
        DbUserProfile profile = profileRepository.getProfileByEmail(userEmail);
        UserProfile userProfile = new UserProfile();
        userProfile.setCitizenData(profile.getCitizenProfile());
        return userProfile;
    }


}
