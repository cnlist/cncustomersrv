package us.cnlist.services.customerserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import us.cnlist.objects.people.User;
import us.cnlist.objects.requests.UserRegistrationRQ;
import us.cnlist.objects.responses.*;
import us.cnlist.services.customerserver.component.PasswordCryptor;
import us.cnlist.services.customerserver.database.repository.UserRepository;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.Date;
import java.util.List;

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
                user.setLogin(request.getEmail().toLowerCase());
                user.setPassword(passwordCryptor.hashPassword(request.getPassword()));
                response.setUserId(userRepository.save(user).getId());
                response.setResponseType(ResponseType.SUCCESS);
            }

        }
        return response;
    }


    @GetMapping("/get/login")
    @ResponseBody
    public UserRS getUserByLogin(@RequestParam("login") String login){

        UserRS userRS = new UserRS();
        userRS.setResponseType(ResponseType.ERROR);
        if (login!=null){
            User user = userRepository.getUserByLogin(login);
            if (user!=null){
                userRS.setResponseType(ResponseType.SUCCESS);
                userRS.setUser(user);
            }else{
                userRS.setErrorType(ErrorType.NOT_FOUND);
            }
        }
        return userRS;
    }

    @GetMapping("/search/login")
    @ResponseBody
    public UserListRs searchUserByLogin(@RequestParam("login") String login){
        UserListRs rs = new UserListRs();
        rs.setResponseType(ResponseType.ERROR);
        List<User> users = userRepository.findUsersByLoginContaining(login.toLowerCase());
        if (users!=null && !users.isEmpty()){
            rs.setResponseType(ResponseType.SUCCESS);
            rs.setUsers(users);
        }else{
            rs.setErrorType(ErrorType.NOT_FOUND);
        }

        return rs;
    }

    @GetMapping("/get/id")
    @ResponseBody
    public UserRS getUserById(@RequestParam("id")Long id){
        UserRS rs = new UserRS();
        rs.setResponseType(ResponseType.ERROR);
        rs.setErrorType(ErrorType.NOT_FOUND);

            User user = userRepository.getUserById(id);
            if (user!=null){
                rs.setResponseType(ResponseType.SUCCESS);
                rs.setErrorType(null);
                rs.setUser(user);
            }

        return rs;
    }

}
