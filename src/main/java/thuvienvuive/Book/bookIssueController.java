package thuvienvuive.Book;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import thuvienvuive.Login.LoginController;
import thuvienvuive.Member.MemberDTO;
import thuvienvuiveReport.PDF;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ResourceBundle;

public class bookIssueController implements Initializable {
    Stage primaryStage;
    bookIssueDTO phieuMuon= new bookIssueDTO();
    static int maxID = 0;
    bookIssueDAO bookIssueDAO = new bookIssueDAO();
    PDF xuatPDF= new PDF();
    static ObservableList<bookIssueDTO> issueList;
    {
        try{
            issueList = bookIssueDAO.readIssueBookListDAO();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    static ObservableList<Book> bookList;
    {
        try{
            bookList = bookIssueDAO.readBookListDAO();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    static ObservableList<MemberDTO> memberList;
    {
        try{
            memberList = bookIssueDAO.readMemberListDAO();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private Label bookNameLabel;

    @FXML
    private Label memberNameLabel;

    @FXML
    private Spinner<String> bookIDSpinner;

    @FXML
    private TextField bookIDText;

    @FXML
    private ImageView bookOpen;

    @FXML
    private Button bookSearchButton;

    @FXML
    private Button cancelButton;

    @FXML
    private DatePicker issueDate;

    @FXML
    private Spinner<String> memberIDSpinner;

    @FXML
    private TextField memberIDText;

    @FXML
    private Button memberSearchButton;

    @FXML
    private TextArea noteTextArea;

    @FXML
    private DatePicker returnDate;

    @FXML
    private Label statusLabel;

    @FXML
    private Button submitButton;

    @FXML
    private Button closeButton;
    @FXML
    private Button XuatPDF;

    @FXML
    private AnchorPane wrap;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setDefault();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

        bookIDSpinner.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                bookIDText.setText(selectBookID());
                bookNameLabel.setText(selectBook(selectBookID()).getTen());
                setStatusLabel(selectBook(selectBookID()));
            }
        });

        memberIDSpinner.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                memberIDText.setText(selectMemberID());
                memberNameLabel.setText(selectMember(selectMemberID()).getTen());
            }
        });

        bookSearchButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String searchValue = bookIDText.getText();
                if (searchIDBook(searchValue) != null){
                    alert("Thông báo", "Đã tìm thấy sách có ID: " + searchValue);
                    bookNameLabel.setText(selectBook(searchValue).getTen());
                    setStatusLabel(selectBook(searchValue));
                }
                else{
                    Book book = new Book();
                    alert("Thông báo", "Không tìm thấy sách có ID: " + searchValue);
                    bookNameLabel.setText("");
                    setStatusLabel(book);
                }
            }
        });

        memberSearchButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String searchValue = memberIDText.getText();
                if (searchIDMember(searchValue) != null){
                    alert("Thông báo", "Đã tìm thấy sách có ID: " + searchValue);
                    memberNameLabel.setText(selectMember(searchValue).getTen());
                    setStatusLabel(selectBook(bookIDText.getText()));
                }
                else{
                    Book book = new Book();
                    alert("Thông báo", "Không tìm thấy sách có ID: " + searchValue);
                    memberNameLabel.setText("");
                    setStatusLabel(book);
                }
            }
        });

        submitButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    submit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        XuatPDF.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                xuatPDF.writeXuatPhieuMuon(phieuMuon);
            }
        });
    }

    private void setMaxID(){
        for (bookIssueDTO issue : issueList){
            String number = issue.getIDPhieuMuon().substring(4);
            int tmp = Integer.parseInt(number);
            if (tmp > maxID){
                maxID = tmp;
            }
        }
    }

    public String setIssueID(){
        maxID++;
        return "IDPM" + maxID;
    }

    public void submit() throws Exception{
        if(bookIDText.getText() == null || memberIDText.getText() == null){
            alert("Thông báo", "Không tìm thấy sách hoặc thành viên.");
        }
        else if (!returnDate.getValue().isAfter(issueDate.getValue())){
            alert("Thông báo", "Ngày trả sách không hợp lệ.");
        }
        else{
            bookIssueDTO issue = new bookIssueDTO(
                    setIssueID(),
                    memberIDText.getText(),
                    LoginController.nhanVien.getIDNhanVien(),
                    issueDate.getValue(),
                    returnDate.getValue(),
                    noteTextArea.getText(),
                    bookIDText.getText()

            );
            phieuMuon=issue;
            if (bookIssueDAO.insertIssue(issue) > 0 && bookIssueDAO.update(issue, selectBookAmount(issue, bookList)) > 0){
                bookList = bookIssueDAO.readBookListDAO();
                alert("Thông báo", "Đã lập phiếu mượn thành công.");
            }
            else{
                errorAlert("Thông báo", "Có lỗi xảy ra trong quá trình thực hiện.");
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

    private void setStatusLabel(Book book){
        if(book.getSoLuong() == 0){
            statusLabel.setText("Đã hết sách.");
            submitButton.setDisable(true);
        }
        else if(book.getSoLuong() < 0){
            statusLabel.setText("");
            submitButton.setDisable(true);
        }
        else{
            statusLabel.setText("Sách vẫn còn.");
            submitButton.setDisable(false);
        }
    }

    public String searchIDBook(String IDBook){
        String result = null;
        for(Book book : bookList){
            if (normalizeString(book.getID()).equals(normalizeString(IDBook))){
                result = book.getID();
                break;
            }
        }
        return result;
    }

    public String searchIDMember(String IDBook){
        String result = null;
        for(MemberDTO member : memberList){
            if (normalizeString(member.getID()).equals(normalizeString(IDBook))){
                result = member.getID();
                break;
            }
        }
        return result;
    }

    public void setDefault() throws Exception {
        setIcon();
        setSpinnerBookIDList();
        setSpinnerMemberIDList();
        setMaxID();
        issueDate.setValue(LocalDate.now());
        returnDate.setValue(LocalDate.now().plusDays(10));
    }

    private ObservableList<String> getBookIDList(ObservableList<Book> List){
        ObservableList<String> IDBook = FXCollections.observableArrayList();
        for (Book book : List){
            IDBook.add(book.getID());
        }
        return IDBook;
    }

    private ObservableList<String> getMemberIDList(ObservableList<MemberDTO> List){
        ObservableList<String> IDMember = FXCollections.observableArrayList();
        for (MemberDTO member : List){
            IDMember.add(member.getID());
        }
        return IDMember;
    }

    private void close(){
        primaryStage = (Stage) wrap.getScene().getWindow();
        primaryStage.close();
    }

    public ObservableList<Book> readBookList() throws Exception{
        bookList = bookIssueDAO.readBookListDAO();
        return bookList;
    }

    public ObservableList<MemberDTO> readMemberList() throws Exception{
        memberList = bookIssueDAO.readMemberListDAO();
        return memberList;
    }

    public ObservableList<bookIssueDTO> readIssueList() throws Exception{
        issueList = bookIssueDAO.readIssueBookListDAO();
        return issueList;
    }

    private void setSpinnerBookIDList() throws Exception{
        SpinnerValueFactory<String> values = new SpinnerValueFactory.ListSpinnerValueFactory<String>(getBookIDList(readBookList()));
        bookIDSpinner.setValueFactory(values);
        bookIDSpinner.getValueFactory().wrapAroundProperty().set(true);
        bookIDText.setText(selectBookID());
        bookNameLabel.setText(selectBook(selectBookID()).getTen());
        setStatusLabel(selectBook(selectBookID()));
    }

    private void setSpinnerMemberIDList() throws Exception{
        SpinnerValueFactory<String> values = new SpinnerValueFactory.ListSpinnerValueFactory<String>(getMemberIDList(readMemberList()));
        memberIDSpinner.setValueFactory(values);
        memberIDSpinner.getValueFactory().wrapAroundProperty().set(true);
        memberIDText.setText(selectMemberID());
        memberNameLabel.setText(selectMember(selectMemberID()).getTen());
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

    public Book selectBook(String bookID) {
        Book book = null;
        for(Book tmp : bookList){
            if (normalizeString(tmp.getID()).contains(normalizeString(bookID))){
                book = tmp;
                break;
            }
        }
        return book;
    }

    public MemberDTO selectMember(String memberID) {
        MemberDTO member = null;
        for(MemberDTO tmp : memberList){
            if (normalizeString(tmp.getID()).contains(normalizeString(memberID))){
                member = tmp;
                break;
            }
        }
        return member;
    }

    public String selectBookID() {
        return bookIDSpinner.getValue();
    }

    public String selectMemberID() {
        return memberIDSpinner.getValue();
    }

    private String normalizeString(String string){
        return string.trim().toLowerCase();
    }

    public  void setIcon(){
        File iconFile = new File("src/image/book_open.png");
        Image iconTeam=new Image(iconFile.toURI().toString());
        bookOpen.setImage(iconTeam);
    }
}