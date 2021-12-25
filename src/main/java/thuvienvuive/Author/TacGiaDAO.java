package thuvienvuive.Author;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import thuvienvuive.Database.Connection;
import thuvienvuive.Member.MemberDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TacGiaDAO {
    Connection db = new Connection("localhost", "sa", "sa", "thuvienvuive");

    //lấy list tacgia
    public ObservableList<TacGiaDTO> readListDAO() throws Exception{
        ObservableList<TacGiaDTO> authorList= FXCollections.observableArrayList();
        String query="Select * from TacGia";
        try{
            ResultSet resultSet = db.excutedQuery(query);
            if (resultSet != null){
                while(resultSet.next()){
                    TacGiaDTO tacgia=new TacGiaDTO(
                            resultSet.getString("IDTacGia"),
                            resultSet.getString("Ho"),
                            resultSet.getString("Ten"),
                            resultSet.getString("ThongTin"),
                            resultSet.getString("GhiChu")
                    );
                    authorList.add(tacgia);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  authorList;
    }

    //lấy tổng sl tác giả
    public int countAuthors() throws Exception{
        int count=0;
        String query="select count(*) from TacGia";
        try{
            ResultSet resultSet = db.excutedQuery(query);
            if (resultSet != null){
                while(resultSet.next()){
                    count=resultSet.getInt(1);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return count;
    }

    //thêm
    public int addAuthor(TacGiaDTO author) throws Exception{
        int res=0;
        String query="insert into TacGia values('"+author.getIDTacgia()+"','"+author.getHo()+"','"+author.getTen()+"','"+author.getThongTin()+"','"+author.getGhiChu()+"')";
        try{
            res=db.excuteUpdate(query);

        }catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }
    //sửa
    public int changeAuthor(TacGiaDTO author) throws Exception{
        int res=0;
        String query="Update TacGia set Ho='"+author.getHo()+"',Ten='"+author.getTen()+"',Thongtin='"+author.getThongTin()+"',GhiChu='"+author.getGhiChu()+"' where IDTacGia='"+author.getIDTacgia()+"'";
        try{
            res=db.excuteUpdate(query);

        }catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }
    //xóa
    public int deleteAuthor(String id) throws Exception{
        int res=0;
        String query="delete from TacGia where IDTacGia='"+id+"'";
        try{
            res=db.excuteUpdate(query);

        }catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }
}
