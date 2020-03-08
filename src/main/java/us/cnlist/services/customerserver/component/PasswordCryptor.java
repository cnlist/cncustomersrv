package us.cnlist.services.customerserver.component;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;

@Component
public class PasswordCryptor {

    public String hashPassword(String password) {
        try {
            return new BigInteger(MessageDigest.getInstance("SHA1").digest(
                    password.getBytes()
            )).toString(16);
        } catch (Exception e) {
            return null;
        }

    }

}
