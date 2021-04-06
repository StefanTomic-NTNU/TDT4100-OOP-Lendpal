package lendpal.model;

import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

    User user;

    /* @BeforeEach
    void beforeEach() {
        user = new User("Fornavn", "Etternavn", "test@email.com", "tullepassord");
    }*/

    @Test
    void testVerifyEmail() {
        Assertions.assertAll(
                () -> Assertions.assertThrows(IllegalArgumentException.class, () ->
                    new User("Fornavn", "Etternavn", "email@email", "tullpassord")),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () ->
                    new User("Fornavn", "Etternavn", "email.com", "tullpassord")),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () ->
                    new User("Fornavn", "Etternavn", "email@.com", "tullpassord"))
        );
    }

    @Test
    void testHashPassword() {
        String password = "tullepassord";
        user = new User("Fornavn", "Etternavn", "valid@email.com", password);
        Assertions.assertNotEquals(password, user.getPassword());

        String hashedPassword = User.hashPassword(password, user.getSalt());
        Assertions.assertEquals(user.getPassword(), hashedPassword);

    }

}
