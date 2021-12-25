package thuvienvuive.Member;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.ResourceBundle;

public class MemberListController implements Initializable {
    Stage primaryStage;
    MemberListDAO ListDAO = new MemberListDAO();
    static ObservableList<MemberDTO> List;
    {
        try {
            List = ListDAO.readListDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Button closeButton;

    @FXML
    private TableView<MemberDTO> memberList;

    @FXML
    private TableColumn<MemberDTO, String> emailColumn;

    @FXML
    private TableColumn<MemberDTO, String> firstNameColumn;

    @FXML
    private TableColumn<MemberDTO, String> genderColumn;

    @FXML
    private TableColumn<MemberDTO, String> lastNameColumn;

    @FXML
    private TableColumn<MemberDTO, String> phoneNumberColumn;

    @FXML
    private TableColumn<MemberDTO, String> IDColumn;

    @FXML
    private ComboBox<String> searchBox;

    @FXML
    private TextField searchField;

    @FXML
    private AnchorPane wrap;

    @FXML
    private ImageView memberAvavtar;

    @FXML
    private Button searchButton;

    @FXML
    private TreeView<String> informationView;

    @FXML
    ImageView titleIcon;
    @FXML
    ImageView closeIcon;
    @FXML
    ImageView searchIcon;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDefault();
        try {
            show(List);
        } catch (Exception e) {
            e.printStackTrace();
        }
        handler();
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
    public void show(ObservableList<MemberDTO> List){
        //gán giá trị vào ô trong bảng
        IDColumn.setCellValueFactory(new PropertyValueFactory<MemberDTO, String>("ID"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<MemberDTO, String>("ho"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<MemberDTO, String>("ten"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<MemberDTO, String>("soDienThoai"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<MemberDTO, String>("email"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<MemberDTO, String>("gioiTinh"));
        memberList.setItems(List);
    }

    //chọn kiểu tìm kiếm trogng phần tiềm kiếm
    private String searchType(){
        return searchBox.getSelectionModel().getSelectedItem();
    }

    //lấy chi tiết thông tin thành viên
    private void memberDetails(MemberDTO member){
        //không chọn bất kì hàng nào
        if(member == null) {

        }
        //chọn 1 hàng trong bảng
        else{
            //lấy avatar của thành viên
//            Image avatar = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../../image/avatar/non_avatar.png")));
//            memberAvavtar.setImage(avatar);
            File iconFile = new File("src/image/avatar/" + member.getHinhAnh());
            Image image = new Image(iconFile.toURI().toString());
            memberAvavtar.setImage(image);

            //tạo treeView để hiển thị chi tiết thông tin thành viên
            TreeItem<String> root = new TreeItem<>("Thông tin:");

            TreeItem<String> nodeHoVaTen = new TreeItem<>("Họ và tên:");
            TreeItem<String> hoVaTen = new TreeItem<>(member.getHo() + " " + member.getTen());

            TreeItem<String> nodeGioiTinh = new TreeItem<>("Giới tính:");
            TreeItem<String> gioiTinh = new TreeItem<>(member.getGioiTinh());

            TreeItem<String> nodeID = new TreeItem<>("ID:");
            TreeItem<String> ID = new TreeItem<>(member.getID());

            TreeItem<String> nodeSdt = new TreeItem<>("Số điện thoại:");
            TreeItem<String> sdt = new TreeItem<>(member.getSoDienThoai());

            TreeItem<String> nodeEmail = new TreeItem<>("Email:");
            TreeItem<String> email = new TreeItem<>(member.getEmail());

            //mở rộng khi chạy
            root.setExpanded(true);
            //thêm node vào treeView
            root.getChildren().addAll(nodeHoVaTen, nodeGioiTinh, nodeID, nodeSdt, nodeEmail);
            //tạo các node con
            nodeHoVaTen.getChildren().add(hoVaTen);
            nodeHoVaTen.setExpanded(true);

            nodeGioiTinh.getChildren().add(gioiTinh);
            nodeGioiTinh.setExpanded(true);

            nodeID.getChildren().add(ID);
            nodeID.setExpanded(true);

            nodeSdt.getChildren().add(sdt);
            nodeSdt.setExpanded(true);

            nodeEmail.getChildren().add(email);
            nodeEmail.setExpanded(true);
            informationView.setRoot(root);
        }

    }

    //đóng cửa sổ
    private void close(){
        primaryStage = (Stage) wrap.getScene().getWindow();
        primaryStage.close();
    }

    //xử lý các sự kiện
    private void handler(){
        //đóng cửa sổ
        closeButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                close();
            }
        });
        //lấy chi tiết thông tin thành viên
        memberList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                memberDetails(selectRow());
            }
        });
        //thiết lập cho loại tìm kiếm "Tất cả"
        searchBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String searchType = searchType();
                if (searchType.equals("Tất cả")){
                    searchField.setText("");
                    searchField.setEditable(false);
                    try {
                        show(List);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else
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
                if (searchType == null){
                    alert("Thông báo", "Hãy chọn loại tìm kiếm!");
                }
                //nếu giá trị tìm kiếm trống
                else if (searchValue.equals("") && !searchType.equals("Tất cả")){
                    alert("Thông báo", "Hãy nhập thông tin để tìm kiếm!");
                }
                //nếu giá trị tìm kiếm là tất cả
                else if(searchType.equals("Tất cả")){
                    alert("Thông báo", "Đã hiện tất cả danh sách!");
                }
                else {
                    //tạo danh sách để lưu kết quả tìm kiếm
                    ObservableList<MemberDTO> searchList = FXCollections.observableArrayList();
                    //chọn kiểu tìm kiếm
                    switch (searchType) {
                        case "Họ" -> {
                            //đọc danh và so sánh
                            for (MemberDTO member : List) {
                                if (normalizeString(member.getHo()).contains(normalizeString(searchValue))){
                                    //thêm vào danh sách tìm kiếm
                                    searchList.add(member);
                                }

                            }
                            //đọc lại danh sách
                            try {
                                show(searchList);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                        case "Tên" -> {
                            for (MemberDTO member : List) {
                                if (normalizeString(member.getTen()).contains(normalizeString(searchValue)))
                                    searchList.add(member);
                            }
                            try {
                                show(searchList);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        case "Số điện thoại" -> {
                            for (MemberDTO member : List) {
                                if (normalizeString(member.getSoDienThoai()).contains(normalizeString(searchValue)))
                                    searchList.add(member);
                            }
                            try {
                                show(searchList);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        case "Email" -> {
                            for (MemberDTO member : List) {
                                if (normalizeString(member.getEmail()).contains(normalizeString(searchValue)))
                                    searchList.add(member);
                            }
                            try {
                                show(searchList);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        case "ID" -> {
                            for (MemberDTO member : List) {
                                if (normalizeString(member.getID()).contains(normalizeString(searchValue)))
                                    searchList.add(member);
                            }
                            try {
                                show(searchList);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        default -> {
                            try {
                                show(List);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
    }

    //chuẩn hoá chuỗi thành in thường và bỏ khoảng trắng
    private String normalizeString(String string){
        return string.trim().toLowerCase();
    }

    //chọn 1 dòng trong bảng
    private MemberDTO selectRow(){
        //nếu như bấm ngoài bảng
        if(memberList.getSelectionModel().getSelectedIndex() < 0){
            return null;
        }
        return memberList.getSelectionModel().getSelectedItem();
    }

    //thiết lập các giá trị mặc định
    private void setDefault(){
        //thiết lập các kiểu tìm kiếm
        ObservableList<String> searTypeList = FXCollections.observableArrayList(
                new String("Tất cả"),
                new String("Họ"),
                new String("Tên"),
                new String("Email"),
                new String("Số điện thoại"),
                new String("ID")
        );
        searchField.setEditable(false);
        searchBox.setItems(searTypeList);
        searchBox.getSelectionModel().select(0);
        setIcon();
    }
    public  void setIcon(){
        File iconFile = new File("src/image/member_icon.png");
        Image iconTitle=new Image(iconFile.toURI().toString());
        titleIcon.setImage(iconTitle);
        File iconButton = new File("src/image/exit_icon.png");
        Image ButtonIcon=new Image(iconButton.toURI().toString());
        closeIcon.setImage(ButtonIcon);
        File searchFile = new File("src/image/search_icon.png");
        Image iconSearch=new Image(searchFile.toURI().toString());
        searchIcon.setImage(iconSearch);
    }
}
