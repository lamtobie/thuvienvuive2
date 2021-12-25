package thuvienvuive.Book;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import thuvienvuive.Database.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class compensationSlipDAO {
    Connection db = new Connection("localhost", "sa", "sa", "thuvienvuive");

    public ObservableList<compensationSlipDTO> readListDAO () throws Exception {
        ObservableList<compensationSlipDTO> list = FXCollections.observableArrayList();
        String query = "select * from BoiThuong";
        try{
            ResultSet resultSet = db.excutedQuery(query);
            while(resultSet.next()){
                compensationSlipDTO compensationSlip = new compensationSlipDTO(
                        resultSet.getString("IDBoiThuong"),
                        resultSet.getString("IDMember"),
                        resultSet.getDate("NgayLap").toLocalDate(),
                        resultSet.getString("ChiTietBoiThuong"),
                        resultSet.getString("IDPhieuMuon")
                );
                list.add(compensationSlip);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public int insertCompensation(compensationSlipDTO compensationSlip) throws Exception{
        int res = 0;
        String query = "insert into BoiThuong (IDBoiThuong, IDMember, NgayLap, ChiTietBoiThuong, IDPhieuMuon) values('"
                + compensationSlip.getIDCompensation() + "', '" + compensationSlip.getIDMember() + "', '" + compensationSlip.getDate()
                + "', '" + compensationSlip.getNote() + "', '" + compensationSlip.getIDIssue() + "')";
        try{
            res = db.excuteUpdate(query);
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return res;
    }

    public int deleteCompensation(String IDIssue) throws Exception{
        int res = 0;
        String query = "delete from BoiThuong where IDPhieuMuon='" + IDIssue + "'";
        try{
            res = db.excuteUpdate(query);
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return res;
    }
}
