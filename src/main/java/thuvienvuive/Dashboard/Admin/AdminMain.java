package thuvienvuive.Dashboard.Admin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminMain extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(AdminController.class.getResource("AdminSample.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Dashboard Admin");
        scene.getStylesheets().add(this.getClass().getResource("AdminStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
