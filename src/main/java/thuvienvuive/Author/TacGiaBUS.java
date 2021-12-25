package thuvienvuive.Author;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TacGiaBUS {
    ObservableList <TacGiaDTO> listAuthors= FXCollections.observableArrayList();
    public TacGiaBUS(){

    }

    //lấy tổng sl tác giả
    public int countAuthors() throws Exception{
        TacGiaDAO authorDAO=new TacGiaDAO();
        int count=authorDAO.countAuthors();
        return count;
    }

    //lấy danh sách
    public ObservableList<TacGiaDTO> authorsList() throws Exception{
        TacGiaDAO authorDAO=new TacGiaDAO();
        listAuthors=authorDAO.readListDAO();
        return listAuthors;
    }

    //thêm
    public int addAuthor(TacGiaDTO author) throws Exception{
        TacGiaDAO authorDAO=new TacGiaDAO();
        int result=authorDAO.addAuthor(author);
        return result;
    }
    //sửa
    public int changeAuthor(TacGiaDTO author) throws Exception{
        TacGiaDAO authorDAO=new TacGiaDAO();
        int result=authorDAO.changeAuthor(author);
        return result;
    }
    //xóa
    public int deleteAuthor(String id) throws Exception{
        TacGiaDAO authorDAO=new TacGiaDAO();
        int result=authorDAO.deleteAuthor(id);
        return result;
    }

    // check id
    public int checkId(String id) throws Exception {
        TacGiaDAO authorDAO=new TacGiaDAO();
        listAuthors=authorDAO.readListDAO();
        int result=0;
        for(TacGiaDTO tg: listAuthors){
            if(id.equals(tg.getIDTacgia())){
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
}


