package us.cnlist.services.customerserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import us.cnlist.objects.messages.rq.AuthenticateUserRQ;
import us.cnlist.objects.messages.rs.AuthenticateUserRs;
import us.cnlist.services.customerserver.component.PasswordCryptor;
import us.cnlist.services.customerserver.database.entity.SystemUser;
import us.cnlist.services.customerserver.database.repository.UserRepository;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordCryptor passwordCryptor;

    @PostMapping
    @ResponseBody
    public AuthenticateUserRs authenticate(@RequestBody AuthenticateUserRQ rq) {

        SystemUser user = userRepository.findSystemUserByLogin(rq.getUsername());
        if (user == null) {
            return sendAuthenticationFail();
        } else {
            if (passwordCryptor.hashPassword(rq.getPassword()).equals(user.getPassword())) {
                return sendAuthenticationSuccess(user);
            } else {
                return sendAuthenticationFail();
            }
        }

    }

    private AuthenticateUserRs sendAuthenticationFail() {
        AuthenticateUserRs rs = new AuthenticateUserRs();
        rs.setUser(null);
        rs.setAuthenticated(false);
        return rs;
    }

    private AuthenticateUserRs sendAuthenticationSuccess(SystemUser user) {
        AuthenticateUserRs rs = new AuthenticateUserRs();
        rs.setAuthenticated(true);
        rs.setUser(user);
        return rs;
    }

}
