package us.cnlist.services.customerserver.database;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import us.cnlist.objects.people.User;
import us.cnlist.services.customerserver.component.PasswordCryptor;
import us.cnlist.services.customerserver.database.repository.UserRepository;

import java.util.Calendar;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
public class UserRegisterDbTestIT {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordCryptor passwordCryptor;


    @Test
    public void testUserRegister() {
        User user = new User();
        user.setPassword(passwordCryptor.hashPassword("TestPas$27w(op"));
        user.setEnabled(true);
        user.setLogin("testlogin");
        user.setRegistrationDate(Calendar.getInstance().getTime());
        userRepository.save(user);

    }

}
