package us.cnlist.services.customerserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import us.cnlist.objects.people.User;
import us.cnlist.objects.requests.UserRegistrationRQ;
import us.cnlist.objects.responses.ErrorType;
import us.cnlist.objects.responses.ResponseType;
import us.cnlist.objects.responses.UserRegistrationRS;
import us.cnlist.services.customerserver.component.PasswordCryptor;
import us.cnlist.services.customerserver.database.repository.UserRepository;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordCryptor passwordCryptor;

    @ResponseBody
    @PostMapping("/register")
    @Transactional(value = Transactional.TxType.REQUIRED)
    public UserRegistrationRS registerUser(@RequestBody UserRegistrationRQ request) {
        UserRegistrationRS response = new UserRegistrationRS();
        response.setResponseType(ResponseType.ERROR);
        if (request.getEmail() != null && request.getPassword() != null) {

            if (userRepository.existsUserByLogin(request.getEmail())) {
                response.setErrorType(ErrorType.USER_EXISTS);
                response.setErrorMessage("User is exists");

            } else {

                User user = new User();
                user.setRegistrationDate(new Date());
                user.setEnabled(true);
                user.setLogin(request.getEmail());
                user.setPassword(passwordCryptor.hashPassword(request.getPassword()));
                response.setUserId(userRepository.save(user).getId());
                response.setResponseType(ResponseType.SUCCESS);
            }

        }
        return response;
    }
}
