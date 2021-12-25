package thuvienvuive.Member;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class deleteMemberController implements Initializable {
    Stage stage;

    deleteMemberDAO ListDAO = new deleteMemberDAO();

    static ObservableList<MemberDTO> List;

    public ObservableList<MemberDTO> readList() throws Exception{
        List = ListDAO.docDB();
        return List;
    }

    @FXML
    ImageView titleImage;

    @FXML
    AnchorPane wrap;

    @FXML
    Button closeButton;

    @FXML
    Button deleteButton;

    @FXML
    TextField deleteField;

    @FXML
    Spinner<String> deleteSpinner;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            setDefault();
        }catch (Exception e){
            e.printStackTrace();
        }
        handler();
    }

    private ObservableList<String> getMemberIDList(ObservableList<MemberDTO> List) {
        ObservableList<String> IDMember = FXCollections.observableArrayList();
        for (MemberDTO memberDTO : List) {
            IDMember.add(memberDTO.getID());
        }
        return IDMember;
    }

    private void readIDList() throws Exception{
        SpinnerValueFactory<String> values = new SpinnerValueFactory.ListSpinnerValueFactory<String>(getMemberIDList(readList()));
        deleteSpinner.setValueFactory(values);
        deleteSpinner.getValueFactory().wrapAroundProperty().set(true);
        deleteField.setText(selectMember());
    }

    //hiển thị thông báo lỗi
    private void errorAlert(String title, String Message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.getDialogPane().setStyle("-fx-font-size: 16px;  -fx-cursor: hand;");
        alert.setContentText(Message);
        alert.setHeaderText(null);
        ButtonType okBtn = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(okBtn);
        alert.show();
    }

    //hiển thị thông báo
    private void alert(String title, String Message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.getDialogPane().setStyle("-fx-font-size: 16px; -fx-cursor: hand;");
        alert.setContentText(Message);
        alert.setHeaderText(null);
        ButtonType okBtn = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(okBtn);
        alert.show();
    }

    private void close(){
        stage = (Stage) wrap.getScene().getWindow();
        stage.close();
    }

    public void handler(){
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                close();
            }
        });

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String ID = deleteField.getText();
                try{
                    deleteMember(getMemberIDList(readList()), ID);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        deleteSpinner.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                deleteField.setText(selectMember());
            }
        });
    }

    public String selectMember(){
        return deleteSpinner.getValue();
    }

    public void deleteMember(ObservableList<String> IDList, String ID) throws Exception{
        int count = 0;
        for(String id : IDList){
            if(id.equalsIgnoreCase(ID)){
                count++;
                if(ListDAO.deletePhieuMuon(ID) > 0) {
                    if (ListDAO.deleteMember(ID) > 0) {
                        alert("Thông báo", "Xóa Member thành công!");
                        readList();
                        readIDList();
                    } else {
                        errorAlert("Lỗi", "Có lỗi xảy ra trong quá trình xóa member!");
                    }
                }
                else{
                    errorAlert("Lỗi", "Không tồn tại member nào để xóa!");
                }
            }
        }
        if(count == 0){
            alert("Thông báo", "Không tồn tại member có ID" + ID);
        }
    }

    public void setDefault() throws Exception {
        setIcon();
        readIDList();
    }

    public  void setIcon(){
        File iconFile = new File("src/image/team.png");
        Image iconTeam=new Image(iconFile.toURI().toString());
        titleImage.setImage(iconTeam);

    }
}
