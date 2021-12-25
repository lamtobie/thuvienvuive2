package thuvienvuive.ThongKe;

import java.time.LocalDate;

public class ThongKeSachHongDTO {
    String IDTheLoai,IDMember,IDPhieuMuon,TenSach,IDTacGia;
    LocalDate NgayLap;

    public ThongKeSachHongDTO(){
        TenSach=null;
        this.IDTheLoai = null;
        this.IDMember = null;
        this.IDPhieuMuon = null;
        IDTacGia= null;
        NgayLap = null;
    }

    public ThongKeSachHongDTO(String IDTheLoai, String IDMember, String IDPhieuMuon, String IDTacGia, String TenSach,LocalDate ngayLap) {
        this.IDPhieuMuon = IDPhieuMuon;
        this.IDTheLoai = IDTheLoai;
        this.TenSach=TenSach;
        this.IDMember = IDMember;
        this.IDTacGia = IDTacGia;
        NgayLap = ngayLap;
    }

    public String getIDTheLoai() {
        return IDTheLoai;
    }

    public void setIDTheLoai(String IDTheLoai) {
        this.IDTheLoai = IDTheLoai;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        TenSach = tenSach;
    }

    public String getIDMember() {
        return IDMember;
    }

    public void setIDMember(String IDMember) {
        this.IDMember = IDMember;
    }

    public String getIDPhieuMuon() {
        return IDPhieuMuon;
    }

    public void setIDPhieuMuon(String IDPhieuMuon) {
        this.IDPhieuMuon = IDPhieuMuon;
    }

    public String getIDTacGia() {
        return IDTacGia;
    }

    public void setIDTacGia(String IDTacGia) {
        this.IDTacGia = IDTacGia;
    }

    public LocalDate getNgayLap() {
        return NgayLap;
    }

    public void setNgayLap(LocalDate ngayLap) {
        NgayLap = ngayLap;
    }
}
