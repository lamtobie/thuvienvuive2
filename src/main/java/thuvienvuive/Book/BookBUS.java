package thuvienvuive.Book;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BookBUS {
    ObservableList<Book> listBooks= FXCollections.observableArrayList();
    public BookBUS(){

    }
    //đọc list sách
    public ObservableList<Book> readListBooks() throws Exception {
        BookDAO bookdao=new BookDAO();
        listBooks=bookdao.readListBooks();
        return listBooks;
    }

    // xuất 5 sách mới nhất
    public ObservableList<Book> findfiveNewBooks() throws Exception{
        BookDAO bookdao=new BookDAO();
        listBooks=bookdao.findfiveNewBooks();
        return listBooks;
    }

    // lấy tổng số sách trong kho
    public int countBooks() throws Exception{
        BookDAO bookdao=new BookDAO();
        return  bookdao.countBooks();
    }
}
