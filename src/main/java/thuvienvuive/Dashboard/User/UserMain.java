package thuvienvuive.Dashboard.User;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UserMain extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(UserController.class.getResource("UserSample.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Dashboard User");
        scene.getStylesheets().add(this.getClass().getResource("UserStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
