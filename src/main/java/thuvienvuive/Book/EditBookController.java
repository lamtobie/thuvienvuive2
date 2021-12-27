package thuvienvuive.Book;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.converter.StringConverter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import thuvienvuive.Author.Author;
import thuvienvuive.Author.AuthorController;
import thuvienvuive.Author.SelectAuthorController;
import thuvienvuive.Database.ConnectDB;

public class EditBookController implements Initializable {
	public static String authorName = "";
	static ObservableList<String> cacheList = FXCollections.observableArrayList();
    static String co="";
	   @FXML
	    ImageView bookIcon;
	  @FXML
		private Button exitButton;
	   @FXML
	    TextField tenSach;
	    @FXML
	    TextField soTrang;
	    @FXML
	    TextField maSach;
	    @FXML
	      TextField tacGia;
	    @FXML
	    ComboBox<String> theLoai;
	    @FXML
	    Spinner<Integer> soLuong;
	    @FXML
	    TextField nxbText;
	    @FXML
	    TextField giaTien;
	    @FXML
	    DatePicker ngayNhap;
	    @FXML
	    Button clearButton;
	    @FXML
	    Button addButton;
	    @FXML
	    Button searchBook;
	    @FXML
	    Button cancelButton;
	    @FXML
	    TextArea motaSach;
	    @FXML
	    DatePicker ngayXB;
	    @FXML
	    Button editButton;
	    @FXML
	    
