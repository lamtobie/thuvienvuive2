package thuvienvuive.Book;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class BookListController implements Initializable {
    Stage stage;
    BookListDAO ListDAO = new BookListDAO();
    static ObservableList<Book> List;
    {
        try{
            List = ListDAO.docDB();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    private Button closeButton;
    @FXML
    ImageView titleIcon;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;
    @FXML
    private DatePicker dateSearchField1, dateSearchField2;
    @FXML
    private TableView<Book> bookList;
    @FXML
    private TableColumn<Book, String> IDBookColumn;
    @FXML
    private TableColumn<Book, String> nameColumn;
    @FXML
    private TableColumn<Book, String> IDauthorColumn;
    @FXML
    private TableColumn<Book, Integer> quantityColumn;
    @FXML
    private TableColumn<Book, Float> priceColumn;
    @FXML
    private TableColumn<Book, LocalDate> publishDateColumn;
    @FXML
    private TableColumn<Book, LocalDate> daterevColumn;
    @FXML
    private TableColumn<Book, String> noteColumn;
    @FXML
    private TableColumn<Book, String> IDgenreColumn;
    @FXML
    private ImageView bookImage;
    @FXML
    private TreeView<String> informationView;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private ComboBox<String> searchBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDefault();
        try{
            show(List);
        }catch(Exception e){
            e.printStackTrace();
        }
        handler();
        Search2();
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

    //đọc danh sách để hiển thị
    public void show(ObservableList<Book> List){
        IDBookColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("ID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("ten"));
        IDauthorColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("IDTacGia"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("soLuong"));
        publishDateColumn.setCellValueFactory(new PropertyValueFactory<Book, LocalDate>("ngayXuatBan"));
        daterevColumn.setCellValueFactory(new PropertyValueFactory<Book, LocalDate>("ngayNhanSach"));
        noteColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("ghiChu"));
        IDgenreColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("IDTheLoai"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Book, Float>("giaTien"));
        bookList.setItems(List);
    }

    //chọn kiểu tìm kiếm trogng phần tiềm kiếm
    private String searchType(){
        return searchBox.getSelectionModel().getSelectedItem();
    }

    //lấy chi tiết thông tin sách
    private void bookDetails(Book bookDTO){
        if(bookDTO == null){

        }
        else{
            File bookFile = new File("src/image/sach/" + bookDTO.getHinhAnh());
            Image image = new Image(bookFile.toURI().toString());
            bookImage.setImage(image);

            //tạo treeView để hiển thị chi tiết thông tin sách
            TreeItem<String> root = new TreeItem<>("Thông tin:");

            TreeItem<String> nodeTenSach = new TreeItem<>("Tên sách:");
            TreeItem<String> tenSach = new TreeItem<>(bookDTO.getTen());

            TreeItem<String> nodeID = new TreeItem<>("Mã số:");
            TreeItem<String> iD = new TreeItem<>(bookDTO.getID());

            TreeItem<String> nodeNgayXuatBan = new TreeItem<>("Ngày xuất bản:");
            TreeItem<String> ngayXuatBan = new TreeItem<>(bookDTO.getNgayXuatBan().toString());

            TreeItem<String> nodeGiaTien = new TreeItem<>("Giá tiền:");
            TreeItem<String> giatien = new TreeItem<>(String.valueOf(bookDTO.getGiaTien()));

            //mở rộng khi chạy
            root.setExpanded(true);
            //thêm node vào treeView
            root.getChildren().addAll(nodeTenSach, nodeID, nodeNgayXuatBan, nodeGiaTien);
            //tạo các node con
            nodeTenSach.getChildren().add(tenSach);
            nodeTenSach.setExpanded(true);

            nodeID.getChildren().add(iD);
            nodeTenSach.setExpanded(true);

            nodeNgayXuatBan.getChildren().add(ngayXuatBan);
            nodeTenSach.setExpanded(true);

            nodeGiaTien.getChildren().add(giatien);
            nodeTenSach.setExpanded(true);

            informationView.setRoot(root);
        }
    }
    //đóng cửa sổ
    private void close(){
        stage = (Stage) mainPane.getScene().getWindow();
        stage.close();
    }

    //chọn 1 dòng trong bảng
    private Book selectRow(){
        //nếu như bấm ngoài bảng
        if(bookList.getSelectionModel().getSelectedIndex() < 0){
            return null;
        }
        return bookList.getSelectionModel().getSelectedItem();
    }

    //chuẩn hoá chuỗi thành in thường và bỏ khoảng trắng
    private String normalizeString(String string){
        return string.trim().toLowerCase();
    }

    //xử lý sự kiện
    private void handler() {
        //đóng cửa sổ
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                close();
            }
        });


        //lấy chi tiết thông tin sách
        bookList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                bookDetails(selectRow());
            }
        });

        //thiết lập cho loại tìm kiếm "Tất cả"
        searchBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String searchType = searchType();
                if (searchType.equals("Tất cả")) {
                    searchField.setText("");
                    searchField.setEditable(false);
                    try {
                        show(List);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else
                    searchField.setEditable(true);
            }
        });

        //tìm kiếm
        searchButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //lấy kiểu tìm kiếm
                String searchType = searchType();

                //lấy giá trị cần tìm
                String searchValue = searchField.getText();

                //nếu kiểu tìm kiếm rỗng
                if (searchType == null) {
                    alert("Thông báo", "Hãy chọn loại tìm kiếm!");
                }

                //nếu giá trị tìm kiếm trống
                else if (searchValue.equals("") && !searchType.equals("Tất cả")) {
                    alert("Thông báo", "Hãy nhập thông tin để tìm kiếm!");
                }

                //nếu giá trị tìm kiếm là tất cả
                else if (searchType.equals("Tất cả")) {
                    alert("Thông báo", "Đã hiện tất cả danh sách!");
                } else {
                    //tạo danh sách để lưu kết quả tìm kiếm
                    ObservableList<Book> searchList = FXCollections.observableArrayList();

                    //chọn kiểu tìm kiếm
                    switch (searchType) {
                        case "Tên sách" -> {
                            //đọc danh sách so sánh
                            for(Book book : List){
                                if(normalizeString(book.getTen()).contains(normalizeString(searchValue))){
                                    //thêm vào danh sách tìm kiếm
                                    searchList.add(book);
                                }
                            }
                            //đọc lại danh sách
                            try{
                                show(searchList);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                        case "Ghi chú" -> {
                            //đọc danh sách so sánh
                            for(Book book : List){
                                if(normalizeString(book.getGhiChu()).contains(normalizeString(searchValue))){
                                    //thêm vào danh sách tìm kiếm
                                    searchList.add(book);
                                }
                            }
                            //đọc lại danh sách
                            try{
                                show(searchList);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
    }

    //thiết lập các giá trị mặc định
    private void setDefault(){
        //thiết lập các kiểu tìm kiếm
        ObservableList<String> searTypeList = FXCollections.observableArrayList(
                new String("Tất cả"),
                new String("Tên sách"),
                new String("Ghi chú")
        );
        searchField.setEditable(false);
        searchBox.setItems(searTypeList);
        searchBox.getSelectionModel().select(0);
        setIcon();
    }

    //tìm kiếm theo ngày
    protected void Search2(){
        FilteredList<Book> filteredItems =  new FilteredList<>(List);
        filteredItems.predicateProperty().bind(Bindings.createObjectBinding(() -> {
                    LocalDate minDate = dateSearchField1.getValue();
                    LocalDate maxDate = dateSearchField2.getValue();

                    // get final values != null
                    final LocalDate finalMin = minDate == null ? LocalDate.MIN : minDate;
                    final LocalDate finalMax = maxDate == null ? LocalDate.MAX : maxDate;

                    // values for openDate need to be in the interval [finalMin, finalMax]
                    return ti -> !finalMin.isAfter(ti.getNgayNhanSach()) && !finalMax.isBefore(ti.getNgayNhanSach());
                },
                dateSearchField1.valueProperty(),
                dateSearchField2.valueProperty()));
        bookList.setItems(filteredItems);
    }

    public void setIcon(){
        File iconFile = new File("src/image/bookIcon.png");
        Image iconTeam=new Image(iconFile.toURI().toString());
        titleIcon.setImage(iconTeam);
    }
}