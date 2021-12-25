package thuvienvuive.Author;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class SelectAuthorController implements Initializable {
    @FXML
    ImageView authorIcon;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setIcon();
    }
    public  void setIcon(){
        File iconFile = new File("src/image/author.png");
        Image iconAuthor=new Image(iconFile.toURI().toString());
        authorIcon.setImage(iconAuthor);

    }
}
