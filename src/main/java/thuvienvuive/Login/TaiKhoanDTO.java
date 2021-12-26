package thuvienvuive.Login;

public class TaiKhoanDTO {
    String IDNhanVien,TenTaiKhoan,MatKhau,IDPhanQuyen;

    public TaiKhoanDTO() {
    }

    public TaiKhoanDTO(String IDNhanVien) {
        this.IDNhanVien = IDNhanVien;
    }

    @Override
    public String toString() {
        return "TaiKhoanDTO{" +
                "IDNhanVien='" + IDNhanVien + '\'' +
                ", TenTaiKhoan='" + TenTaiKhoan + '\'' +
                ", MatKhau='" + MatKhau + '\'' +
                ", IDPhanQuyen='" + IDPhanQuyen + '\'' +
                '}';
    }

    public TaiKhoanDTO(String IDNhanVien, String tenTaiKhoan, String matKhau, String IDPhanQuyen) {
        this.IDNhanVien = IDNhanVien;
        TenTaiKhoan = tenTaiKhoan;
        MatKhau = matKhau;
        this.IDPhanQuyen = IDPhanQuyen;
    }

    public String getIDNhanVien() {
        return IDNhanVien;
    }

    public void setIDNhanVien(String IDNhanVien) {
        this.IDNhanVien = IDNhanVien;
    }

    public String getTenTaiKhoan() {
        return TenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        TenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getIDPhanQuyen() {
        return IDPhanQuyen;
    }

    public void setIDPhanQuyen(String IDPhanQuyen) {
        this.IDPhanQuyen = IDPhanQuyen;
    }
}
