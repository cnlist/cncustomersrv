package us.cnlist.services.customerserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import us.cnlist.objects.people.User;


@Controller
public class UserRegistrationController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = "userserv.register")
    public void onSingleUserRegisterMessage(User user){

    }



}
