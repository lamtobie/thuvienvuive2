package thuvienvuive.Book;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static thuvienvuive.Book.bookIssueController.maxID;
import static thuvienvuive.Book.returnBookController.*;

public class compensationSlipController implements Initializable {

    Stage primaryStage;
    static int IDMax = 0;
    static ObservableList<compensationSlipDTO> list;
    compensationSlipDAO DAO = new compensationSlipDAO();
    {
        try{
            list = DAO.readListDAO();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private Label IDCompensationLabel;

    @FXML
    private Label IDIssueLabel;

    @FXML
    private Label IDMemberLabel;

    @FXML
    private Button cancelButton;

    @FXML
    private Button closeButton;

    @FXML
    private Label date;

    @FXML
    private TextArea detailArea;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button submitButton;

    @FXML
    private Label titleLabel;

    @FXML
    private AnchorPane titlePane;

    @FXML
    private AnchorPane wrap;

    @FXML
    private ImageView titleIcon;

    @FXML
    private ImageView closeIcon;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDefault();
        handler();
    }

    public void handler(){
        closeButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                close();
            }
        });

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                close();
            }
        });

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                compensationSlipDTO compensationSlip = getCompensation();
                if(compensationSlip.getNote().equals("")){
                    alert("Thông báo", "Ghi chú không được để trống, hãy điền nội dung tiền bồi thường.");
                }
                else{
                    try {
                        String status = "Đã trả";
                        String currentStatus = selectedIssue.getTrangThai();
                        if(normalizeString(status).contains(normalizeString(currentStatus)) ||
                                normalizeString(currentStatus).contains(normalizeString("Đang mượn"))){
                            if(DAO.insertCompensation(compensationSlip) > 0){
                                returnBookController rtCtrl = new returnBookController();
                                returnBookDAO rtDAO = new returnBookDAO();
                                if (rtDAO.updateStatus(selectedIssue, "Đã mất") > 0
                                        && rtDAO.updateBook(selectedIssue, rtCtrl.selectBookAmount(selectedIssue, BookList)) > 0){
                                    alert("Thông báo", "Cập nhật thành công.");
                                    close();
                                }
                                else
                                    errorAlert("Thông báo", "Có lỗi xảy ra trong quá trình thực hiện.");
                            }
                            else{
                                errorAlert("Thông báo", "Có lỗi xảy ra trong quá trình thực hiện.");
                            }
                        }
                        else{
                            if(DAO.insertCompensation(compensationSlip) > 0){
                                returnBookController rtCtrl = new returnBookController();
                                returnBookDAO rtDAO = new returnBookDAO();
                                if (rtDAO.updateStatus(selectedIssue, "Đã mất") > 0
                                        && rtDAO.updateBook(selectedIssue, rtCtrl.selectBookAmount(selectedIssue, BookList)-1) > 0){
                                    alert("Thông báo", "Cập nhật thành công.");
                                    close();
                                }
                                else
                                    errorAlert("Thông báo", "Có lỗi xảy ra trong quá trình thực hiện.");
                            }
                            else{
                                errorAlert("Thông báo", "Có lỗi xảy ra trong quá trình thực hiện.");
                            }
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private String normalizeString(String string){
        return string.trim().toLowerCase();
    }

    public ObservableList<compensationSlipDTO> readListDAO() throws Exception{
        list = DAO.readListDAO();
        return list;
    }

    private void setMaxID(){
        for (compensationSlipDTO compensation : list){
            String number = compensation.getIDCompensation().substring(4);
            int tmp = Integer.parseInt(number);
            if (tmp > IDMax){
                IDMax = tmp;
            }
        }
    }

    public compensationSlipDTO getCompensation(){
        return new compensationSlipDTO(
                IDCompensationLabel.getText(),
                IDMemberLabel.getText(),
                LocalDate.now(),
                detailArea.getText(),
                IDIssueLabel.getText()
        );
    }

    public void setDetails(){
        setMaxID();
        bookIssueDTO issue = selectedIssue;
        IDCompensationLabel.setText("IDBT"+(IDMax+1));
        IDIssueLabel.setText(issue.getIDPhieuMuon());
        IDMemberLabel.setText(issue.getIDThanhVien());
        date.setText(String.valueOf(LocalDate.now()));
    }

    public void setDefault(){
        setDetails();
        setIcon();
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
        primaryStage = (Stage) wrap.getScene().getWindow();
        primaryStage.close();
    }

    public  void setIcon(){
        File iconFile = new File("src/image/register_icon.png");
        Image iconTitle=new Image(iconFile.toURI().toString());
        titleIcon.setImage(iconTitle);
        File iconButton = new File("src/image/exit_icon.png");
        Image ButtonIcon=new Image(iconButton.toURI().toString());
        closeIcon.setImage(ButtonIcon);
    }
}
