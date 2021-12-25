package thuvienvuive.ThongKe;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import  thuvienvuive.Excel.*;
import thuvienvuiveReport.PDF;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ThongKeSachHongController implements Initializable {
    @FXML
    private TableView table;
    @FXML
    private TableColumn IDMember;
    @FXML
    private TableColumn TenSach;
    @FXML
    private TableColumn IDTacGia;
    @FXML
    private TableColumn IDTheLoai;
    @FXML
    private TableColumn IDPhieuMuon;
    @FXML
    private TableColumn NgayHong;
    @FXML
    private DatePicker FromDate;
    @FXML
    private DatePicker ToDate;
    @FXML
    private Button ThongKe;
    @FXML
    private Text FromTo;
    @FXML
    private Button XuatExcel;
    @FXML
    private Button XuatPDF;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setAction();
    }
    public void setAction(){
        thuvienvuive.Excel.XuatExcel Excel= new XuatExcel();
        PDF xuatPDF= new PDF();
        ThongKe.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ThongKeSachMuonBUS ThongkeBus = new ThongKeSachMuonBUS();
                ObservableList<ThongKeSachHongDTO> List;
                {
                    try {
                        FromTo.setText("Từ ngày "+FromDate.getValue()+" đến ngày "+ToDate.getValue());
                        List = ThongkeBus.getSachHong(FromDate.getValue(), ToDate.getValue());
                        InsertTableSachHong(List);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        XuatExcel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Excel.xuatFileExcelThongKeSachHong(FromDate.getValue(),ToDate.getValue());
            }
        });
        XuatPDF.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                xuatPDF.writeThongKeSachHong(FromDate.getValue(),ToDate.getValue());
            }
        });
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
    public void InsertTableSachHong(ObservableList<ThongKeSachHongDTO> List){
        if (FromDate.getValue()!=null)
            if (ToDate.getValue()!=null)
                if (FromDate.getValue().isAfter(ToDate.getValue()))
                {
                    errorAlert("Thông báo", "Khoảng ngày không hợp lệ.");
                }
                else {
                    IDPhieuMuon.setCellValueFactory(new PropertyValueFactory<ThongKeSachHongDTO, String>("IDPhieuMuon"));
                    IDTheLoai.setCellValueFactory(new PropertyValueFactory<ThongKeSachHongDTO, String>("IDTheLoai"));
                    TenSach.setCellValueFactory(new PropertyValueFactory<ThongKeSachHongDTO, String>("TenSach"));
                    IDMember.setCellValueFactory(new PropertyValueFactory<ThongKeSachHongDTO, String>("IDMember"));
                    IDTacGia.setCellValueFactory(new PropertyValueFactory<ThongKeSachHongDTO, String>("IDTacGia"));
                    NgayHong.setCellValueFactory(new PropertyValueFactory<ThongKeSachHongDTO, LocalDate>("NgayLap"));
                    table.setItems(List);
                }
            else{
                errorAlert("Thông báo", "Ngày không được để trống");
            }else{
            errorAlert("Thông báo", "Ngày không được để trống");
        }

    }

}
