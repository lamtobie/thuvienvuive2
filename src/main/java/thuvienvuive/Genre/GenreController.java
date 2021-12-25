package thuvienvuive.Genre;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class GenreController implements Initializable {
    @FXML
    ImageView imageIcon;
    @FXML
    ImageView closeIcon;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setIcon();
    }
    public  void setIcon(){
        File iconFile = new File("src/image/bookgenres.png");
        Image iconImage=new Image(iconFile.toURI().toString());
        imageIcon.setImage(iconImage);
        File closeFile = new File("src/image/close.png");
        Image iconClose =new Image(closeFile.toURI().toString());
        closeIcon.setImage(iconClose);
    }
}
