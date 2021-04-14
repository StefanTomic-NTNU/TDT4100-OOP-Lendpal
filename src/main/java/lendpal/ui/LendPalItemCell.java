package lendpal.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import lendpal.model.LendPalItem;

import java.io.IOException;

public class LendPalItemCell extends ListCell<LendPalItem> {

    @FXML
    private Text nameText;

    @FXML
    private Text availability;

    @FXML
    private Button lendButton;

    @FXML
    private HBox hBox;

    private FXMLLoader fxmlLoader;

    @Override
    protected void updateItem(LendPalItem item, boolean empty) {

        if (empty || item == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("LendPalItemCell.fxml"));
                fxmlLoader.setController(this);
            } try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (nameText != null) {
            nameText.setText(item.getName());
        }
        setText(null);
        setGraphic(hBox);
    }

}
