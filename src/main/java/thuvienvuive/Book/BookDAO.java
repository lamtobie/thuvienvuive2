//book vs list book
package thuvienvuive.Book;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import thuvienvuive.Database.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class BookDAO {
    Connection db = new Connection("localhost", "sa", "sa", "thuvienvuive");

    //lấy list sách
    public ObservableList<Book> readListBooks() throws Exception{
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
    //tìm sách theo id
    public Book findBookByIDSach(String id) throws Exception{
        Book book=new Book();
        String query="";
        try{
            ResultSet resultSet = db.excutedQuery("select * from Sach where IDSach = '"+id+"'");
            if(resultSet!=null){
                System.out.println(resultSet.getString("IDSach"));
                book.setID(resultSet.getString("IDSach"));
                book.setTen(resultSet.getString("TenSach"));
                book.setIDTacGia(resultSet.getString("IDTacGia"));
                book.setSoLuong(resultSet.getInt("SoLuong"));
                book.setNgayXuatBan(resultSet.getDate("NgayXuatBan").toLocalDate());
                book.setNgayNhanSach( resultSet.getDate("NgayNhanSach").toLocalDate());
                book.setSoTrang(resultSet.getInt("SoTrang"));
                book.setGhiChu(resultSet.getString("GhiChu"));
                book.setIDTheLoai(resultSet.getString("IDTheLoai"));
                book.setHinhAnh(resultSet.getString("HinhAnh"));
                book.setGiaTien(resultSet.getFloat("price"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    //tìm sách theo tên
    public ObservableList<Book> findBookByTenSach(String name) throws Exception{
        ObservableList<Book> bookList = FXCollections.observableArrayList();
        String query = "select * from Sach where TenSach like %"+name+"% ";
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

    //tìm sách theo mã tác giả
    public ObservableList<Book> findBookByIDTacGia(String id) throws Exception{
        ObservableList<Book> bookList = FXCollections.observableArrayList();
        String query = "select * from Sach where IDTacGia="+id+"";
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

    //tìm sách có ngày nhận nằm trong khoàng
    public ObservableList<Book> findBookByNgayNhanSach(LocalDate from,LocalDate to) throws Exception{
        ObservableList<Book> bookList = FXCollections.observableArrayList();
        String query = "select * from Sach where NgayNhanSach from "+from+" to "+to+"";
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
    //lấy 5 quyển mới nhất theo ngày nhận sách
    public ObservableList<Book> findfiveNewBooks() throws Exception{
        ObservableList<Book> bookList = FXCollections.observableArrayList();
        String query = "select top(5) * from Sach order by NgayNhanSach desc";
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

    // lấy tổng số sách trong kho
    public int countBooks() throws Exception{
        int count=1;
        String query="select count(*) as total from Sach";
        try {
            ResultSet resultSet = db.excutedQuery(query);
            while(resultSet.next()){
                count=resultSet.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return count;
    }

    //thêm 1 quyển sách
    public int addNewBook(Book book) throws Exception{
        int res=0;
        String query="Insert into Sach values('"+book.getID()+"','"+book.getTen()+"','"+book.getIDTacGia()+
                "',"+book.getSoLuong()+",'"+book.getNgayXuatBan()+"','"+book.getNgayNhanSach()+"',"+book.getSoTrang()+
                ",'"+book.getGhiChu()+"','"+book.getIDTheLoai()+"','"+book.getHinhAnh()+"',"+book.getGiaTien()+")";
        try{
            res=db.excuteUpdate(query);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  res;
    }

    //sửa thông tin 1 quyển sách
    public int updateBook(Book book) throws Exception{
        int res=0;
        String query="update Sach set TenSach='"+book.getTen()+"', IDTacGia='"+book.getIDTacGia()+"', SoLuong="+book.getSoLuong()+
                ", NgayXuatBan='"+book.getNgayXuatBan()+"', NgayNhanSach='"+book.getNgayNhanSach()+"', SoTrang="+book.getSoTrang()+
                ", GhiChu='"+book.getGhiChu()+"', IDTheLoai='"+book.getIDTheLoai()+"', HinhAnh='"+book.getHinhAnh()+"', GiaTien="+book.getGiaTien()+" ";
        try{
            res=db.excuteUpdate(query);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return  res;
    }


}
