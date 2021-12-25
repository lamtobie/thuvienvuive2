package thuvienvuive.Book;

import java.time.LocalDate;

public class bookIssueDTO {
    String IDNhanVien, IDThanhVien, ghiChu, trangThai, IDPhieuMuon, IDSach;
    LocalDate ngayMuon, ngayTra;

    public bookIssueDTO() {
        this.IDNhanVien = "";
        this.IDThanhVien = "";
        this.ghiChu = "";
        this.trangThai = "";
        this.ngayMuon = null;
        this.ngayTra = null;
        this.IDPhieuMuon = "";
        IDSach = "";
    }

    public bookIssueDTO(String IDPhieuMuon, String IDThanhVien, String IDNhanVien,LocalDate ngayMuon, LocalDate ngayTra, String ghiChu,String IDSach) {
        this.IDNhanVien = IDNhanVien;
        this.IDThanhVien = IDThanhVien;
        this.ghiChu = ghiChu;
        this.trangThai = "Đang mượn";
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
        this.IDPhieuMuon = IDPhieuMuon;
        this.IDSach = IDSach;
    }

    public String getIDSach() {
        return IDSach;
    }

    public void setIDSach(String IDSach) {
        this.IDSach = IDSach;
    }

    public bookIssueDTO(String IDPhieuMuon, String IDThanhVien, String IDNhanVien, LocalDate ngayMuon, LocalDate ngayTra, String ghiChu, String trangThai, String IDSach) {
        this.IDNhanVien = IDNhanVien;
        this.IDThanhVien = IDThanhVien;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
        this.IDPhieuMuon = IDPhieuMuon;
        this.IDSach = IDSach;
    }

    public String getIDPhieuMuon() {
        return IDPhieuMuon;
    }

    public void setIDPhieuMuon(String IDPhieuMuon) {
        this.IDPhieuMuon = IDPhieuMuon;
    }

    public String getIDNhanVien() {
        return IDNhanVien;
    }

    public void setIDNhanVien(String IDNhanVien) {
        this.IDNhanVien = IDNhanVien;
    }

    public String getIDThanhVien() {
        return IDThanhVien;
    }

    public void setIDThanhVien(String IDThanhVien) {
        this.IDThanhVien = IDThanhVien;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public LocalDate getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(LocalDate ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public LocalDate getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(LocalDate ngayTra) {
        this.ngayTra = ngayTra;
    }
}
