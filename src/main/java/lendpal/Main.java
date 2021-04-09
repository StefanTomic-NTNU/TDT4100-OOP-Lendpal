package lendpal;

import lendpal.file.LendPalModelFileHandler;
import lendpal.file.LendPalModelJson;
import lendpal.model.LendPalItem;
import lendpal.model.LendPalModel;
import lendpal.model.User;
import lendpal.ui.App;

public class Main {
    public static void main(String[] args) {

        LendPalModel model = new LendPalModel();
        User user = new User("Test");
        LendPalItem item = new LendPalItem("Sirkelsag", "Grønn, slitt.");
        model.addUser(user);
        model.addItem(item);
        model.lendItem(user.getId(), item.getId());
        LendPalModelFileHandler handler = new LendPalModelJson();
        handler.save(model, "src/test/resources/lendpal/file/test_model.json");
        LendPalModel readModel = handler.load("src/test/resources/lendpal/file/test_model.json");

        // App.main(args);
    }
}
