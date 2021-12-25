package thuvienvuive.Dashboard.Admin;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.stage.Modality;
import thuvienvuive.Author.TacGiaBUS;
import thuvienvuive.Login.LoginController;
import thuvienvuive.Member.*;
import thuvienvuive.Book.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import thuvienvuive.Author.AuthorController;
import thuvienvuive.Genre.GenreController;
import thuvienvuive.ThongKe.ThongKeSachMuonItController;
import thuvienvuive.ThongKe.ThongKeSachMuonNhieuController;
import thuvienvuive.User.ManagerUserController;
import thuvienvuive.User.NhanVienBUS;
import thuvienvuive.User.NhanVienDTO;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AdminController implements Initializable {
    //icon
    @FXML
    ImageView imageIcon;
    //ảnh sản phẩm mới
    @FXML
    ImageView imageNewBook1;
    @FXML
    ImageView imageNewBook2;
    @FXML
    ImageView imageNewBook3;
    @FXML
    ImageView imageNewBook4;
    @FXML
    ImageView imageNewBook5;
    //menu
    @FXML
    TreeView<String> treeMenu;
    //tên admin
    @FXML
    Label adminNameLabel;
    //thống kê sách-admin-tác giả
    @FXML
    Label countBooks;
    @FXML
    Label countMembers;
    @FXML
    Label countAuthors;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //icon
        addIcon();
        //add menu
        addMenu();

        //thống kê sách-admin-tác giả
        try {
            statistical();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            addImageNewBook();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addIcon(){
        File iconFile = new File("src/image/OIP.png");
        Image iconImage=new Image(iconFile.toURI().toString());
        imageIcon.setImage(iconImage);
    }
    public void addMenu(){
        TreeItem<String> root=new TreeItem<>();


        TreeItem<String> rootMembers=new TreeItem<>("Thủ thư");
        TreeItem<String> itemMembersList=new TreeItem<>("Quản lý thủ thư");


        TreeItem<String> rootStatistical=new TreeItem<>("Thống kê");
        TreeItem<String> itemLessBorrowBook=new TreeItem<>("Thống kê sách mượn ít");
        TreeItem<String> itemMoreBorrowBook=new TreeItem<>("Thống kê sách mượn nhiều");
        TreeItem<String> itemBrokenBook=new TreeItem<>("Thống kê sách hư hỏng");

        //root tự mở

        rootMembers.setExpanded(true);
        rootStatistical.setExpanded(true);

        root.getChildren().addAll(rootMembers,rootStatistical);
        rootMembers.getChildren().addAll(itemMembersList);
        rootStatistical.getChildren().addAll(itemLessBorrowBook,itemMoreBorrowBook,itemBrokenBook);

        root.setExpanded(true);
        treeMenu.setRoot(root);
        treeMenu.setShowRoot(false);
        EventHandler<MouseEvent> mouseEventHandle = (MouseEvent event) -> {
            try {
                handleMouseClicked(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        treeMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandle);
    }
    static NhanVienDTO admin;
    public void setAdminName(NhanVienDTO nhanVienDTO){
        //tên userNameLabel
        admin=nhanVienDTO;
        adminNameLabel.setText(admin.getHo()+" "+admin.getTen());
    }
    public void statistical() throws Exception{
        BookBUS bookbus=new BookBUS();
        int countBs=bookbus.countBooks();
        NhanVienBUS nhanVienbus=new NhanVienBUS();
        int countMs=nhanVienbus.countNhanVien();
        TacGiaBUS authorBUS=new TacGiaBUS();
        int countAs=authorBUS.countAuthors();
        countBooks.setText(String.valueOf(countBs));
        countMembers.setText(String.valueOf(countMs) );
        countAuthors.setText(String.valueOf(countAs));
    }
    public void addImageNewBook() throws  Exception{
        BookBUS bookbus=new BookBUS();
        ObservableList<Book> listBooks= bookbus.findfiveNewBooks();

        File imageFile1 = new File(listBooks.get(0).getHinhAnh());
        Image imageImage1=new Image(imageFile1.toURI().toString());
        imageNewBook1.setImage(imageImage1);

        File imageFile2 = new File(listBooks.get(1).getHinhAnh());
        Image imageImage2=new Image(imageFile2.toURI().toString());
        imageNewBook2.setImage(imageImage2);

        File imageFile3 = new File(listBooks.get(2).getHinhAnh());
        Image imageImage3=new Image(imageFile3.toURI().toString());
        imageNewBook3.setImage(imageImage3);

        File imageFile4 = new File(listBooks.get(3).getHinhAnh());
        Image imageImage4=new Image(imageFile4.toURI().toString());
        imageNewBook4.setImage(imageImage4);

        File imageFile5 = new File(listBooks.get(4).getHinhAnh());
        Image imageImage5=new Image(imageFile5.toURI().toString());
        imageNewBook5.setImage(imageImage5);
    }

    public void handleMouseClicked(MouseEvent event) throws IOException {
        Node node = event.getPickResult().getIntersectedNode();
        String name;
        Parent root;
        // Accept clicks only on node cells, and not on empty spaces of the TreeView
        if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
            name = (String) ((TreeItem)treeMenu.getSelectionModel().getSelectedItem()).getValue();
            switch (name){

                case "Quản lý thủ thư":
                    openManageUser(event);
                    break;

                case "Thống kê sách mượn ít":
                    openBorrowBook(event);
                    break;
                case "Thống kê sách mượn nhiều":
                    openBorrowBook2(event);
                    break;
                case "Thống kê sách hư hỏng":
                    openBrokenBook(event);
                    break;
            }
        }

    }

    //Danh sách thủ thư
    public void openManageUser(MouseEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(ManagerUserController.class.getResource("ManagerUser.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage newstage = new Stage();
        newstage.setTitle("Danh sách nhân viên");
        newstage.setScene(scene);
        //ngăn tương tác với dashboard
        Stage oldStage=(Stage) ((Node) e.getSource()).getScene().getWindow();
        newstage.initModality(Modality.WINDOW_MODAL);
        newstage.initOwner(oldStage);
        //chạy newStage
        newstage.show();
    }

    public void openBorrowBook(MouseEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(ThongKeSachMuonItController.class.getResource("ThongKeSachMuonIt.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage newstage = new Stage();
        newstage.setTitle("Thống kê sách mượn ít");
        newstage.setScene(scene);
        //ngăn tương tác với dashboard
        Stage oldStage=(Stage) ((Node) e.getSource()).getScene().getWindow();
        newstage.initModality(Modality.WINDOW_MODAL);
        newstage.initOwner(oldStage);
        //chạy newStage
        newstage.show();
    }
    public void openBorrowBook2(MouseEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(ThongKeSachMuonNhieuController.class.getResource("ThongKeSachMuonNhieu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage newstage = new Stage();
        newstage.setTitle("Thống kê sách mượn nhiều");
        newstage.setScene(scene);
        //ngăn tương tác với dashboard
        Stage oldStage=(Stage) ((Node) e.getSource()).getScene().getWindow();
        newstage.initModality(Modality.WINDOW_MODAL);
        newstage.initOwner(oldStage);
        //chạy newStage
        newstage.show();
    }
    public void openBrokenBook(MouseEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(ThongKeSachMuonItController.class.getResource("ThongKeSachHong.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage newstage = new Stage();
        newstage.setTitle("Thống kê sách hỏng");
        newstage.setScene(scene);
        //ngăn tương tác với dashboard
        Stage oldStage=(Stage) ((Node) e.getSource()).getScene().getWindow();
        newstage.initModality(Modality.WINDOW_MODAL);
        newstage.initOwner(oldStage);
        //chạy newStage
        newstage.show();
    }
    public void logout(ActionEvent e) throws Exception{
        Stage stage=(Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(LoginController.class.getResource("LoginGUI.fxml"));
        Parent root=loader.load();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dashboard Admin");
        System.out.printf("admin");
    }
}
