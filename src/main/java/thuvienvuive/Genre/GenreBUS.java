package thuvienvuive.Genre;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GenreBUS {
    ObservableList<GenreDTO> listGenre= FXCollections.observableArrayList();
    public GenreBUS(){

    }
    //lấy danh sách
    public ObservableList<GenreDTO> genreList() throws Exception{
        GenreDAO genreDAO=new GenreDAO();
        listGenre=genreDAO.readListDAO();
        return listGenre;
    }
    // check id
    public int checkId(String id) throws Exception {
        GenreDAO genreDAO=new GenreDAO();
        listGenre=genreDAO.readListDAO();
        int result=0;
        for(GenreDTO type: listGenre){
            if(id.equals(type.getIDTheLoai())){
                result=0;
                break;
            }
            else{
                result=1;
                continue;
            }
        }
        return result;
    }
    //thêm
    public int addGenre(GenreDTO type) throws Exception{
        GenreDAO genreDAO=new GenreDAO();
        int result=genreDAO.addGenre(type);
        return result;
    }
    //sửa
    public int editGenre(GenreDTO type) throws Exception{
        GenreDAO genreDAO=new GenreDAO();
        int result=genreDAO.editGenre(type);
        return result;
    }
    //xóa
    public int deleteGenre(String id) throws Exception{
        GenreDAO genreDAO=new GenreDAO();
        int result=genreDAO.deleteGenre(id);
        return result;
    }

}
