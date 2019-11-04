package us.cnlist.services.customerserver.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import javax.annotation.PostConstruct;

@Configuration
@EnableJms
public class JmsConfiguration {
    @Autowired
    private JmsTemplate jmsTemplate;

    @PostConstruct
    public void sendConnectMessage(){
        jmsTemplate.convertAndSend("tests","test");
    }
}
