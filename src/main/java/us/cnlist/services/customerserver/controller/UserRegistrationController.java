package us.cnlist.services.customerserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import us.cnlist.objects.requests.UserRegistrationRQ;
import us.cnlist.objects.responses.UserRegistrationRS;

@Controller
@RequestMapping("/register")
public class UserRegistrationController {

    @PostMapping
    @ResponseBody
    public UserRegistrationRS receiveUserRegistrationRequest(@RequestBody UserRegistrationRQ request) {
        UserRegistrationRS response = new UserRegistrationRS();
        response.setUserId(-1L);
        return response;
    }

}