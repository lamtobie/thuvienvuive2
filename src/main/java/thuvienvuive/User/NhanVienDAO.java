
package thuvienvuive.User;

import thuvienvuive.Database.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NhanVienDAO {
    Connection db = new Connection("localhost", "sa", "sa", "thuvienvuive");
    //lấy tổng thành viên
    public int countNhanVien() throws Exception{
        int count=0;
        String query="Select count(*) from NhanVien";
        try{
            ResultSet resultSets=db.excutedQuery(query);
            if(resultSets!=null){
                while (resultSets.next()){
                    count=resultSets.getInt(1);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return count;
    }
}
