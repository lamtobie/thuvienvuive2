package thuvienvuive.Genre;

public class GenreDTO {
    String IDTheLoai,Ten;

    public GenreDTO() {
    }

    public GenreDTO(String IDTheLoai) {
        this.IDTheLoai = IDTheLoai;
    }

    public GenreDTO(String IDTheLoai, String ten) {
        this.IDTheLoai = IDTheLoai;
        Ten = ten;
    }

    public String getIDTheLoai() {
        return IDTheLoai;
    }

    public void setIDTheLoai(String IDTheLoai) {
        this.IDTheLoai = IDTheLoai;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }
}
