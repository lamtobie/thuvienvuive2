package thuvienvuive.Book;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import thuvienvuive.Database.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class BookListDAO {

    Connection conn = new Connection("localhost", "sa", "", "thuvienvuivee");

    public ObservableList<Book> docDB() throws Exception {
        ObservableList<Book> dsbook = FXCollections.observableArrayList();
        try{
            String qry = "SELECT * FROM Sach";
            ResultSet rs = conn.excutedQuery(qry);
            if(rs != null){
                while(rs.next()){
                    Book bookDTO = new Book();
                    bookDTO.setID(rs.getString("IDSach"));
                    bookDTO.setTen(rs.getString("TenSach"));
                    bookDTO.setIDTacGia(rs.getString("IDTacGia"));
                    bookDTO.setSoLuong(rs.getInt("SoLuong"));
                    bookDTO.setNgayXuatBan(rs.getDate("NgayXuatBan").toLocalDate());
                    bookDTO.setNgayNhanSach(rs.getDate("NgayNhanSach").toLocalDate());
                    bookDTO.setSoTrang(rs.getInt("SoTrang"));
                    bookDTO.setGhiChu(rs.getString("GhiChu"));
                    bookDTO.setIDTheLoai(rs.getString("IDTheLoai"));
                    bookDTO.setHinhAnh(rs.getString("HinhAnh"));
                    bookDTO.setGiaTien(rs.getFloat("price"));
                    dsbook.add(bookDTO);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return dsbook;
    }
}
