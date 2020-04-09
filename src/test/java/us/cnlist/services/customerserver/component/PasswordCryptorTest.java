package us.cnlist.services.customerserver.component;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;


public class PasswordCryptorTest {
    @Test
    public void testHashPasswordWithNormal() {
        String passwordToHash = "test_password";
        String value = "-604801ede8512bbd4fb3f0a1bc4a2a582cd78f69";
        PasswordCryptor cryptor = new PasswordCryptor();
        String rc = cryptor.hashPassword(passwordToHash);
        assert (value.equals(rc));
    }

    @Test
    public void testHashNull() {
        String exceptionMessage = "Password should not be empty or null";
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new PasswordCryptor().hashPassword(null);
        });
        Assert.assertEquals(exceptionMessage, exception.getMessage());
    }

    @Test
    public void testHashEmpty() {
        String exceptionMessage = "Password should not be empty or null";
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new PasswordCryptor().hashPassword("");
        });
        Assert.assertEquals(exceptionMessage, exception.getMessage());
    }

}
