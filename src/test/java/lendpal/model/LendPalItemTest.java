package lendpal.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LendPalItemTest {

    private LendPalItem item;
    private final String testName = "Sirkelsag";
    private final String testDescription = "Grønn sirkelsag, slitt.";

    @BeforeEach
    void beforeEach() {
        item = new LendPalItem(testName, testDescription);
    }

    @Test
    void testGetName() {
        Assertions.assertEquals(item.getName(), testName);
    }

    @Test
    void testGetDescription() {
        Assertions.assertEquals(item.getDescription(), testDescription);
    }

    @Test
    void testSetName() {
        String newName = "Stikksag";
        item.setName(newName);
        Assertions.assertEquals(item.getName(), newName);
    }

    @Test
    void testSetDescription() {
        String newDescription = "Blå, stikksag, som ny.";
        item.setDescription(newDescription);
        Assertions.assertEquals(item.getDescription(), newDescription);
    }
}
