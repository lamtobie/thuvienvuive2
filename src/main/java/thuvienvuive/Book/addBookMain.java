package thuvienvuive.Book;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import thuvienvuive.Book.BookListController;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

public class addBookMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(AddBookController.class.getResource("AddNewBook.fxml"));
            
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


}