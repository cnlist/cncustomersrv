package us.cnlist.services.customerserver.component;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class PasswordCryptor {

    private final Logger logger = Logger.getLogger("Password cryptor");

    public String hashPassword(String password) {
        if (password==null || password.isEmpty()){
            throw new IllegalArgumentException("Password should not be empty or null");
        }
        try {
            return new BigInteger(MessageDigest.getInstance("SHA1").digest(
                    password.getBytes()
            )).toString(16);
        } catch (NoSuchAlgorithmException e) {
            logger.log(Level.SEVERE,e.getMessage());
            throw new RuntimeException(e);
        }

    }

}
