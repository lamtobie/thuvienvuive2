package thuvienvuive.Dashboard.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Modality;
import thuvienvuive.Author.AuthorMain;
import thuvienvuive.Author.TacGiaBUS;
import thuvienvuive.Author.TacGiaDAO;
import thuvienvuive.Genre.*;
import thuvienvuive.Login.TaiKhoanDTO;
import thuvienvuive.Member.MemberListController;
import thuvienvuive.Member.*;
import thuvienvuive.Book.*;
import javafx.event.ActionEvent;
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
import thuvienvuive.User.ManagerUserController;
import thuvienvuive.User.NhanVienBUS;
import thuvienvuive.User.NhanVienDTO;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class UserController implements Initializable {
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
    TreeView <String> treeMenu;
    //tên user
    @FXML
    Label userNameLabel;


    //thống kê sách-admin-tác giả
    @FXML
    Label countBooks;
    @FXML
    Label countMembers;
    @FXML
    Label countAuthors;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addIcon();
        addMenu();
//        setUserName();
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
        TreeItem<String> rootGenres=new TreeItem<>("Thể loại");
        TreeItem<String> itemManagerGenres=new TreeItem<>("Quản lý thể loại");
        TreeItem<String> rootAuthors=new TreeItem<>("Tác giả");
        TreeItem<String> itemManageAuthors=new TreeItem<>("Quản lý tác giả");
        TreeItem<String> rootMembers=new TreeItem<>("Đọc giả");
        TreeItem<String> itemAddMember=new TreeItem<>("Thêm đọc giả");
        TreeItem<String> itemDeleteMember=new TreeItem<>("Xóa đọc giả");
        TreeItem<String> itemMembersList=new TreeItem<>("Danh sách đọc giả");
        TreeItem<String> rootBooks=new TreeItem<>("Sách");
        TreeItem<String> itemAddBook=new TreeItem<>("Thêm sách");
        TreeItem<String> itemDeleteBook=new TreeItem<>("Xóa sách");
        TreeItem<String> itemBooksList=new TreeItem<>("Danh sách sách");
        TreeItem<String> rootCirculation=new TreeItem<>("Quản lý mượn/trả sách");
        TreeItem<String> itemIssueBook=new TreeItem<>("Cho mượn sách");
        TreeItem<String> itemReturnBook=new TreeItem<>("Hoàn trả sách");

        //root tự mở
        rootGenres.setExpanded(true);
        rootAuthors.setExpanded(true);
        rootMembers.setExpanded(true);
        rootBooks.setExpanded(true);
        rootCirculation.setExpanded(true);
        root.getChildren().addAll(rootGenres,rootAuthors,rootMembers,rootBooks,rootCirculation);
        rootGenres.getChildren().add(itemManagerGenres);
        rootAuthors.getChildren().add(itemManageAuthors);
        rootMembers.getChildren().addAll(itemAddMember,itemDeleteMember,itemMembersList);
        rootBooks.getChildren().addAll(itemAddBook,itemDeleteBook,itemBooksList);
        rootCirculation.getChildren().addAll(itemIssueBook,itemReturnBook);
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
    public void handleMouseClicked(MouseEvent event) throws IOException {
        Node node = event.getPickResult().getIntersectedNode();
        String name;
        Parent root;
        // Accept clicks only on node cells, and not on empty spaces of the TreeView
        if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
            name = (String) ((TreeItem)treeMenu.getSelectionModel().getSelectedItem()).getValue();
            switch (name){
                case "Quản lý thể loại":
                    openGenreManager(event);
                    break;
                case "Quản lý tác giả":
                    openAuthorManager(event);
                    break;
                case "Thêm đọc giả":
                    openAddUser(event);
                    break;
                case "Sửa thông tin đọc giả":
                    openEditUser(event);
                    break;
                case "Xóa đọc giả":
                    openDeleteUser(event);
                    break;
                case "Danh sách đọc giả":
                    openManageUser(event);
                    break;
                case"Thêm sách":
                    openAddBook(event);
                    break;
                case "Sửa thông tin sách":
                    openEditBook(event);
                    break;
                case "Xóa sách":
                    openDeleteBook(event);
                    break;
                case "Danh sách sách":
                    openManageBook(event);
                    break;
                case "Cho mượn sách":
                    openIssueBook(event);
                    break;
                case "Hoàn trả sách":
                    openReturnBook(event);
                    break;
            }
        }

    }
    public void openGenreManager(MouseEvent e) throws IOException {
        Stage newstage = new Stage();
        GenreMain sreen = new GenreMain();

        //ngăn tương tác với dashboard
        Stage oldStage=(Stage) ((Node) e.getSource()).getScene().getWindow();
        newstage.initModality(Modality.WINDOW_MODAL);
        newstage.initOwner(oldStage);
        //chạy newStage
        sreen.start(newstage);
    }
    public void openAuthorManager(MouseEvent e) throws IOException {
        Stage newstage = new Stage();
        AuthorMain sreen = new AuthorMain();
        //ngăn tương tác với dashboard
        Stage oldStage=(Stage) ((Node) e.getSource()).getScene().getWindow();
        newstage.initModality(Modality.WINDOW_MODAL);
        newstage.initOwner(oldStage);
        //chạy newStage
        sreen.start(newstage);
    }
    public void openAddUser(MouseEvent e) throws IOException {
        Stage newstage = new Stage();
        addMemberMain sreen = new addMemberMain();
        //ngăn tương tác với dashboard
        Stage oldStage=(Stage) ((Node) e.getSource()).getScene().getWindow();
        newstage.initModality(Modality.WINDOW_MODAL);
        newstage.initOwner(oldStage);
        //chạy newStage
        sreen.start(newstage);
    }
    public void openEditUser(MouseEvent e) throws IOException{
        System.out.println("Sửa thông tin đọc giả");
    }
    public void openDeleteUser(MouseEvent e) throws IOException {
        Stage newstage = new Stage();
        deleteMemberMain sreen = new deleteMemberMain();
        //ngăn tương tác với dashboard
        Stage oldStage=(Stage) ((Node) e.getSource()).getScene().getWindow();
        newstage.initModality(Modality.WINDOW_MODAL);
        newstage.initOwner(oldStage);
        //chạy newStage
        sreen.start(newstage);
    }
    public void openManageUser(MouseEvent e) throws IOException {
        Stage newstage = new Stage();
        MemberListMain sreen = new MemberListMain();
        //ngăn tương tác với dashboard
        Stage oldStage=(Stage) ((Node) e.getSource()).getScene().getWindow();
        newstage.initModality(Modality.WINDOW_MODAL);
        newstage.initOwner(oldStage);
        //chạy newStage
        sreen.start(newstage);
    }
    public void openAddBook(MouseEvent e) throws IOException {
        Stage newstage = new Stage();
        addBookMain sreen = new addBookMain();
        //ngăn tương tác với dashboard
        Stage oldStage=(Stage) ((Node) e.getSource()).getScene().getWindow();
        newstage.initModality(Modality.WINDOW_MODAL);
        newstage.initOwner(oldStage);
        //chạy newStage
        sreen.start(newstage);
    }
    public void openEditBook(MouseEvent e) throws IOException{
        System.out.println("Sửa thông tin sách");
    }
    public void openDeleteBook(MouseEvent e) throws IOException {

        Stage newstage = new Stage();
        deleteBookMain sreen = new deleteBookMain();
        //ngăn tương tác với dashboard
        Stage oldStage=(Stage) ((Node) e.getSource()).getScene().getWindow();
        newstage.initModality(Modality.WINDOW_MODAL);
        newstage.initOwner(oldStage);
        //chạy newStage
        sreen.start(newstage);
    }
    public void openManageBook(MouseEvent e) throws IOException {
        Stage newstage = new Stage();
        booksListMain sreen = new booksListMain();
        //ngăn tương tác với dashboard
        Stage oldStage=(Stage) ((Node) e.getSource()).getScene().getWindow();
        newstage.initModality(Modality.WINDOW_MODAL);
        newstage.initOwner(oldStage);
        //chạy newStage
        sreen.start(newstage);
    }
    public void openIssueBook(MouseEvent e) throws IOException {
        Stage newstage = new Stage();
        bookIssueMain sreen = new bookIssueMain();
        //ngăn tương tác với dashboard
        Stage oldStage=(Stage) ((Node) e.getSource()).getScene().getWindow();
        newstage.initModality(Modality.WINDOW_MODAL);
        newstage.initOwner(oldStage);
        //chạy newStage
        sreen.start(newstage);
    }
    public void openReturnBook(MouseEvent e) throws IOException {
        Stage newstage = new Stage();
        returnBookMain returnBook = new returnBookMain();
        //ngăn tương tác với dashboard
        Stage oldStage=(Stage) ((Node) e.getSource()).getScene().getWindow();
        newstage.initModality(Modality.WINDOW_MODAL);
        newstage.initOwner(oldStage);
        //chạy newStage
        returnBook.start(newstage);
    }
    static NhanVienDTO user;
    public void setUserName(NhanVienDTO nhanVienDTO){
        //tên userNameLabel
        user=nhanVienDTO;
        userNameLabel.setText(user.getHo()+" "+user.getTen());
//        System.out.println(mem.getTen());
    }
    public void statistical() throws Exception {
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
    public void addImageNewBook() throws Exception {
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

