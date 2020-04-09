package us.cnlist.services.customerserver.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import us.cnlist.services.customerserver.component.PasswordCryptor;

@Configuration
public class SecurityConfiguration {

    @Value("${us.cnlist.passwords.salt}")
    private String saltString;

    @Bean
    public PasswordCryptor passwordCryptor(){
        return new PasswordCryptor(saltString);
    }

}
