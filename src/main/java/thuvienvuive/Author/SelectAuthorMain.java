package thuvienvuive.Author;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class SelectAuthorMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(AuthorController.class.getResource("SelectAuthorList.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            primaryStage.setTitle("Author List");
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
