package thuvienvuive.User;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class ManagerUserMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(ManagerUserController.class.getResource("ManagerUser.fxml"));
			Scene scene = new Scene(fxmlLoader.load());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Quản lý người dùng");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}