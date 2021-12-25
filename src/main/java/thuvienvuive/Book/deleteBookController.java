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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class deleteBookController implements Initializable {
    Stage primaryStage;

    @FXML
    private Spinner<String> bookIDSpinner;

    @FXML
    private Button closeButton;

    @FXML
    private AnchorPane wrap;

    @FXML
    ImageView titleIcon;

    @FXML
    ImageView closeIcon;

    @FXML
    private TextField bookIDText;

    @FXML
    private Button cancelButton;

    @FXML
    private Button deleteButton;

    DeleteBookDAO ListDAO = new DeleteBookDAO();
    static ObservableList<Book> List;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setDefault();
        } catch (Exception e) {
            e.printStackTrace();
        }
        handler();
    }

    public ObservableList<Book> readList() throws Exception{
        List = ListDAO.readListDAO();
        return List;
    }

    private ObservableList<String> getBookIDList(ObservableList<Book> List){
        ObservableList<String> IDBook = FXCollections.observableArrayList();
        for (Book book : List){
            IDBook.add(book.getID());
        }
        return IDBook;
    }

    private void close(){
        primaryStage = (Stage) wrap.getScene().getWindow();
        primaryStage.close();
    }

    public void setDefault() throws Exception {
        setIcon();
        setSpinnerBookIDList();
    }

    private void setSpinnerBookIDList() throws Exception{
        SpinnerValueFactory<String> values = new SpinnerValueFactory.ListSpinnerValueFactory<String>(getBookIDList(readList()));
        System.out.println(values.valueProperty().toString());
        bookIDSpinner.setValueFactory(values);
        bookIDSpinner.getValueFactory().wrapAroundProperty().set(true);
        bookIDText.setText(selectBook());
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

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String ID = bookIDText.getText();
                try {
                    deleteBook(getBookIDList(readList()), ID);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        bookIDSpinner.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                bookIDText.setText(selectBook());
            }
        });
    }

    public String selectBook() {
        return bookIDSpinner.getValue();
    }

    private String normalizeString(String string){
        return string.trim().toLowerCase();
    }

    public void deleteBook(ObservableList<String> IDList, String ID) throws Exception{
        int count = 0;
        for (String id : IDList){
            if(id.equalsIgnoreCase(ID)){
                count++;
                if(ListDAO.deleteBook(ID) > 0){
                    alert("Thông báo", "Xoá sách thành công!");
                    readList();
                    setSpinnerBookIDList();
                }
                else{
                    errorAlert("Lỗi", "Có lỗi xảy ra trong quá trình xoá sách!");
                }
            }
        }
        if(count == 0){
            alert("Thông báo", "Không tồn tại sách có ID: " + ID);
        }
    }

    public  void setIcon(){
        File iconFile = new File("src/image/delete_book_icon.png.png");
        Image iconTitle=new Image(iconFile.toURI().toString());
        titleIcon.setImage(iconTitle);
        File iconButton = new File("src/image/exit_icon.png");
        Image ButtonIcon=new Image(iconButton.toURI().toString());
        closeIcon.setImage(ButtonIcon);
    }
}