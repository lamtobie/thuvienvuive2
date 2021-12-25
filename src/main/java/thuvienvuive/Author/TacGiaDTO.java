package thuvienvuive.Author;

public class TacGiaDTO {
    String IDTacgia,Ho,Ten,ThongTin,GhiChu;

    public TacGiaDTO() {
    }

    public TacGiaDTO(String IDTacgia) {
        this.IDTacgia = IDTacgia;
    }

    public TacGiaDTO(String IDTacgia, String ho, String ten, String thongTin, String ghiChu) {
        this.IDTacgia = IDTacgia;
        Ho = ho;
        Ten = ten;
        ThongTin = thongTin;
        GhiChu = ghiChu;
    }

    public String getIDTacgia() {
        return IDTacgia;
    }

    public void setIDTacgia(String IDTacgia) {
        this.IDTacgia = IDTacgia;
    }

    public String getHo() {
        return Ho;
    }

    public void setHo(String ho) {
        Ho = ho;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getThongTin() {
        return ThongTin;
    }

    public void setThongTin(String thongTin) {
        ThongTin = thongTin;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }
}
