package thuvienvuive;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import thuvienvuive.Login.*;
import java.io.IOException;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("LoginGUI.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            primaryStage.setTitle("Dashboard Admin");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
