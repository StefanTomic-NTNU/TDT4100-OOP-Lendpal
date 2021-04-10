package lendpal.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lendpal.model.LendPalModel;

public class App extends Application {

    /**
     * This main method is not meant to be run directly, as it will raise an error if javaFX is not configured
     * correctly for the IDE where execution is taking place. Run the main method in Main instead.
     * It is defined because it is needed for a workaround to allow running the app without having javaFX configured.
     * @param args optional arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        final Parent parent = loader.load();
        LoginController loginController = loader.getController();
        DirectLendPalDataAccess access =
                new DirectLendPalDataAccess("src/main/resources/lendpal/file/LendPalModel.json");
        access.setModel(new LendPalModel());
        access.readData();
        loginController.setLendPalDataAccess(access);
        primaryStage.setTitle("LendPal - Logg inn");
        primaryStage.setScene(new Scene(parent));
        primaryStage.show();

    }
}
