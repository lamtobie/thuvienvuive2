package thuvienvuive.Book;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import thuvienvuive.Database.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class returnBookDAO {
    Connection db = new Connection("localhost", "sa", "sa", "thuvienvuive");

    public ObservableList<bookIssueDTO> readIssueDAO() throws Exception{
        ObservableList<bookIssueDTO> issueBookList = FXCollections.observableArrayList();
        String query = "select * from PhieuMuon";
        try{
            ResultSet resultSet = db.excutedQuery(query);
            if(resultSet != null){
                while(resultSet.next()){
                    bookIssueDTO bookIssue = new bookIssueDTO(
                            resultSet.getString("IDPhieuMuon"),
                            resultSet.getString("IDMember"),
                            resultSet.getString("IDNhanVien"),
                            resultSet.getDate("NgayLap").toLocalDate(),
                            resultSet.getDate("NgayTra").toLocalDate(),
                            resultSet.getString("GhiChu"),
                            resultSet.getString("TrangThai"),
                            resultSet.getString("IDSach")
                    );
                    issueBookList.add(bookIssue);
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return issueBookList;
    }

    public int updateBook(bookIssueDTO bookIssue, int soLuong) throws Exception{
        int res = 0;
        String query = "update Sach set SoLuong='" + soLuong + "' where IDSach='" + bookIssue.getIDSach() + "'";
        try {
            res = db.excuteUpdate(query);
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return res;
    }

    public int updateStatus(bookIssueDTO bookIssue, String status) throws Exception{
        int res = 0;
        String query = "update PhieuMuon set TrangThai=N'" + status + "', NgayTra='" + bookIssue.getNgayTra() + "' where IDPhieuMuon='" + bookIssue.getIDPhieuMuon() + "'";
        try {
            res = db.excuteUpdate(query);
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return res;
    }
}
