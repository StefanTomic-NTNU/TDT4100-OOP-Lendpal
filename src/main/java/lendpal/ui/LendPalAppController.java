package lendpal.ui;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import lendpal.model.LendPalItem;


public class LendPalAppController {

    @FXML
    ListView<LendPalItem> itemListView;

    private String userId;

    private LendPalDataAccess access;

    public void setLendPalDataAccess(LendPalDataAccess access) {
        this.access = access;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
