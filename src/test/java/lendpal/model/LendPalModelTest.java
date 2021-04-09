package lendpal.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LendPalModelTest {

    LendPalModel model;
    User user;
    LendPalItem item;

    @BeforeEach
    void beforeEach() {
        model = new LendPalModel();
        user = new User("Fornavn");
        item = new LendPalItem("Sirkelsag", "Grønn");
    }

    @Test
    void testAddNewUserAndContainsUser() {
        Assertions.assertFalse(model.containsUser(user));
        model.addNewUser(user);
        Assertions.assertTrue(model.containsUser(user));
    }

    @Test
    void testAddItemToLendPalList() {
        model.addNewUser(user);
        model.addItemToLendPalList(user, item);
        Assertions.assertEquals(model.getLendPalList(user), model.getLendPalList(item));
    }
}
