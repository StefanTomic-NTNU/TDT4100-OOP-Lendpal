package lendpal.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class PopUpController {

    @FXML
    TextArea popUpTextArea;

    @FXML
    Button okButton;

    public void setMessage(String message) {
        this.popUpTextArea.setText(message);
    }

    @FXML
    void okButtonClicked() {
        ((Stage) okButton.getScene().getWindow()).close();
    }
}
