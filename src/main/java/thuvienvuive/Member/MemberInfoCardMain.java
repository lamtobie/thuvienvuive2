package thuvienvuive.Member;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import thuvienvuive.Genre.GenreController;


public class MemberInfoCardMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(MemberInfoCardController.class.getResource("MemberInfoCardGUI.fxml"));
			Scene scene = new Scene(fxmlLoader.load());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Thẻ thông tin độc giả");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
