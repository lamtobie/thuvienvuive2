package thuvienvuive.Genre;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import thuvienvuive.Database.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreDAO {
    Connection db = new Connection("localhost", "sa", "sa", "thuvienvuive");
    //lấy list
    public ObservableList<GenreDTO> readListDAO() throws Exception{
        ObservableList<GenreDTO> genreList= FXCollections.observableArrayList();
        String query="Select * from TheLoai";
        try{
            ResultSet resultSet = db.excutedQuery(query);
            if (resultSet != null){
                while(resultSet.next()){
                    GenreDTO genreDTO=new GenreDTO(
                            resultSet.getString(1),
                            resultSet.getString(2)
                    );
                    genreList.add(genreDTO);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  genreList;
    }

    //thêm
    public int addGenre(GenreDTO type) throws Exception{
        int res=0;
        String query="insert into TheLoai values('"+type.getIDTheLoai()+"','"+type.getTen()+"')";
        try{
            res=db.excuteUpdate(query);

        }catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }
    //sửa
    public int editGenre(GenreDTO type) throws Exception{
        int res=0;
        String query="Update TheLoai set Ten='"+type.getTen()+"' where IDTheLoai='"+type.getIDTheLoai()+"'";
        try{
            res=db.excuteUpdate(query);

        }catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }
    //xóa
    public int deleteGenre(String id) throws Exception{
        int res=0;
        String query="delete from TheLoai where IDTheLoai='"+id+"'";
        try{
            res=db.excuteUpdate(query);

        }catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }

}
