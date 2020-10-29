package us.cnlist.services.customerserver.database;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import us.cnlist.objects.requests.AuthenticationRQ;
import us.cnlist.objects.requests.UserRegistrationRQ;
import us.cnlist.objects.responses.AuthenticationRS;
import us.cnlist.objects.responses.ResponseType;
import us.cnlist.objects.responses.UserRegistrationRS;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
public class UserRegistrationTestIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testRestTemplateLoaded(){
        Assert.assertNotNull(testRestTemplate);
    }

    @Test
    public void testResponseType(){
        UserRegistrationRQ dummyRQ = new UserRegistrationRQ();
        UserRegistrationRS response = testRestTemplate.postForObject(
                "http://localhost:"+port+"/user/register",dummyRQ,
                UserRegistrationRS.class);

        Assert.assertEquals(response.getResponseType(), ResponseType.ERROR);
    }

    @Test
    public void registerUserTest(){
        UserRegistrationRQ rq = new UserRegistrationRQ();
        rq.setEmail("test@email.com");
        rq.setPassword("TestPassword134");
        UserRegistrationRS rs =
                testRestTemplate.postForObject("" +
                                "http://localhost:"+port+"/user/register",
                        rq,
                        UserRegistrationRS.class);
        Assert.assertNotNull(rs);
        Assert.assertEquals(rs.getResponseType(),ResponseType.SUCCESS);
        Assert.assertThat(rs.getUserId(), Matchers.greaterThan(0L));

    }

    @Test
    public void userAuthTest(){
        String email = "test@email.com";
        AuthenticationRQ rq = new AuthenticationRQ();
        rq.setEmail(email);
        rq.setPassword("TestPassword134");
        AuthenticationRS rs =
                testRestTemplate.postForObject("" +
                                "http://localhost:"+port+"/auth",
                        rq,
                        AuthenticationRS.class);
        assert (rs.getResponseType()==ResponseType.SUCCESS);
        assert (rs.getAuthenticatedUser() !=null );
        assert (rs.getAuthenticatedUser().getLogin().equals(email));

    }

}
