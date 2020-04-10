package us.cnlist.services.customerserver.component;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class PasswordCryptorTest {
    final String salt = "salt";

    @Test
    public void testHashPasswordWithNormal() {

        String passwordToHash = "test_password";
        PasswordCryptor cryptor = new PasswordCryptor(salt);
        String rc = cryptor.hashPassword(passwordToHash);

        PasswordCryptor control = new PasswordCryptor(salt);
        Assertions.assertEquals(rc, control.hashPassword(passwordToHash));
    }

    @Test
    public void testHashNull() {
        String exceptionMessage = "Password should not be empty or null";
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new PasswordCryptor(salt).hashPassword(null);
        });
        Assert.assertEquals(exceptionMessage, exception.getMessage());
    }

    @Test
    public void testHashEmpty() {
        String exceptionMessage = "Password should not be empty or null";
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new PasswordCryptor(salt).hashPassword("");
        });
        Assert.assertEquals(exceptionMessage, exception.getMessage());
    }

}
