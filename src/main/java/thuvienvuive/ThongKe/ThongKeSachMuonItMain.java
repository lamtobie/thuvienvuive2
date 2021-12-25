package thuvienvuive.ThongKe;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class ThongKeSachMuonItMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(ThongKeSachMuonItController.class.getResource("ThongKeSachMuonIt.fxml"));
			Scene scene = new Scene(fxmlLoader.load());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Thống kê sách mượn");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}