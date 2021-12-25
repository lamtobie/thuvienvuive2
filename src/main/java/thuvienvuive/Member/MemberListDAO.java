package thuvienvuive.Member;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import thuvienvuive.Database.Connection;
import thuvienvuive.Member.MemberDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberListDAO{
    Connection db = new Connection("localhost", "sa", "sa", "thuvienvuive");

    public ObservableList<MemberDTO> readListDAO()  throws Exception{
        ObservableList<MemberDTO> memberList = FXCollections.observableArrayList();
        String query = "select * from Member";
        try{
            ResultSet resultSet = db.excutedQuery(query);
            if (resultSet != null){
                while(resultSet.next()){
                    MemberDTO member = new MemberDTO(
                            resultSet.getString("IDMember"),
                            resultSet.getString("Ho"),
                            resultSet.getString("Ten"),
                            resultSet.getString("SDT"),
                            resultSet.getString("Email"),
                            resultSet.getString("GioiTinh")
                    );
                    memberList.add(member);
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return memberList;
    }
}
