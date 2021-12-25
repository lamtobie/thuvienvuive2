package thuvienvuive.Book;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import thuvienvuive.Member.MemberListDAO;
import thuvienvuive.Member.MemberDTO;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static thuvienvuive.Book.compensationSlipController.IDMax;

public class returnBookController implements Initializable {
    @FXML
    ImageView titleIcon;
    @FXML
    ImageView closeIcon;

    @FXML
    private Label bookNameLabel;

    @FXML
    private Label memberNameLabel;

    @FXML
    private Button closeButton;

    @FXML
    private Button deleteButton;

    @FXML
    private DatePicker issueDate;

    @FXML
    private Button lostButton;

    @FXML
    private ComboBox<String> statusBox;

    @FXML
    private TextField memberIDFied;

    @FXML
    private TextField bookIDFied;

    @FXML
    private TextArea noteField;

    @FXML
    private Button returnButton;

    @FXML
    private DatePicker returnDate;

    @FXML
    private AnchorPane wrap;

    @FXML
    private TableView<bookIssueDTO> issueBookList;

    @FXML
    private TableColumn<bookIssueDTO, String> bookIDColumn;

    @FXML
    private TableColumn<bookIssueDTO, String> statusColumn;

    @FXML
    private TableColumn<bookIssueDTO, LocalDate> returnDateColumn;

    @FXML
    private TableColumn<bookIssueDTO, String> noteColumn;

    @FXML
    private TableColumn<bookIssueDTO, String> memberIDColumn;

    @FXML
    private TableColumn<bookIssueDTO, LocalDate> issueDateColumn;

    @FXML
    private Button printButton;

    @FXML
    private ImageView printIcon;

    @FXML
    private Button refreshButton;

    @FXML
    private ImageView refreshIcon;

    Stage primaryStage;
    static ObservableList<bookIssueDTO> List;
    static ObservableList<Book> BookList;
    returnBookDAO ListDAO = new returnBookDAO();
    compensationSlipDAO compensationSlipDAO = new compensationSlipDAO();
    static bookIssueDTO selectedIssue = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDefault();
        try{
            show(readListByStatus(List));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        handler();
    }

