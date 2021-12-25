package thuvienvuive.User;

public class NhanVienDTO {
    String IDNhanVien,Ho,Ten,SDT,IDPhanQuyen,GioiTinh,Email,ChucVu,TrangThai;

    public NhanVienDTO() {
    }

    public NhanVienDTO(String IDNhanVien) {
        this.IDNhanVien = IDNhanVien;
    }

    public NhanVienDTO(String IDNhanVien, String ho, String ten, String SDT, String IDPhanQuyen, String gioiTinh, String email, String chucVu, String trangThai) {
        this.IDNhanVien = IDNhanVien;
        Ho = ho;
        Ten = ten;
        this.SDT = SDT;
        this.IDPhanQuyen = IDPhanQuyen;
        GioiTinh = gioiTinh;
        Email = email;
        ChucVu = chucVu;
        TrangThai = trangThai;
    }

    public String getIDNhanVien() {
        return IDNhanVien;
    }

    public void setIDNhanVien(String IDNhanVien) {
        this.IDNhanVien = IDNhanVien;
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

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getIDPhanQuyen() {
        return IDPhanQuyen;
    }

    public void setIDPhanQuyen(String IDPhanQuyen) {
        this.IDPhanQuyen = IDPhanQuyen;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public void setChucVu(String chucVu) {
        ChucVu = chucVu;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }
}

