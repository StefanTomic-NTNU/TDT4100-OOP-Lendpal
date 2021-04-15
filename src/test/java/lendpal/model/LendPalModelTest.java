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
        Assertions.assertFalse(model.containsUser(user.getId()));
        model.addUser(user);
        Assertions.assertTrue(model.containsUser(user.getId()));
    }

    @Test
    void testAddItemToLendPalList() {
        model.addUser(user);
        model.addAvailableItem(item);
        model.lendAvailableItem(user.getId(), item.getId());
        Assertions.assertEquals(model.getUser(user.getId()), model.getItemHolder(item.getId()));
    }
}
