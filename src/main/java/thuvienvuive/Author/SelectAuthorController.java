package thuvienvuive.Author;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import thuvienvuive.Author.*;
import thuvienvuive.Book.AddBookController;
import thuvienvuive.Database.ConnectDB;


public class SelectAuthorController implements Initializable {
    @FXML
    ImageView authorIcon;
    @FXML
    
	private Button exitButton;
    @FXML
    Button authorButton;

	@FXML
	TableView<Author> authorTable;
	@FXML
	TableColumn<Author, String> idColumn;
	@FXML
	TableColumn<Author, String> fNameColumn;
	@FXML
	TableColumn<Author, String> lNameColumn;
	@FXML
	TableColumn<Author, String> aboutColumn;
	@FXML
	TableColumn<Author, String> exColumn;

	Stage stage;
	private ObservableList<Author> authorList = FXCollections.observableArrayList();
    @Override
    
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setIcon();
        loadData();
      
    
    }
    public  void setIcon(){
        File iconFile = new File("src/image/author.png");
        Image iconAuthor=new Image(iconFile.toURI().toString());
        authorIcon.setImage(iconAuthor);
        Image minimizeImg;
        try {
            minimizeImg = new Image(new FileInputStream("src/image/exit.png"));
           ImageView img= new ImageView(minimizeImg);
           img.setFitHeight(43);
           img.setFitWidth(60);
            exitButton.setGraphic(img);
        } catch (FileNotFoundException ex) {
           ex.printStackTrace();
        }
    }
    public void exit(ActionEvent event) {
    	stage = (Stage)((Button)event.getSource()).getScene().getWindow();
	
       FXMLLoader loader = new FXMLLoader(AddBookController.class.getResource("AddNewBook.fxml"));
              Scene scene = null;
		try {
			scene = new Scene(loader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
       
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

	}
    public void loadData()
    {
    	ConnectDB conn = new ConnectDB();
		ResultSet m;
		try {
		m=conn.excuteQuery("Select * from TacGia");
	
	     if (m != null) {
             while (m.next()) {
               authorList.add(
            		   new Author(m.getString("IDTacGia"), m.getString("Ho"), m.getString("Ten"),m.getString("ThongTin"), m.getString("GhiChu"))
            		   
            		   );
             }
         }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(authorList.size());
		idColumn.setCellValueFactory(new PropertyValueFactory<Author,String>("ID"));
		fNameColumn.setCellValueFactory(new PropertyValueFactory<Author,String>("fistName"));
		lNameColumn.setCellValueFactory(new PropertyValueFactory<Author,String>("lastName"));
		exColumn.setCellValueFactory(new PropertyValueFactory<Author,String>("exString"));
		aboutColumn.setCellValueFactory(new PropertyValueFactory<Author,String>("aboutString"));
		authorTable.setItems(authorList);
    }
    public  void onEdit(MouseEvent event) {
        // check the table's selected item and get selected item
        if (authorTable.getSelectionModel().getSelectedItem() != null) {
            Author select = authorTable.getSelectionModel().getSelectedItem();
            String nameString = select.getFistName() + " " + select.getLastName();
         //   AddBookController.authorName=nameString;
        
          
           
        }
    }
    
    
    public void chooseAuthor(ActionEvent e) throws IOException {
    
   	 if (authorTable.getSelectionModel().getSelectedItem() != null) {
         Author select = authorTable.getSelectionModel().getSelectedItem();
         String nameString = select.getID();
         AddBookController.authorName=nameString;
	 }else {
		AddBookController.authorName="";
	}
       	 Stage stage = (Stage)((Button)e.getSource()).getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(AddBookController.class.getResource("AddNewBook.fxml"));
                   Scene scene = new Scene(loader.load());        
          stage.setScene(scene);
          stage.centerOnScreen();
          stage.show();
           
            
         
    }
    public void nameAuthor() {
    	
    	   Author selected = authorTable.getSelectionModel().getSelectedItem();
    	   System.out.println(selected.getFistName());
	}
   
    
}
