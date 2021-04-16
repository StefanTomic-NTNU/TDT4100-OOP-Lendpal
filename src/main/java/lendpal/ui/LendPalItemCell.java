package lendpal.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import lendpal.model.LendPalItem;

import java.io.IOException;
import java.time.ZonedDateTime;

public class LendPalItemCell extends ListCell<LendPalItem> {

    @FXML
    private Text nameText;

    @FXML
    private Text availability;

    @FXML
    private Button lendButton;

    @FXML
    private HBox hBox;

    private LendPalAppController appController;

    private FXMLLoader fxmlLoader;

    /*
    public LendPalItemCell() {
        super();

        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });

    }

     */

    public LendPalItemCell(LendPalAppController appController) {
        this.appController = appController;
    }

    @Override
    protected void updateItem(LendPalItem item, boolean empty) {

        super.updateItem(item, empty);

        if (empty || item == null) {

            setText(null);
            setGraphic(null);
            nameText = null;
            availability = null;
            lendButton = null;

        } else {

            fxmlLoader = new FXMLLoader(getClass().getResource("LendPalItemCell.fxml"));
            fxmlLoader.setController(this);
            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            setText(null);
            setGraphic(hBox);
            if (nameText != null && availability != null && lendButton != null) {
                nameText.setText(item.getName());
                ZonedDateTime returnDate = item.getReturnDate();
                lendButton.setText("LAAN");
                lendButton.setOnAction(e -> this.appController.lend(getItem()));
                if (returnDate != null) {
                    availability.setText("OPPTATT TIL " + item.getReturnDate().toString());
                    lendButton.setDisable(true);
                } else {
                    availability.setText("LEDIG");
                }
            }
        }
    }

}
