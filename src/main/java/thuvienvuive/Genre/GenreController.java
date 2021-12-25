package thuvienvuive.Genre;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import thuvienvuive.Author.TacGiaBUS;
import thuvienvuive.Author.TacGiaDTO;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class GenreController implements Initializable {
    @FXML
    ImageView imageIcon;
    @FXML
    ImageView closeIcon;

    @FXML
    TableView<GenreDTO> tableView;
    @FXML
    TableColumn<GenreDTO,String> idColumn;
    @FXML
    TableColumn<GenreDTO,String> nameColumn;
    @FXML
    TextField idField;
    @FXML
    TextField nameField;
    ObservableList<GenreDTO> genreList= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setIcon();
        try {
            setGenreList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  void setIcon(){
        File iconFile = new File("src/image/bookgenres.png");
        Image iconImage=new Image(iconFile.toURI().toString());
        imageIcon.setImage(iconImage);
        File closeFile = new File("src/image/close.png");
        Image iconClose =new Image(closeFile.toURI().toString());
        closeIcon.setImage(iconClose);
    }
    public void setGenreList() throws Exception{
        GenreBUS genreBUS=new GenreBUS();
        genreList=genreBUS.genreList();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("IDTheLoai"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Ten"));
        tableView.setItems(genreList);
    }
    @FXML
    private void handleClickTableView(MouseEvent click) {
        GenreDTO select = tableView.getSelectionModel().getSelectedItem();
        idField.setText(select.getIDTheLoai());
        nameField.setText(select.getTen());
    }

    public void addType(ActionEvent e) throws IOException {
        GenreDTO newGenre=new GenreDTO();
        newGenre.setIDTheLoai(idField.getText());
        newGenre.setTen(nameField.getText());
        GenreBUS bus=new GenreBUS();

        try {
            if(idField.getText().equals("")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Mã loại không được để trống");
                alert.show();
            }
            else{
                int checkid=bus.checkId(idField.getText());
                if(checkid==0){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Thêm thất bại: Mã loại sách đã trùng");
                    alert.show();
                }
                else if(checkid==1){
                    int resultAdd=bus.addGenre(newGenre);
                    if(resultAdd>0){
                        genreList.add(newGenre);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Thêm thành công");
                        alert.show();
                    }

                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void editType(ActionEvent e) throws IOException{
        GenreDTO select = tableView.getSelectionModel().getSelectedItem();
        if(select==null){
            Alert confirmAlert=new Alert(Alert.AlertType.WARNING);
            confirmAlert.setContentText("Hãy chọn loại sách muốn sửa");
            confirmAlert.show();
        }
        else {
            GenreDTO newGenre=new GenreDTO();
            newGenre.setIDTheLoai(idField.getText());
            newGenre.setTen(nameField.getText());

            GenreBUS bus=new GenreBUS();
            try {
                if(idField.getText().equals("")){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Mã loại sách không được để trống");
                    alert.show();
                }
                else{
                    if(idField.getText().equals(select.getIDTheLoai())==false){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Sửa thất bại: Không được thay đổi mã loại");
                        alert.show();
                    }
                    else{
                        int kq=bus.editGenre(newGenre);
                        if(kq>0){
                            genreList.remove(select);
                            genreList.add(newGenre);
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
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
    public void deleteType(ActionEvent e) throws IOException{
        GenreDTO select = tableView.getSelectionModel().getSelectedItem();
        if(select==null){
            Alert confirmAlert=new Alert(Alert.AlertType.WARNING);
            confirmAlert.setContentText("Hãy chọn loại sách muốn xóa");
            confirmAlert.show();
        }
        else{
            Alert warningAlert=new Alert(Alert.AlertType.CONFIRMATION);
            warningAlert.setContentText("Bạn có muốn xóa thông tin về loại sách này ra khỏi hệ thống không");
            ButtonType btnYes=new ButtonType("YES", ButtonBar.ButtonData.YES);
            ButtonType btnNo=new ButtonType("NO", ButtonBar.ButtonData.NO);
            warningAlert.getButtonTypes().addAll(btnYes,btnNo);
            warningAlert.getButtonTypes().removeAll(ButtonType.OK,ButtonType.CANCEL);
            Optional<ButtonType> result=warningAlert.showAndWait();
            if(result.get()==btnNo){
                warningAlert.close();
            }
            else{
                GenreBUS bus=new GenreBUS();
                try {
                    int kq=bus.deleteGenre(idField.getText());
                    if(kq>0){
                        genreList.remove(select);
                        bus.listGenre.remove(select);/***/
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("Xóa thành công");
                        alert.show();
                    }
                    else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
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
