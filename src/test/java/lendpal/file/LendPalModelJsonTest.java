package lendpal.file;

import lendpal.model.LendPalItem;
import lendpal.model.LendPalModel;
import lendpal.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LendPalModelJsonTest {

    final String fileName = "src/test/resources/lendpal/file/TestModel.json";

    LendPalModelFileHandler handler;
    LendPalModel model;
    LendPalItem item;
    User user;

    @BeforeEach
    void beforeEach() {
        model = new LendPalModel();
        user = new User("Test");
        item = new LendPalItem("Sirkelsag", "Grønn, slitt.");
        model.addUser(user);
        model.addAvailableItem(item);
        model.lendAvailableItem(user.getId(), item);
        handler = new LendPalModelJson();

    }

    @Test
    void testSaveAndLoad() {
        handler.save(model, fileName);
        LendPalModel readModel = handler.load(fileName);
        Assertions.assertAll(
                () -> Assertions.assertTrue(readModel.containsUser(user.getId())),
                () -> Assertions.assertTrue(readModel.isItemAvailable(item.getId()))
        );
        System.out.println(readModel.getUser(user.getId()));
        //System.out.println(readModel.get());
    }

}
