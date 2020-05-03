package us.cnlist.services.customerserver.controller.user;

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
import us.cnlist.objects.responses.ResponseType;
import us.cnlist.objects.responses.UserListRs;
import us.cnlist.objects.responses.UserRS;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
public class UserGatheringTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testGetUserByLogin(){
        UserRS rs =
                testRestTemplate.getForObject(
                        "http://localhost:"+port+"/user/get/login?login=test@email.com",
                        UserRS.class
                );
        Assert.assertNotNull(rs);
        Assert.assertThat(rs.getResponseType(), Matchers.equalTo(ResponseType.SUCCESS));
    }

    @Test
    public void searchUserByLoginTest(){
        UserListRs rs =
                testRestTemplate.getForObject(
                        "http://localhost:"+port+"/user/search/login?login=te",
                        UserListRs.class
                );

        assert (rs.getResponseType()==ResponseType.SUCCESS);
    }

}
