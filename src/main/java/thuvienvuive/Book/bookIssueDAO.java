package thuvienvuive.Book;

import javafx.collections.ObservableList;
import thuvienvuive.Database.Connection;
import thuvienvuive.Member.MemberDTO;
import thuvienvuive.Member.MemberListDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class bookIssueDAO {
    Connection db = new Connection("localhost", "sa", "sa", "thuvienvuive");

    public ObservableList<Book> readBookListDAO() throws Exception{
        DeleteBookDAO bookList = new DeleteBookDAO();
        return bookList.readListDAO();
    }

    public ObservableList<bookIssueDTO> readIssueBookListDAO() throws Exception{
        returnBookDAO issueBookList = new returnBookDAO();
        return issueBookList.readIssueDAO();
    }
    public Book findBookByIDSach(String id) throws Exception{
        Book book=new Book();
        String query="";
        try{
            ResultSet resultSet = db.excutedQuery("select * from Sach where IDSach ='"+id+"'");
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
    public ObservableList<MemberDTO> readMemberListDAO() throws Exception{
        MemberListDAO memberList = new MemberListDAO();
        return memberList.readListDAO();
    }

    public int update(bookIssueDTO book, int soLuong) throws Exception{
        int res = 0;
        String query = "update Sach set SoLuong='" + (soLuong-1) + "' where IDSach='" + book.getIDSach() + "'";
        try{
            res = db.excuteUpdate(query);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return res;
    }

    public int insertIssue(bookIssueDTO issue) throws Exception{
        int res = 0;
        String query = "insert into PhieuMuon (IDPhieuMuon, IDMember, IDNhanVien, NgayLap, NgayTra, GhiChu, TrangThai, IDSach) values ('"
                + issue.getIDPhieuMuon() + "', '" + issue.getIDThanhVien() + "', '" + issue.getIDNhanVien() + "', '" + issue.getNgayMuon() +
                "', '" + issue.getNgayTra() + "', N'" + issue.getGhiChu() + "', N'" + issue.getTrangThai() + "', '" + issue.getIDSach() + "')";
        try{
            res = db.excuteUpdate(query);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return res;
    }
}
