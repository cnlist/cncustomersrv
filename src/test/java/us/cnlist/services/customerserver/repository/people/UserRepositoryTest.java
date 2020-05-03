package us.cnlist.services.customerserver.repository.people;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import us.cnlist.services.customerserver.CustomerServerApplication;
import us.cnlist.services.customerserver.database.repository.UserRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CustomerServerApplication.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testUserRepositoryLoaded(){
        Assert.assertNotNull(userRepository);
    }


}
