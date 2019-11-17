package us.cnlist.services.customerserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import us.cnlist.objects.people.User;
import us.cnlist.services.customerserver.Core;
import us.cnlist.services.customerserver.database.entity.SystemUser;
import us.cnlist.services.customerserver.database.repository.UserRepository;
import us.cnlist.services.customerserver.events.UserRegisteredEvent;

import javax.transaction.Transactional;

@Service
public class RegistrationService extends Core {

    @Autowired
    private UserRepository userRepository;



}
