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
import thuvienvuive.Excel.XuatExcel;
import thuvienvuiveReport.PDF;

import java.net.URL;
import java.util.ResourceBundle;

public class ThongKeSachMuonNhieuController implements Initializable {

    @FXML
    private TableView table;
    @FXML
    private TableColumn IDSach;
    @FXML
    private TableColumn TenSach;
    @FXML
    private TableColumn IDTacGia;
    @FXML
    private TableColumn IDTheLoai;
    @FXML
    private TableColumn SoLuongTon;
    @FXML
    private TableColumn SoLanMuon;
    @FXML
    private DatePicker FromDate;
    @FXML
    private DatePicker ToDate;
    @FXML
    private Button ThongKe;
    @FXML
    private Button XuatExcel;
    @FXML
    private Button XuatPDF;
    @FXML
    private Text FromTo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setAction();
    }
    public void setAction(){
        ThongKe.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ThongKeSachMuonBUS ThongkeBus = new ThongKeSachMuonBUS();
                ObservableList<ThongKeSachMuonDTO> List;
                {
                    try {
                        FromTo.setText("Từ ngày "+FromDate.getValue()+" đến ngày "+ToDate.getValue());
                        List = ThongkeBus.getSachMuonNhieu(FromDate.getValue(), ToDate.getValue());
                        InsertTableSachNhieu(List);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thuvienvuive.Excel.XuatExcel Excel= new XuatExcel();
        PDF xuatPDF= new PDF();
        XuatExcel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Excel.xuatFileExcelThongKeSachMuonNhieu(FromDate.getValue(),ToDate.getValue());
            }
        });
        XuatPDF.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                xuatPDF.writeThongKeSachMuonNhieu(FromDate.getValue(),ToDate.getValue());
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
    public void InsertTableSachNhieu(ObservableList<ThongKeSachMuonDTO> List){
        if (FromDate.getValue()!=null)
            if (ToDate.getValue()!=null)
                if (FromDate.getValue().isAfter(ToDate.getValue()))
                {
                    errorAlert("Thông báo", "Khoảng ngày không hợp lệ.");
                }
                else {
                    IDSach.setCellValueFactory(new PropertyValueFactory<ThongKeSachMuonDTO, String>("IDSach"));
                    TenSach.setCellValueFactory(new PropertyValueFactory<ThongKeSachMuonDTO, String>("TenSach"));
                    IDTacGia.setCellValueFactory(new PropertyValueFactory<ThongKeSachMuonDTO, String>("IDTacGia"));
                    IDTheLoai.setCellValueFactory(new PropertyValueFactory<ThongKeSachMuonDTO, String>("IDTheLoai"));
                    SoLuongTon.setCellValueFactory(new PropertyValueFactory<ThongKeSachMuonDTO, Integer>("SoLuongTon"));
                    SoLanMuon.setCellValueFactory(new PropertyValueFactory<ThongKeSachMuonDTO, Integer>("SoLanMuon"));
                    table.setItems(List);
                }
            else{
                errorAlert("Thông báo", "Ngày không được để trống");
            }else{
            errorAlert("Thông báo", "Ngày không được để trống");
        }

    }

}
