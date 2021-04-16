package lendpal.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import lendpal.model.LendPalItem;
import lendpal.model.LendPalItemContainer;

import java.util.List;
import java.util.stream.Collectors;


public class LendPalAppController {

    @FXML
    ListView<LendPalItem> itemListView;

    @FXML
    TextField searchField;

    @FXML
    Button searchButton;

    @FXML
    Text lendNumberText;

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
        LendPalItemContainer items = access.getAllItems();
        if (searchField != null && !searchField.getText().isBlank()) {
            List<LendPalItem> searchResults = items.getItems()
                    .stream()
                    .filter(p -> p.containsKeyword(searchField.getText()))
                    .collect(Collectors.toList());
            itemListView.getItems().setAll(searchResults);
        } else {
            itemListView.getItems().setAll(items.getItems());
        }
    }

    public void lend(LendPalItem item) {
        this.access.lendItem(loggedInUserId, item);
        this.access.writeData();
        updateListView();
    }

    @FXML
    public void performSearch() {
        updateListView();
    }


}
