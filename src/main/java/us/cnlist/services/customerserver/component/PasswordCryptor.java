package us.cnlist.services.customerserver.component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PasswordCryptor {

    private final Logger logger = Logger.getLogger("Password cryptor");
    private final String saltString;

    public PasswordCryptor(String saltString) {
        this.saltString = saltString;
    }

    public String hashPassword(String password) {

        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password should not be empty or null");
        }

        byte[] salt = saltString.getBytes();
        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return new BigInteger(factory.generateSecret(spec).getEncoded()).toString(16);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            logger.log(Level.SEVERE, e.getMessage());
            throw new RuntimeException(e);

        }

    }

}
