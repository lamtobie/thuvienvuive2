package thuvienvuive.Member;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import thuvienvuive.Database.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class deleteMemberDAO {

    Connection conn = new Connection("localhost", "sa", "", "thuvienvuivee");
    public ObservableList<MemberDTO> docDB() throws Exception {
        ObservableList<MemberDTO> dsmember = FXCollections.observableArrayList();
        try {
            String qry = "SELECT * FROM Member";
            ResultSet rs = conn.excutedQuery(qry);
            if (rs != null) {
                while (rs.next()) {
                    MemberDTO memberDTO = new MemberDTO();
                    memberDTO.setID(rs.getString("IDMember"));
                    memberDTO.setHo(rs.getString("Ho"));
                    memberDTO.setTen(rs.getString("Ten"));
                    memberDTO.setSoDienThoai(rs.getString("SDT"));
                    memberDTO.setEmail(rs.getString("Email"));
                    memberDTO.setGioiTinh(rs.getString("GioiTinh"));
                    memberDTO.setHinhAnh(rs.getString("HinhAnh"));
                    dsmember.add(memberDTO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsmember;
    }

    public int deleteMember(String ID) throws Exception{
        String qry = "delete from Member where IDMember='" + ID + "'";
        int res = 0;
        try{
            res = conn.excuteUpdate(qry);
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public int deletePhieuMuon(String ID) throws Exception{
        String qry = "delete from PhieuMuon where IDMember='" + ID + "'";
        int res = 0;
        try{
            res = conn.excuteUpdate(qry);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return res;
    }
}
