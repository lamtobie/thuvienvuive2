package thuvienvuive.Book;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import thuvienvuive.Book.Book;
import thuvienvuive.Database.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteBookDAO {
    Connection db = new Connection("localhost", "sa", "sa", "thuvienvuive");

    public ObservableList<Book> readListDAO() throws Exception{
        ObservableList<Book> bookList = FXCollections.observableArrayList();
        String query = "select * from Sach";
        try{
            ResultSet resultSet = db.excutedQuery(query);
            if (resultSet != null){
                while(resultSet.next()){
                    Book book = new Book(
                            resultSet.getString("IDSach"),
                            resultSet.getString("TenSach"),
                            resultSet.getString("IDTacGia"),
                            resultSet.getInt("SoLuong"),
                            resultSet.getDate("NgayXuatBan").toLocalDate(),
                            resultSet.getDate("NgayNhanSach").toLocalDate(),
                            resultSet.getInt("SoTrang"),
                            resultSet.getString("GhiChu"),
                            resultSet.getString("IDTheLoai"),
                            resultSet.getString("HinhAnh"),
                            resultSet.getFloat("price")
                    );
                    bookList.add(book);
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return bookList;
    }

    public int deleteBook(String ID) throws Exception{
        String query = "delete from Sach where IDSach='" + ID + "'";
        int res = 0;
        try{
            res = db.excuteUpdate(query);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return res;
    }
}
