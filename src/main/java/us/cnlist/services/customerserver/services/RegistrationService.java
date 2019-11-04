package us.cnlist.services.customerserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import us.cnlist.services.customerserver.Core;
import us.cnlist.services.customerserver.database.entity.SystemUser;
import us.cnlist.services.customerserver.database.repository.UserRepository;
import us.cnlist.services.customerserver.events.UserRegisteredEvent;

import javax.transaction.Transactional;

@Service
public class RegistrationService extends Core {

    @Autowired
    private UserRepository userRepository;

    @Async
    @Transactional
    public void registerNewUser(SystemUser systemUser) {
        UserRegisteredEvent result = new UserRegisteredEvent(systemUser, true);
        if (systemUser == null) {
            result.setFailReason("User is null");
        } else if (systemUser.getLogin() == null || systemUser.getLogin().isEmpty()) {
            result.setFailReason("Login is empty");
        } else if (systemUser.getPassword() == null || systemUser.getPassword().isEmpty()) {
            result.setFailReason("password is empty");
        } else {

            SystemUser user = userRepository.findSystemUserByLogin(systemUser.getLogin());
            if (user == null) {

                result = new UserRegisteredEvent(userRepository.save(systemUser), false);

            } else {
                result.setFailReason("User exists");
            }


        }

        event(result);
    }


}
