package lendpal.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    TextField emailField;

    @FXML
    PasswordField passwordField;

    @FXML
    Button loginButton;

    @FXML
    Button registerButton;

    private String email;

    private String password;

    private String userId;

    private LendPalDataAccess access;

    public LoginController() {

    }

    public void setLendPalDataAccess(LendPalDataAccess access) {
        this.access = access;
    }

    @FXML
    void loginButtonClicked() throws IOException {
        // System.out.println("Hei");
        System.out.println(emailField.getText());
        System.out.println(passwordField.getText());
        if (access.checkUserCredentials(emailField.getText(), passwordField.getText())) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LendPalApp.fxml"));
            Parent root = (Parent) loader.load();
            LendPalAppController appController = loader.getController();
            appController.setLendPalDataAccess(access);
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 900, 500));
            stage.setTitle("LendPal");
            stage.show();
        }
        else {
            emailField.clear();
            passwordField.clear();
        }
    }



}
