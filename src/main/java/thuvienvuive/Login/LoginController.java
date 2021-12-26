
package thuvienvuive.Login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import thuvienvuive.Book.returnBookController;
import thuvienvuive.Dashboard.Admin.AdminController;
import thuvienvuive.Dashboard.User.UserController;
import thuvienvuive.Member.MemberDTO;
import thuvienvuive.User.NhanVienDTO;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    ImageView icon;
    @FXML
    TextField userField;
    @FXML
    PasswordField passField;
    public static NhanVienDTO nhanVien = new NhanVienDTO();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setIcon();

    }
    public void  setIcon(){
        File iconFile = new File("src/image/book_login.png");
        Image iconLogin=new Image(iconFile.toURI().toString());
        icon.setImage(iconLogin);
    }

    public void login(ActionEvent e) throws Exception {
        //0:lỗi;1:user;2:admin
        TaiKhoanDTO Acc=new TaiKhoanDTO();
        Acc.setTenTaiKhoan(userField.getText());
        Acc.setMatKhau(passField.getText());
        TaiKhoanBUS accountBUS=new TaiKhoanBUS();
        int kq=accountBUS.login(Acc);
        if(kq==1){
            nhanVien = accountBUS.accountInfo(Acc);
            Stage stage=(Stage) ((Node) e.getSource()).getScene().getWindow();
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(UserController.class.getResource("UserSample.fxml"));
            Parent root=loader.load();
            Scene scene=new Scene(root);

            UserController userCtrl=loader.getController();
            NhanVienDTO nhanVienDTO =accountBUS.accountInfo(Acc);
            userCtrl.setUserName(nhanVienDTO);

            stage.setScene(scene);
            stage.setTitle("Dashboard User");
            System.out.printf("user");
        }else if(kq==2){
            nhanVien = accountBUS.accountInfo(Acc);
            Stage stage=(Stage) ((Node) e.getSource()).getScene().getWindow();
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(AdminController.class.getResource("AdminSample.fxml"));
            Parent root=loader.load();
            Scene scene=new Scene(root);

            AdminController adminCtrl=loader.getController();
            NhanVienDTO nhanVienDTO =accountBUS.accountInfo(Acc);
            adminCtrl.setAdminName(nhanVienDTO);

            stage.setScene(scene);
            stage.setTitle("Dashboard Admin");
            System.out.printf("admin");
        }
        else{
            Alert errorAlert=new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Đăng nhập thất bại");
            errorAlert.setContentText("Tài khoản hoặc mật khẩu sai!");
            errorAlert.show();
        }
    }
}
