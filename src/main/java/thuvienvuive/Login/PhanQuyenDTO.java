package thuvienvuive.Login;

public class PhanQuyenDTO {
    String IDPhanQuyen,TenChucVu,MoTaQuyen,TrangThai;

    public PhanQuyenDTO() {
    }

    public PhanQuyenDTO(String IDPhanQuyen) {
        this.IDPhanQuyen = IDPhanQuyen;
    }

    public PhanQuyenDTO(String IDPhanQuyen, String tenChucVu, String moTaQuyen, String trangThai) {
        this.IDPhanQuyen = IDPhanQuyen;
        TenChucVu = tenChucVu;
        MoTaQuyen = moTaQuyen;
        TrangThai = trangThai;
    }

    public String getIDPhanQuyen() {
        return IDPhanQuyen;
    }

    public void setIDPhanQuyen(String IDPhanQuyen) {
        this.IDPhanQuyen = IDPhanQuyen;
    }

    public String getTenChucVu() {
        return TenChucVu;
    }

    public void setTenChucVu(String tenChucVu) {
        TenChucVu = tenChucVu;
    }

    public String getMoTaQuyen() {
        return MoTaQuyen;
    }

    public void setMoTaQuyen(String moTaQuyen) {
        MoTaQuyen = moTaQuyen;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }
}
