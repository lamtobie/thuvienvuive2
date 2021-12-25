package thuvienvuive.Member;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class addMemberController implements Initializable {
    @FXML
    ImageView memberIcon;
    @FXML
    ImageView exitButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setIcon();
    }
    public  void setIcon(){
        File iconFile = new File("src/image/member.png");
        Image iconTeam=new Image(iconFile.toURI().toString());
        memberIcon.setImage(iconTeam);
        File iconButton = new File("src/image/exit.png");
        Image ButtonIcon=new Image(iconButton.toURI().toString());
        exitButton.setImage(ButtonIcon);
    }
}
