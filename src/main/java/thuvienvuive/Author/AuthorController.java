package thuvienvuive.Author;

import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AuthorController implements Initializable {
    @FXML
    ImageView bookIcon;

    //Table view
    @FXML
    TableView<TacGiaDTO> tableView;
    @FXML
    TableColumn<TacGiaDTO,String> idColumn;
    @FXML
    TableColumn<TacGiaDTO,String> hoColumn;
    @FXML
    TableColumn<TacGiaDTO,String> tenColoumn;
    @FXML
    TableColumn<TacGiaDTO,String> thongtinColumn;
    @FXML
    TableColumn<TacGiaDTO,String> ghichuColumn;
    //field
    @FXML
    TextField idField;
    @FXML
    TextField hoField;
    @FXML
    TextField tenField;
    @FXML
    TextField thongtinField;
    @FXML
    TextArea ghichuField;

    ObservableList <TacGiaDTO> authorList= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //icon
        setIcon();
        try {
            authorList();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //icon
    public void setIcon(){
        File iconFile = new File("src/image/book_pencil.png");
        Image iconLogin=new Image(iconFile.toURI().toString());
        bookIcon.setImage(iconLogin);
    }

    //xuất ds tacgia
    public void authorList() throws Exception {
        TacGiaBUS bus=new TacGiaBUS();
        authorList=bus.authorsList();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("IDTacgia"));
        hoColumn.setCellValueFactory(new PropertyValueFactory<>("Ho"));
        tenColoumn.setCellValueFactory(new PropertyValueFactory<>("Ten"));
        thongtinColumn.setCellValueFactory(new PropertyValueFactory<>("ThongTin"));
        ghichuColumn.setCellValueFactory(new PropertyValueFactory<>("GhiChu"));
        tableView.setItems(authorList);
    }
    @FXML
    private void handleClickTableView(MouseEvent click) {
        TacGiaDTO select = tableView.getSelectionModel().getSelectedItem();
        idField.setText(select.getIDTacgia());
        hoField.setText(select.getHo());
        tenField.setText(select.getTen());
        thongtinField.setText(select.getThongTin());
        ghichuField.setText(select.getGhiChu());

    }
    //thêm
    public void addAuthor(ActionEvent e){
        TacGiaDTO newAuthor=new TacGiaDTO();
        newAuthor.setIDTacgia(idField.getText());
        newAuthor.setHo(hoField.getText());
        newAuthor.setTen(tenField.getText());
        newAuthor.setThongTin(thongtinField.getText());
        newAuthor.setGhiChu(ghichuField.getText());
        TacGiaBUS bus=new TacGiaBUS();
        try {
            if(idField.getText().equals("")){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("Mã tác giả không được để trống");
                alert.show();
            }
            else{
                int checkid=bus.checkId(idField.getText());
                if(checkid==0){
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("Thêm thất bại: Mã tác giả đã trùng");
                    alert.show();
                }
                else if(checkid==1){
                    int resultAdd=bus.addAuthor(newAuthor);
                    if(resultAdd>0){
                        authorList.add(newAuthor);
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setContentText("Thêm thành công");
                        alert.show();
                    }

                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }



    }
    //sửa
    public void changeAuthor(ActionEvent e){
        TacGiaDTO select = tableView.getSelectionModel().getSelectedItem();
        if(select==null){
            Alert confirmAlert=new Alert(AlertType.WARNING);
            confirmAlert.setContentText("Hãy chọn tác giả muốn sửa");
            confirmAlert.show();
        }
        else {
            TacGiaDTO newAuthor=new TacGiaDTO();
            newAuthor.setIDTacgia(idField.getText());
            newAuthor.setHo(hoField.getText());
            newAuthor.setTen(tenField.getText());
            newAuthor.setThongTin(thongtinField.getText());
            newAuthor.setGhiChu(ghichuField.getText());
            TacGiaBUS bus=new TacGiaBUS();
            try {
                if(idField.getText().equals("")){
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("Mã tác giả không được để trống");
                    alert.show();
                }
                else{
                    if(idField.getText().equals(select.getIDTacgia())==false){
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setContentText("Sửa thất bại: Không được thay đổi mã tác giả");
                        alert.show();
                    }
                    else{
                        int kq=bus.changeAuthor(newAuthor);
                        if(kq>0){
                            authorList.remove(select);
                            authorList.add(newAuthor);
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setContentText("Sửa thành công");
                            alert.show();
                        }
                    }
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }
    public void deleteAuthor(ActionEvent e){
        TacGiaDTO select = tableView.getSelectionModel().getSelectedItem();
        if(select==null){
            Alert confirmAlert=new Alert(AlertType.WARNING);
            confirmAlert.setContentText("Hãy chọn tác giả muốn xóa");
            confirmAlert.show();
        }
        else{
            Alert warningAlert=new Alert(AlertType.CONFIRMATION);
            warningAlert.setContentText("Bạn có muốn xóa thông tin về tác giả này ra khỏi hệ thống không");
            ButtonType btnYes=new ButtonType("YES", ButtonBar.ButtonData.YES);
            ButtonType btnNo=new ButtonType("NO", ButtonBar.ButtonData.NO);
            warningAlert.getButtonTypes().addAll(btnYes,btnNo);
            warningAlert.getButtonTypes().removeAll(ButtonType.OK,ButtonType.CANCEL);
            Optional <ButtonType> result=warningAlert.showAndWait();
            if(result.get()==btnNo){
                warningAlert.close();
            }
            else{
                TacGiaBUS bus=new TacGiaBUS();
                try {
                    int kq=bus.deleteAuthor(idField.getText());
                    if(kq>0){
                        authorList.remove(select);
                        bus.listAuthors.remove(select);/***/
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setHeaderText("Xóa thành công");
                        alert.show();
                    }
                    else{
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setHeaderText("Xóa thất bại");
                        alert.show();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

    }
}

