package lendpal.ui;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import lendpal.model.LendPalItem;


public class LendPalAppController {

    @FXML
    ListView<LendPalItem> itemListView;

    private String loggedInUserId;

    private LendPalDataAccess access;

    public void setLendPalDataAccess(LendPalDataAccess access) {
        this.access = access;
    }

    public void setLoggedInUserId(String loggedInUserId) {
        this.loggedInUserId = loggedInUserId;
    }

    public void init() {
        initializeListView();
        updateListView();
    }

    public void initializeListView() {
        itemListView.setCellFactory(list -> new LendPalItemCell(this));
        itemListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void updateListView() {
        itemListView.getItems().setAll(access.getAllItems().getItems());
    }

    public void lend(LendPalItem item) {
        this.access.lendItem(loggedInUserId, item);
        this.access.writeData();
        updateListView();
    }


}
