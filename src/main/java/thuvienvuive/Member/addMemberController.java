package thuvienvuive.Member;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import thuvienvuive.Book.Book;
import thuvienvuive.Database.ConnectDB;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class addMemberController implements Initializable {
    @FXML
    ImageView memberIcon;
    @FXML
    Button exitButton;
    @FXML
    TextField fName;
    @FXML
    TextField lName;
    @FXML
    TextField phoneTxt;
    @FXML
    TextField mailTxt;
    @FXML
    ComboBox<String> genderCB;
    @FXML
    Button fileChoice;
    @FXML
    Button addMemButton;
    @FXML
    Label linkImage;
    Stage stage;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setIcon();
        setCombobox();
        fileChoose();
     	fName.textProperty().addListener((observable, oldValue, newValue) -> {
     		if(newValue != null) {
     			Pattern pattern = Pattern.compile("[^A-Za-z ]");
     			  Matcher matcher = pattern.matcher(newValue);
     	  if (matcher.find()) {
     		    
            	Alert alert = new Alert(AlertType.WARNING, "Vui lòng nhập chữ", ButtonType.OK);
    	    	alert.setTitle("Cảnh báo nhập tên thành viên");
    	    	fName.setText("");
    	    	    	    	alert.showAndWait();
    			return;
            }
     		}
        });
    	lName.textProperty().addListener((observable, oldValue, newValue) -> {
     		if(newValue != null) {
     			Pattern pattern = Pattern.compile("[^a-zA-Z ]");
   			  Matcher matcher = pattern.matcher(newValue);
   	  if (matcher.find()) {
   		    
            	Alert alert = new Alert(AlertType.WARNING, "Vui lòng nhập chữ", ButtonType.OK);
    	    	alert.setTitle("Cảnh báo nhập họ và tên đệm thành viên");
    	    	lName.setText("");
    	    //lName.setFocusTraversable(true);
    	    	alert.showAndWait();
    			return;
            }
     		}
        });
      	phoneTxt.textProperty().addListener((observable, oldValue, newValue) -> {
     		if(newValue != null) {
     			
   	  if (!newValue.matches(("\\d{0,10}"))) {
   		    
            	Alert alert = new Alert(AlertType.WARNING, "Vui lòng nhập đúng 10 số", ButtonType.OK);
    	    	alert.setTitle("Cảnh báo nhập số điện thoại");
    	    	phoneTxt.setText("");
    	    //lName.setFocusTraversable(true);
    	    	alert.showAndWait();
    			return;
            }
     		}
        });
      	
      	
    }
   
    public  void setIcon(){
        File iconFile = new File("src/image/member.png");
        Image iconTeam=new Image(iconFile.toURI().toString());
        memberIcon.setImage(iconTeam);
      
        Image image = null;
			try {
				image = new Image(new FileInputStream("src/image/exit.png"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ImageView imageView = new ImageView(image);

			imageView.setFitWidth(48);

			imageView.setFitHeight(43);
                 imageView.setLayoutX(exitButton.getLayoutX());
                 imageView.setLayoutY(exitButton.getLayoutY());
			exitButton.setGraphic(imageView);
    }
 public void exit(ActionEvent event) {
		
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Bạn có muốn thoát " + " ?", ButtonType.YES, ButtonType.NO);
    	alert.setTitle("Thoát");
    	alert.showAndWait();

    	if (alert.getResult() == ButtonType.YES) {
    	    //do stuff
    	
	  stage = (Stage)((Button)event.getSource()).getScene().getWindow();                     
        stage.close();
    	}
	}
 public void setCombobox()
 {
	 ObservableList<String> genderList = FXCollections.observableArrayList("Nam","Nữ");
	 genderCB.setItems(genderList);
 }
 public void fileChoose() {
 	final FileChooser fileChooser = new FileChooser();
     configuringFileChooser(fileChooser);
          Button button = new Button("Open DirectoryChooser and select a directory");
      fileChoice.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
             File file=fileChooser.showOpenDialog(stage);
              
              if (file != null) {
                  // pickUpPathField it's your TextField fx:id
                  linkImage.setText(file.getPath());
               
                                } 
          }
      });
	
	}
 private void configuringFileChooser(FileChooser fileChooser) {
 	 
     // Set tiêu đề cho FileChooser
     fileChooser.setTitle("Select Pictures");


     // Sét thư mục bắt đầu nhìn thấy khi mở FileChooser
     fileChooser.setInitialDirectory(new File("D:/Ảnh"));


     // Thêm các bộ lọc file vào
     fileChooser.getExtensionFilters().addAll(//
         //
             new FileChooser.ExtensionFilter("JPG", "*.jpg"), //
             new FileChooser.ExtensionFilter("PNG", "*.png"));
 }
 public void setAddMem(ActionEvent event)
 {
	 ObservableList<String> listAdd = FXCollections.observableArrayList();
	 ObservableList<String> listTitle = FXCollections.observableArrayList();
	 listAdd.addAll(fName.getText(),lName.getText(),phoneTxt.getText(),mailTxt.getText(),genderCB.getValue(),linkImage.getText());
	 listTitle.addAll("tên","họ và tên đệm","số điện thoại","email","giới tính","ảnh đại diện");
		for(int i=0 ; i<listAdd.size();i++)
		{
			if(listAdd.get(i) == null)
			{
				Alert alert = new Alert(AlertType.WARNING, "Vui lòng nhập " + listTitle.get(i), ButtonType.OK);
		    	alert.setTitle("Cảnh báo nhập " + listTitle.get(i));
		    	alert.showAndWait();
				return;
			}
		}
		Pattern pattern = Pattern.compile("^[\\w.+\\-]+@gmail\\.com$");
        Matcher matcher = pattern.matcher(mailTxt.getText());
        if (!matcher.find()) {
        	Alert alert = new Alert(AlertType.WARNING, "Vui lòng nhập đúng định dạng mail ", ButtonType.OK);
	    	alert.setTitle("Cảnh báo nhập mail" );
	    	alert.showAndWait();
			return;
        }
        if (phoneTxt.getLength() != 10) {
        	Alert alert = new Alert(AlertType.WARNING, "Vui lòng nhập đúng số điện thoại (10 số) ", ButtonType.OK);
	    	alert.setTitle("Cảnh báo nhập số điện thoại" );
	    	alert.showAndWait();
			return;
        }
        if(linkImage.getText().equalsIgnoreCase("Đường dẫn ảnh"))
        		{
        	Alert alert = new Alert(AlertType.WARNING, "Vui lòng chọn ảnh đại diện!!", ButtonType.OK);
	    	alert.setTitle("Cảnh báo chọn ảnh đại diện" );
	    	alert.showAndWait();
			return;
        		}
        MemberDTO memberDTO = new MemberDTO("IDMember" + layMaMember(),fName.getText(),lName.getText(),phoneTxt.getText(),mailTxt.getText(),genderCB.getValue(),linkImage.getText());
        if(addMem(memberDTO) > 0)
        {
    		Alert alert = new Alert(AlertType.INFORMATION, "Thêm thành viên thành công !!!", ButtonType.OK);
	    	alert.setTitle("Thêm thành viên thành công!!!");
	    	alert.showAndWait();
			return;
        }else {
    		Alert alert = new Alert(AlertType.INFORMATION, "Thêm thành viên thất bại !!!", ButtonType.OK);
	    	alert.setTitle("Thêm thành viên thất bại !!!");
	    	alert.showAndWait();
			return;
		}
        }
 public int addMem(MemberDTO mem) { //cần ghi lại khi qua class khác
 	ConnectDB connection = new ConnectDB();
 	int check = 0;
     try {
         String qry = "INSERT INTO Member values (";
         qry = qry + "'" + mem.getID() + "'";
         qry = qry + "," + "'" + mem.getTen() + "'";
         qry = qry + "," + "'" + mem.getHo()+ "'";
         qry = qry + "," + "'" + mem.getEmail() + "'";
         qry = qry + "," + "'" + mem.getSoDienThoai() + "'";
         qry = qry + "," + "'" + mem.getGioiTinh() + "'";
         qry = qry + "," + "'" + mem.getHinhAnh() + "'";
         qry = qry + ")";
         connection.getStatement();
        check= connection.ExecuteUpdate(qry);
         System.out.println(qry);
         connection.closeConnect();
     } catch (Exception ex) {
     }
     return check;
 }
 public int layMaMember() {
 	int ma=0;
 	ConnectDB conn = new ConnectDB();
 	 ResultSet id = null;
 	try {
			id=conn.excuteQuery("select * from Member");
          if(id!=null)
          {
         	 while (id.next()) {
                 
               		ma++;   
         	 }
          }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	return ma+1;
 	
	}
}
