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
    final String fileName = "src/test/resources/lendpal/file/test_model.json";

    @BeforeEach
    void beforeEach() {
        model = new LendPalModel();
        User user = new User("Test");
        LendPalItem item = new LendPalItem("Sirkelsag", "Grønn, slitt.");
        System.out.println(item.getId());
        System.out.println(item);
        model.addNewUser(user);
        System.out.println(user);
        model.addItemToLendPalList(user, item);
        System.out.println(model.getLendPalList(user));
        handler = new LendPalModelJson();

    }

    @Test
    void testSaveAndLoad() {
        handler.save(model, fileName);
        LendPalModel model = handler.load(fileName);
    }

}
