package thuvienvuive.Book;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class AddBookController implements Initializable {
    @FXML
    ImageView bookIcon;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setIcon();
    }
    public  void setIcon(){
        File iconFile = new File("src/image/copy_139314729.png");
        Image iconBook=new Image(iconFile.toURI().toString());
        bookIcon.setImage(iconBook);

    }
}
