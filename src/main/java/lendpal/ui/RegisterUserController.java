package lendpal.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class RegisterUserController {

    @FXML
    TextField firstNameField;

    @FXML
    TextField lastNameField;

    @FXML
    TextField emailField;

    @FXML
    PasswordField passwordField;

    @FXML
    PasswordField confirmPasswordField;

    @FXML
    Button cancelButton;

    @FXML
    Button registerButton;

    private LendPalDataAccess access;

    public void setLendPalDataAccess(LendPalDataAccess access) {
        this.access = access;
    }

    private void returnToLogin() throws IOException {
        Stage oldStage = (Stage) cancelButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        LoginController loginController = loader.getController();
        loginController.setLendPalDataAccess(access);
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.setTitle("Login");
        newStage.show();
        oldStage.close();
    }

    @FXML
    void cancelButtonClicked() throws IOException {
        returnToLogin();
    }

    @FXML
    void registerButtonClicked() throws IOException {
        try {
            access.addNewUser(firstNameField.getText(),
                    lastNameField.getText(),
                    emailField.getText(),
                    passwordField.getText(),
                    confirmPasswordField.getText());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PopUp.fxml"));
            Parent popUpRoot = loader.load();
            PopUpController popUpController = loader.getController();
            popUpController.setMessage("Bruker " + firstNameField.getText() + " opprettet!");
            Stage popUp = new Stage();
            popUp.setScene(new Scene(popUpRoot));
            popUp.setTitle("Bruker opprettet");
            returnToLogin();
            popUp.show();
            access.writeData();
        } catch (IllegalArgumentException e) {
            passwordField.clear();
            confirmPasswordField.clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PopUp.fxml"));
            Parent popUpRoot = loader.load();
            PopUpController popUpController = loader.getController();
            popUpController.setMessage(e.getMessage());
            Stage popUp = new Stage();
            popUp.setScene(new Scene(popUpRoot));
            popUp.setTitle("Feilmelding");
            popUp.show();
        }
    }


}
