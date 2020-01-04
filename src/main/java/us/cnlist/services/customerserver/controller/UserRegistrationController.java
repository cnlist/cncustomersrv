package us.cnlist.services.customerserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import us.cnlist.objects.messages.rq.NamedUserRegisterRq;
import us.cnlist.services.customerserver.component.PasswordCryptor;
import us.cnlist.services.customerserver.database.entity.CitizenProfile;
import us.cnlist.services.customerserver.database.entity.DbUserProfile;
import us.cnlist.services.customerserver.database.entity.SystemUser;
import us.cnlist.services.customerserver.database.repository.CitizenRepository;
import us.cnlist.services.customerserver.database.repository.ProfileRepository;
import us.cnlist.services.customerserver.database.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Date;


@Controller
@Slf4j
public class UserRegistrationController {

    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private CitizenRepository citizenRepository;
    @Autowired
    private PasswordCryptor passwordCryptor;

    @Transactional
    @JmsListener(destination = "userserv.register")
    public void onSingleUserRegisterMessage(NamedUserRegisterRq rq) {

        SystemUser user = new SystemUser();
        user.setLogin(rq.getUser().getLogin());
        user.setPassword(passwordCryptor.hashPassword(user.getPassword()));
        user.setRegistrationDate(new Date());
        user.setEnabled(true);
        SystemUser registeredUser = userRepository.save(user);
        DbUserProfile userProfile = new DbUserProfile();
        CitizenProfile citizenProfile = new CitizenProfile();
        citizenProfile.setFirstName(rq.getName());
        citizenProfile = citizenRepository.save(citizenProfile);
        userProfile.setCitizenProfile(citizenProfile);
        userProfile = profileRepository.save(userProfile);
        registeredUser.setUserProfile(userProfile);
        userRepository.save(registeredUser);
//TODO: send back user is registered
    }


}
