package thuvienvuive.Book;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class bookInfoCardController implements Initializable {
    @FXML
    ImageView iconImage;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setIcon();
    }
    public  void setIcon(){
        File iconFile = new File("src/image/bookIcon.png");
        Image iconTeam=new Image(iconFile.toURI().toString());
        iconImage.setImage(iconTeam);

    }
}