    public void show(ObservableList<bookIssueDTO> List) throws Exception {
        bookIDColumn.setCellValueFactory(new PropertyValueFactory<>("IDSach"));
        memberIDColumn.setCellValueFactory(new PropertyValueFactory<>("IDThanhVien"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
        issueDateColumn.setCellValueFactory(new PropertyValueFactory<>("ngayMuon"));
        returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("ngayTra"));
        noteColumn.setCellValueFactory(new PropertyValueFactory<>("ghiChu"));
        issueBookList.setItems(List);
    }

    private void close(){
        primaryStage = (Stage) wrap.getScene().getWindow();
        primaryStage.close();
    }

    public void handler(){
        closeButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                close();
            }
        });

        issueBookList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    issueBookDetails(selectRow());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        statusBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String status = statusType();
                switch(status) {
                    case "Đang mượn" -> {
                        try {
                            show(readListByStatus(List));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    case "Đã mất" -> {
                        try {
                            show(readListByStatus(List));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    case "Đã trả" -> {
                        try {
                            show(readListByStatus(List));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    default -> {
                        try {
                            show(readListByStatus(List));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        returnButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(selectRow() == null){
                    alert("Thông báo", "Vui lòng chọn 1 hàng để thao tác.");
                }
                else{
                    String status = "Đã trả";
                    String currentStatus = selectRow().getTrangThai();
                    if(normalizeString(status).contains(normalizeString(currentStatus))){
                        alert("Thông báo", "Trạng thái thay đổi phải khác trạng thái hiện tại.");
                    }
                    else{
                        if(normalizeString(currentStatus).contains(normalizeString("Đã mất"))){
                            try {
                                //xoá bồi thường
                                compensationSlipDAO cmpDAO = new compensationSlipDAO();
                                cmpDAO.deleteCompensation(selectedIssue.getIDPhieuMuon());
                                update(selectRow(), status, selectBookAmount(selectRow(), BookList)+1);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        else {
                            try {
                                update(selectRow(), status, selectBookAmount(selectRow(), BookList)+1);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

            }
        });

        lostButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(selectRow() == null){
                    alert("Thông báo", "Vui lòng chọn 1 hàng để thao tác.");
                }
                else{
                    String status = "Đã mất";
                    String currentStatus = selectRow().getTrangThai();
                    if(normalizeString(status).contains(normalizeString(currentStatus))){
                        alert("Thông báo", "Trạng thái thay đổi phải khác trạng thái hiện tại.");
                    }
                    else{
                        try {
                            Stage compensationSlipStage = new Stage();
                            compensationSlipMain compensationSlip = new compensationSlipMain();

                            primaryStage = (Stage) wrap.getScene().getWindow();
                            compensationSlipStage.initModality(Modality.WINDOW_MODAL);
                            compensationSlipStage.initOwner(primaryStage);

                            try {
                                compensationSlip.start(compensationSlipStage);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        deleteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(selectRow() == null){
                    alert("Thông báo", "Vui lòng chọn 1 hàng để thao tác.");
                }
                else{
                    String status = "Đang mượn";
                    String currentStatus = selectRow().getTrangThai();
                    if(normalizeString(status).contains(normalizeString(currentStatus))){
                        alert("Thông báo", "Trạng thái thay đổi phải khác trạng thái hiện tại.");
                    }
                    else{
                        if(normalizeString(currentStatus).contains(normalizeString("Đã mất"))){
                            try {
                                //xoá bồi thường
                                compensationSlipDAO cmpDAO = new compensationSlipDAO();
                                cmpDAO.deleteCompensation(selectedIssue.getIDPhieuMuon());
                                update(selectRow(), status, selectBookAmount(selectRow(), BookList));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        else {
                            try {
                                update(selectRow(), status, selectBookAmount(selectRow(), BookList)-1);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        refreshButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    show(readListByStatus(List));
                    issueBookDetails(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

    public void update(bookIssueDTO bookIssue, String status, int soLuong) throws Exception {
        if(bookIssue == null){
            alert("Thông báo", "Vui lòng chọn một hàng để thao tác!");
        }
        else{
            if(returnDate.getValue().isAfter(bookIssue.getNgayMuon()) || returnDate.getValue().isEqual(bookIssue.getNgayMuon())){
                bookIssue.setNgayTra(returnDate.getValue());
                if(ListDAO.updateStatus(bookIssue, status) > 0 && ListDAO.updateBook(bookIssue, soLuong) > 0){
                    alert("Thông báo", "Đã cập nhập trạng thái!");
                    show(readListByStatus(List));
                    issueBookDetails(bookIssue);
                }
                else{
                    errorAlert("Lỗi", "Có lỗi xảy ra trong quá trình cập nhật!");
                }
            }
            else{
                errorAlert("Lỗi", "Ngày trả sách không hợp lệ!");
            }
        }
    }

    public int selectBookAmount(bookIssueDTO bookIssue, ObservableList<Book> bookList){
        Book book = new Book();
        for(Book books : bookList){
            if(books.getID().equals(bookIssue.getIDSach())){
                book = books;
                break;
            }
        }
        return book.getSoLuong();
    }

    public ObservableList<bookIssueDTO> readListByStatus(ObservableList<bookIssueDTO> List) throws Exception{
        List = ListDAO.readIssueDAO();
        DeleteBookDAO book = new DeleteBookDAO();
        BookList = book.readListDAO();
        String status = statusType();
        if (status.equals("Tất cả"))
            return List;

        ObservableList<bookIssueDTO> list = FXCollections.observableArrayList();
        for(bookIssueDTO bookIssueDTO : List){
            if (normalizeString(status).contains(normalizeString(bookIssueDTO.getTrangThai()))){
                list.add(bookIssueDTO);
            }
        }
        return list;
    }

    private String normalizeString(String string){
        return string.trim().toLowerCase();
    }

    private String statusType(){
        return statusBox.getSelectionModel().getSelectedItem();
    }

    public void issueBookDetails(bookIssueDTO bookIssue) throws Exception{
        if (bookIssue == null){
            bookNameLabel.setText("");
            memberIDFied.setText("");
            bookIDFied.setText("");
            memberNameLabel.setText("");
            issueDate.setValue(null);
            returnDate.setValue(null);
            noteField.setText("");
        }
        else{
            bookIDFied.setText(bookIssue.getIDSach());
            for(Book book : BookList){
                if(book.getID().equals(bookIssue.getIDSach()))
                    bookNameLabel.setText(book.getTen());
            }

            MemberListDAO memberList = new MemberListDAO();
            ObservableList<MemberDTO> list;
            list = memberList.readListDAO();
            memberIDFied.setText(bookIssue.getIDThanhVien());
            for (MemberDTO thanhVien : list){
                if (thanhVien.getID().equals(bookIssue.getIDThanhVien()))
                    memberNameLabel.setText(thanhVien.getHo() + " " + thanhVien.getTen());
            }

            issueDate.setValue(bookIssue.getNgayMuon());
            returnDate.setValue(bookIssue.getNgayTra());
            noteField.setText(bookIssue.getGhiChu());
        }
    }

    public bookIssueDTO selectRow(){
        if (issueBookList.getSelectionModel().getSelectedIndex() < 0)
            return null;
        selectedIssue = issueBookList.getSelectionModel().getSelectedItem();
        return selectedIssue;
    }

    private void setDefault(){
        ObservableList<String> statusTypeList = FXCollections.observableArrayList(
                new String("Tất cả"),
                new String("Đã mất"),
                new String("Đã trả"),
                new String("Đang mượn")
        );
        statusBox.setItems(statusTypeList);
        statusBox.getSelectionModel().select(0);
        setIcon();
    }

    public  void setIcon(){
        File iconFile = new File("src/image/book_icon.png");
        Image iconTitle=new Image(iconFile.toURI().toString());
        titleIcon.setImage(iconTitle);

        File iconButton = new File("src/image/exit_icon.png");
        Image ButtonIcon=new Image(iconButton.toURI().toString());
        closeIcon.setImage(ButtonIcon);

        File refresh = new File("src/image/refresh_icon.png");
        Image RefreshIcon=new Image(refresh.toURI().toString());
        refreshIcon.setImage(RefreshIcon);

        File print = new File("src/image/print_icon.png");
        Image PrintIcon=new Image(print.toURI().toString());
        printIcon.setImage(PrintIcon);
    }
}