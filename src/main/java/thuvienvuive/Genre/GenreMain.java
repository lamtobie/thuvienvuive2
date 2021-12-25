package thuvienvuive.Genre;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import thuvienvuive.Login.LoginController;

public class GenreMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(GenreController.class.getResource("GenreGUI.fxml"));
			Scene scene = new Scene(fxmlLoader.load());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Quản lý thể loại sách");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}