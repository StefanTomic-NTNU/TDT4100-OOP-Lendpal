package lendpal.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

    User user;

    @BeforeEach
    void beforeEach() {
        user = new User("Fornavn", "Etternavn", "test@email.com", "passord");
    }

}
