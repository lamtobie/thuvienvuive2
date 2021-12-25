package thuvienvuive.ThongKe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import thuvienvuive.Book.Book;
import thuvienvuive.Database.Connection;
import thuvienvuive.Member.MemberDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ThongKeDAO {
    Connection db = new Connection("localhost", "sa", "sa", "thuvienvuive");
    public ObservableList<ThongKeSachMuonDTO> SachMuonItDAO(LocalDate NgayBatDau, LocalDate NgayKetThuc )  throws Exception{
        ObservableList<ThongKeSachMuonDTO> SachMuonIt = FXCollections.observableArrayList();
        String query = "Select TOP(5)  Sach.IDSach,Sach.TenSach,IDTacGia,IDTheLoai,SoLuong,COUNT(Sach.IDSach) as SoLanMuon FROM Sach,PhieuMuon WHERE Sach.IDSach=PhieuMuon.IDSach and NgayTra<='"+NgayKetThuc+"' and NgayTra>='"+NgayBatDau+"' Group by PhieuMuon.IDSach,Sach.IDSach,Sach.TenSach,IDTacGia,IDTheLoai,SoLuong ORDER BY SoLanMuon ASC";
        try{
            ResultSet resultSet = db.excutedQuery(query);
            if (resultSet != null){
                while(resultSet.next()){
                    ThongKeSachMuonDTO sachmuon = new ThongKeSachMuonDTO(
                            resultSet.getString("IDSach"),
                            resultSet.getString("TenSach"),
                            resultSet.getString("IDTacGia"),
                            resultSet.getString("IDTheLoai"),
                            resultSet.getInt("SoLuong"),
                            resultSet.getInt("SoLanMuon")
                    );
                    SachMuonIt.add(sachmuon);
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return SachMuonIt;
    }
    public ObservableList<ThongKeSachMuonDTO> SachMuonNhieuDAO(LocalDate NgayBatDau, LocalDate NgayKetThuc )  throws Exception{
        ObservableList<ThongKeSachMuonDTO> SachMuonNhieu = FXCollections.observableArrayList();
        String query = "Select TOP(5)  Sach.IDSach,Sach.TenSach,IDTacGia,IDTheLoai,SoLuong,COUNT(Sach.IDSach) as SoLanMuon FROM Sach,PhieuMuon WHERE Sach.IDSach=PhieuMuon.IDSach and NgayTra<='"+NgayKetThuc+"' and NgayTra>='"+NgayBatDau+"' Group by PhieuMuon.IDSach,Sach.IDSach,Sach.TenSach,IDTacGia,IDTheLoai,SoLuong ORDER BY SoLanMuon DESC";
        try{
            ResultSet resultSet = db.excutedQuery(query);
            if (resultSet != null){
                while(resultSet.next()){
                    ThongKeSachMuonDTO sachmuon = new ThongKeSachMuonDTO(
                            resultSet.getString("IDSach"),
                            resultSet.getString("TenSach"),
                            resultSet.getString("IDTacGia"),
                            resultSet.getString("IDTheLoai"),
                            resultSet.getInt("SoLuong"),
                            resultSet.getInt("SoLanMuon")
                    );
                    SachMuonNhieu.add(sachmuon);
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return SachMuonNhieu;
    }
    public ObservableList<ThongKeSachHongDTO> SachHongDAO(LocalDate NgayBatDau, LocalDate NgayKetThuc )  throws Exception{
        ObservableList<ThongKeSachHongDTO> SachHong = FXCollections.observableArrayList();
        String query = "Select * FROM Sach,PhieuMuon,BoiThuong WHERE PhieuMuon.IDSach=Sach.IDSach and PhieuMuon.IDPhieuMuon=BoiThuong.IDPhieuMuon and NgayTra<='"+NgayKetThuc+"' and NgayTra>='"+NgayBatDau+"'";
        try{
            ResultSet resultSet = db.excutedQuery(query);
            if (resultSet != null){
                while(resultSet.next()){
                    ThongKeSachHongDTO sachhong = new ThongKeSachHongDTO(
                            resultSet.getString("IDTheLoai"),
                            resultSet.getString("IDMember"),
                            resultSet.getString("IDPhieuMuon"),
                            resultSet.getString("IDTacGia"),
                            resultSet.getString("TenSach"),
                            resultSet.getDate("NgayLap").toLocalDate()
                    );
                    SachHong.add(sachhong);
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return SachHong;
    }
}
