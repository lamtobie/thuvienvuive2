package thuvienvuive.ThongKe;

public class ThongKeSachMuonDTO {
    String IDSach,TenSach,IDTacGia,IDTheLoai;
    int SoLuongTon,SoLanMuon;
    public ThongKeSachMuonDTO() {
        this.IDSach = null;
        TenSach = null;
        this.IDTacGia = null;
        this.IDTheLoai = null;
        SoLuongTon = 0;
        SoLanMuon = 0;
    }

    public ThongKeSachMuonDTO(String IDSach, String tenSach, String IDTacGia, String IDTheLoai, int soLuongTon, int soLanMuon) {
        this.IDSach = IDSach;
        TenSach = tenSach;
        this.IDTacGia = IDTacGia;
        this.IDTheLoai = IDTheLoai;
        SoLuongTon = soLuongTon;
        SoLanMuon = soLanMuon;
    }

    public String getIDSach() {
        return IDSach;
    }

    public void setIDSach(String IDSach) {
        this.IDSach = IDSach;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        TenSach = tenSach;
    }

    public String getIDTacGia() {
        return IDTacGia;
    }

    public void setIDTacGia(String IDTacGia) {
        this.IDTacGia = IDTacGia;
    }

    public String getIDTheLoai() {
        return IDTheLoai;
    }

    public void setIDTheLoai(String IDTheLoai) {
        this.IDTheLoai = IDTheLoai;
    }

    public int getSoLuongTon() {
        return SoLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        SoLuongTon = soLuongTon;
    }

    public int getSoLanMuon() {
        return SoLanMuon;
    }

    public void setSoLanMuon(int soLanMuon) {
        SoLanMuon = soLanMuon;
    }
}
