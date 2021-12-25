package thuvienvuive.Book;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import thuvienvuive.Book.BookListController;

import java.io.IOException;
import java.util.Objects;

public class returnBookMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(returnBookController.class.getResource("returnBook.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("returnBook.css")).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
