package lendpal.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class LendPalListTest {

    User user;
    LendPalList list;
    LendPalItem item1;
    LendPalItem item2;

    @BeforeEach
    void beforeEach() {
        user = new User("Fornavn", "Etternavn", "epost@epost.com", "passord");
        item1 = new LendPalItem("Sirkelsag", "Grønn sirkelsag.");
        item2 = new LendPalItem("Stikksag", "Blå  og fin.");
        list = new LendPalList(user);
    }

    @Test
    void testConstructWithMultipleItems() {
        list = new LendPalList(user, item1, item2);
        Assertions.assertAll(
                () -> Assertions.assertTrue(list.containsItem(item1)),
                () -> Assertions.assertTrue(list.containsItem(item2))
        );
    }

    @Test
    void testPutAndRemoveItems() {
        Assertions.assertFalse(list.containsItem(item1));
        list.putItem(item1);
        Assertions.assertTrue(list.containsItem(item1));
        list.removeItem(item1);
        Assertions.assertFalse(list.containsItem(item1));
    }



}