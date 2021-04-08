package lendpal.file;

import lendpal.model.LendPalItem;
import lendpal.model.LendPalModel;
import lendpal.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.FileSystems;
import java.nio.file.Path;

class LendPalModelJsonTest {

    LendPalModel model;
    LendPalModelFileHandler handler;

    @BeforeEach
    void beforeEach() {
        model = new LendPalModel();
        User user = new User("Test", "Test", "test@epost.com", "Tullepassord");
        LendPalItem item = new LendPalItem("Sirkelsag", "Grønn, slitt.");
        System.out.println(item);
        model.addNewUser(user);
        model.addItemToLendPalList(user, item);
        System.out.println(model);
        handler = new LendPalModelJson();

    }

    @Test
    void testSaveThrowsNoError() {
        handler.save(model, "model.json");
    }

}
