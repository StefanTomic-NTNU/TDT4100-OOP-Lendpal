package lendpal.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    /**
     * This main method is not meant to be run directly, as it will raise an error if javaFX is not configured
     * correctly for the IDE where execution is taking place.
     * It is defined because it is needed for a workaround to allow running the app without having javaFX configured.
     * @param args arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final Parent parent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("LendPal - Logg inn");
        primaryStage.setScene(new Scene(parent));
        primaryStage.show();

    }
}
