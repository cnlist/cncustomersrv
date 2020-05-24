package us.cnlist.services.customerserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import us.cnlist.objects.people.User;
import us.cnlist.objects.requests.AuthenticationRQ;
import us.cnlist.objects.responses.AuthenticationRS;
import us.cnlist.objects.responses.ErrorType;
import us.cnlist.objects.responses.ResponseType;
import us.cnlist.services.customerserver.component.PasswordCryptor;
import us.cnlist.services.customerserver.database.repository.UserRepository;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordCryptor cryptor;

    @PostMapping
    @ResponseBody
    public AuthenticationRS authenticate(@RequestBody AuthenticationRQ rq){
        AuthenticationRS rs = new AuthenticationRS();
        User user = userRepository.findUserByLoginAndAndEnabledIsTrue(rq.getEmail());
        rs.setResponseType(ResponseType.ERROR);
        rs.setErrorType(ErrorType.UNAUTHORIZED);
        if (user!=null){
            if (cryptor.hashPassword(rq.getPassword()).equals(user.getPassword())){
                rs.setResponseType(ResponseType.SUCCESS);
                user.setPassword(null);
                rs.setAuthenticatedUser(user);
            }
        }
        return rs;

    }


}