	    ImageView anhBia;
	    @FXML
	    Label linkImage;
	    @FXML
	    Button imageButton;
	  Stage stage;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		setIcon();
		cacheList.removeAll(cacheList);
		setSpinner();
		AddBookController.changeStatus("Edit");
		 this.tacGia.setText(AddBookController.authorName);
		 
	}
    public void exit(ActionEvent event) {
		
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Bạn có muốn thoát " + " ?", ButtonType.YES, ButtonType.NO);
    	alert.setTitle("Thoát");
    	alert.showAndWait();
    	AddBookController.changeStatus(null);

    	if (alert.getResult() == ButtonType.YES) {
    	    //do stuff
    	
		  stage = (Stage)((Button)event.getSource()).getScene().getWindow();                     
        stage.close();
    	}
	}
    public  void setIcon(){
        File iconFile = new File("src/image/copy_139314729.png");
        Image iconBook=new Image(iconFile.toURI().toString());
        bookIcon.setImage(iconBook);
        
        Image image = null;
			try {
				image = new Image(new FileInputStream("src/image/exit.png"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ImageView imageView = new ImageView(image);

			imageView.setFitWidth(60);

			imageView.setFitHeight(43);

			exitButton.setGraphic(imageView);

    }
    
    public void choiceAuthor(ActionEvent event) throws Exception {  
    	AddBookController.changeStatus("Edit");
    	if(ngayNhap.getValue()==null && ngayXB.getValue() != null) {
    		 AddBookController.setCo("ngaynhap"); 
    	    	cacheList.addAll(linkImage.getText(),motaSach.getText(),giaTien.getText(),nxbText.getText(),soLuong.getValue().toString(),theLoai.getValue(),soTrang.getText(),tenSach.getText(),ngayXB.getValue().toString());
    	}else if(ngayNhap.getValue()!=null && ngayXB.getValue() == null)
    	{
    		 AddBookController.setCo("ngayxuat"); 
    		cacheList.addAll(linkImage.getText(),motaSach.getText(),giaTien.getText(),nxbText.getText(),soLuong.getValue().toString(),theLoai.getValue(),soTrang.getText(),tenSach.getText(),ngayNhap.getValue().toString());

    	}else if(ngayNhap.getValue()!=null && ngayXB.getValue() != null)
    	{
    		cacheList.addAll(linkImage.getText(),motaSach.getText(),giaTien.getText(),nxbText.getText(),soLuong.getValue().toString(),theLoai.getValue(),soTrang.getText(),tenSach.getText(),ngayNhap.getValue().toString(),ngayXB.getValue().toString());

    	}
    	else {
    		cacheList.addAll(linkImage.getText(),motaSach.getText(),giaTien.getText(),nxbText.getText(),soLuong.getValue().toString(),theLoai.getValue(),soTrang.getText(),tenSach.getText());

    	}
    	stage =(Stage)((Node) event.getSource()).getScene().getWindow();
    	stage.close();

    	    	Stage stage = new Stage(); 
    	    		
    	    			//(Stage)((Node) event.getSource()).getScene().getWindow();
    	    	
    	        FXMLLoader loader = new FXMLLoader(AuthorController.class.getResource("SelectAuthorList.fxml"));
    	        Scene scene = new Scene(loader.load());    
    	        stage.initStyle(StageStyle.UNDECORATED);
    	        stage.setScene(scene);
    	        stage.centerOnScreen();
    	        stage.show();
    	        
    	    	
    	}
    
    public void setCache() {
    	if (cacheList.size()>1) {
    	
    	linkImage.setText(cacheList.get(0));
    	motaSach.setText(cacheList.get(1));
    	giaTien.setText(cacheList.get(2));
    	nxbText.setText(cacheList.get(3));
      	 SpinnerValueFactory<Integer> valueFactory = 
                 new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.parseInt(cacheList.get(4)));
    	           soLuong.setValueFactory(valueFactory);
    	theLoai.setValue(cacheList.get(5));
    	soTrang.setText(cacheList.get(6));
    	tenSach.setText(cacheList.get(7));
    	  File fileimg = new File(cacheList.get(0));
          Image image = new Image(fileimg.toURI().toString());  
          anhBia.setImage(image);
        
      	}
    	  if(cacheList.size() == 9) {
        	  if(co.equalsIgnoreCase("ngaynhap"))
        	  {
          convertString(null,cacheList.get(8));
        	  }else if (co.equalsIgnoreCase("ngayxuat")){
				convertString(cacheList.get(8), null);
			   }
             }
         if (cacheList.size() == 10) {
        	 convertString(cacheList.get(8),cacheList.get(9));
		       }
		}
    public void setSpinner() {
        SpinnerValueFactory<Integer> valueFactory = 
                     new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0);
        	           soLuong.setValueFactory(valueFactory);
             
    	}
    public void convertString(String daynhap,String dayxuat)
    {
    	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	if(daynhap==null) {
    	    	 LocalDate localxuat = LocalDate.parse(dayxuat, formatter);
    			ngayXB.setValue(localxuat);
    	}else if (dayxuat == null) {
    		 LocalDate localnhap = LocalDate.parse(daynhap, formatter);
		ngayNhap.setValue(localnhap);
    	}else if(daynhap != null && dayxuat != null)
    	{
    		 LocalDate localxuat = LocalDate.parse(dayxuat, formatter);
    		 LocalDate localnhap = LocalDate.parse(daynhap, formatter);
    		 ngayXB.setValue(localxuat);
    		 ngayNhap.setValue(localnhap);
    	}else {
    		 ngayXB.setValue(null);
    		 ngayNhap.setValue(null);
		}
    }
    
    public void setClear(ActionEvent event)
    {
    	linkImage.setText(null);
    	ngayNhap.setValue(null);
    	motaSach.setText(null);
    	giaTien.setText(null);
    	nxbText.setText(null);
      	setSpinner();
    	theLoai.setValue(null);
    	soTrang.setText(null);
    	tenSach.setText(null);
    	tacGia.setText(null);
    	anhBia.setImage(null);
    	ngayXB.setValue(null);
    }
    public void searchBoxButton( ActionEvent event) {
    	String ma=maSach.getText();
    	if(ma.isBlank())
    	{
    		Alert alert = new Alert(AlertType.INFORMATION, "Vui lòng nhập mã sách để tìm kiếm !!!", ButtonType.OK);
	    	alert.setTitle("Tìm kiếm sách thất bại!!!");
	    	alert.showAndWait();
			return;
    	}
    	 ObservableList<String> BookList = FXCollections.observableArrayList();
	    	ConnectDB conn = new ConnectDB();
			ResultSet m;
			try {
			m=conn.excuteQuery("Select * from Sach where IDSach='"+ma + "'");
		
		     if (m != null) {
	             while (m.next()) {
	               BookList.addAll(
	            		   m.getString("IDSach"), m.getString("TenSach"), m.getString("IDTacgia"),m.getString("SoLuong"),m.getString("NgayXuatBan"),m.getString("NgayNhanSach"),m.getString("SoTrang"),m.getString("GhiChu"),m.getString("IDTheLoai"),m.getString("HinhAnh"),m.getString("price")
	            		   
	            		   );
	             }
	         }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(BookList.size() == 0)
			{
				Alert alert = new Alert(AlertType.INFORMATION, "Mã sách không tồn tại !!!", ButtonType.OK);
		    	alert.setTitle("Tìm kiếm sách thất bại!!!");
		    	alert.showAndWait();
				return;
			}
		tenSach.setText(BookList.get(1));
		tacGia.setText(BookList.get(2));
		 SpinnerValueFactory<Integer> valueFactory = 
                 new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,Integer.parseInt(BookList.get(3)));
    	           soLuong.setValueFactory(valueFactory);
    	          
    	           String[] part1 = BookList.get(5).split(" ");
    	           String[] part2 = BookList.get(4).split(" ");
    	        convertString(part1[0],null);
    	           convertString(null,part2[0]);
    	           soTrang.setText(BookList.get(6));
    	           motaSach.setText(BookList.get(7));
    	           theLoai.setValue(BookList.get(8));
    	           File fileimg = new File(BookList.get(9));
    	           Image image = new Image(fileimg.toURI().toString());  
    	           anhBia.setImage(image);
    	           giaTien.setText(BookList.get(10));
	    }
	public void editBook(ActionEvent event) {
		 ObservableList<String> listAdd = FXCollections.observableArrayList();
      	 ObservableList<String> listTitle = FXCollections.observableArrayList();
 		
      	 listTitle.addAll("ảnh bìa sách","mô tả sách","giá tiền","nhà xuất bản",soLuong.getValue().toString(),"thể loại","số trang","tên sách","tác giả");

    		listAdd.addAll(linkImage.getText(),motaSach.getText(),giaTien.getText(),nxbText.getText(),soLuong.getValue().toString(),theLoai.getValue(),soTrang.getText(),tenSach.getText(),tacGia.getText());
    		for(int i=0 ; i<listAdd.size();i++)
    		{
    			if( listAdd.get(4).equalsIgnoreCase("0"))
    			{
    				Alert alert = new Alert(AlertType.WARNING, "Vui lòng nhập đúng số lượng sách cần nhập !!!", ButtonType.OK);
    		    	alert.setTitle("Cảnh báo nhập số lượng sách !!!");
    		    	alert.showAndWait();
    				return;
    			}
    			if( listAdd.get(5)==null)
    			{
    				Alert alert = new Alert(AlertType.WARNING, "Vui lòng chọn thể loại sách !!!", ButtonType.OK);
    		    	alert.setTitle("Cảnh báo chọn thể loại sách !!!");
    		    	alert.showAndWait();
    				return;
    			}
    			if( listAdd.get(0).isEmpty())
    			{
    				Alert alert = new Alert(AlertType.WARNING, "Vui lòng chọn ảnh bìa sách !!!", ButtonType.OK);
    		    	alert.setTitle("Cảnh báo chọn ảnh bìa sách !!!");
    		    	alert.showAndWait();
    				return;
    			}
    			if(listAdd.get(i).isEmpty())
    			{
    				Alert alert = new Alert(AlertType.WARNING, "Vui lòng nhập " + listTitle.get(i), ButtonType.OK);
    		    	alert.setTitle("Cảnh báo nhập " + listTitle.get(i));
    		    	alert.showAndWait();
    				return;
    			}
             
    		}
    		if(ngayNhap.getValue()==null)
    		{
    			Alert alert = new Alert(AlertType.WARNING, "Vui lòng chọn thời gian nhập sách!!!", ButtonType.OK);
		    	alert.setTitle("Cảnh báo nhập thời gian!!!");
		    	alert.showAndWait();
				return;
    		}
    		if(ngayXB.getValue()==null)
    		{
    			Alert alert = new Alert(AlertType.WARNING, "Vui lòng chọn thời gian xuất bản sách!!!", ButtonType.OK);
		    	alert.setTitle("Cảnh báo nhập thời gian!!!");
		    	alert.showAndWait();
				return;
    		}
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    		LocalDate localnhap = LocalDate.parse(ngayNhap.getValue().toString(), formatter);
    		LocalDate localxuat = LocalDate.parse(ngayXB.getValue().toString(), formatter);
    		String string=theLoai.getValue(); //tách chuỗi
    		String[] parts = string.split("-");
    	Book bookDTO = new Book(maSach.getText(),tenSach.getText(),tacGia.getText(),soLuong.getValue(),localxuat,localnhap,Integer.parseInt(soTrang.getText()),motaSach.getText(),parts[0], linkImage.getText(),Float.parseFloat(giaTien.getText()));
    	if(suaSach(bookDTO,maSach.getText()) >0)
    	{
    		Alert alert = new Alert(AlertType.INFORMATION, "Sửa thông tin sách thành công !!!", ButtonType.OK);
	    	alert.setTitle("Cập nhật sách thành công!!!");
	    	alert.showAndWait();
			return;
    	}else {
    		Alert alert = new Alert(AlertType.INFORMATION, "Sửa thông tin sách thất bại !!!", ButtonType.OK);
	    	alert.setTitle("Cập nhật sách thất bại!!!");
	    	alert.showAndWait();
			return;
		}
	}
	 public int suaSach(Book book,String maString) { //cần ghi lại khi qua class khác
	    	ConnectDB connection = new ConnectDB();
	    	String   qry = "Update Sach  SET TenSach =";
	        qry = qry + "," + "'" + book.getTen() + "', IDTacGia=";
	        qry = qry + "," + "'" + book.getIDTacGia() + "', SoLuong=";
	        qry = qry + "," + "'" + book.getSoLuong() + "',price=";
	        qry = qry + "," + "'" + book.getGiaTien() + "',NgayXuatBan=";
	        qry = qry + "," + "'" + book.getNgayXuatBan() + "',NgayNhanSach=";
	        qry = qry + "," + "'" + book.getNgayNhanSach() + "'SoTrang=";
	        qry = qry + "," + "'" + book.getSoTrang() + "'GhiChu=";
	        qry = qry + "," + "'" + book.getGhiChu() + "',IDTheLoai=";
	        qry = qry + "," + "'" + book.getIDTheLoai() + "',HinhAnh=";
	        qry = qry + "," + "'" + book.getHinhAnh() + "'";
	        qry = qry + "Where IDSach=" + maString;
	    	
	    	int check = 0;
	        try {
	                  connection.getStatement();
	           check= connection.ExecuteUpdate(qry);
	            System.out.println(qry);
	            connection.closeConnect();
	        } catch (Exception ex) {
	        }
	        return check;
	    }
	 
	    public void setCancel(ActionEvent event) {
	    	Alert alert = new Alert(AlertType.CONFIRMATION, "Hủy " + " ?", ButtonType.YES, ButtonType.NO);
	    	alert.setTitle("Hủy thêm sách");
	    	alert.showAndWait();

	    	if (alert.getResult() == ButtonType.YES) {
	    	    //do stuff
	    	
			  stage = (Stage)((Button)event.getSource()).getScene().getWindow();                     
	        stage.close();
	    	}
		}
}
