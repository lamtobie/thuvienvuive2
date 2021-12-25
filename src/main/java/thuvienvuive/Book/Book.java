package thuvienvuive.Book;

import java.time.LocalDate;

public class Book {
    String ID, ten, IDTacGia, IDTheLoai, hinhAnh, ghiChu;
    int soLuong, soTrang;
    float giaTien;
    LocalDate ngayNhanSach, ngayXuatBan;

    public Book(){
        this.ID = "";
        this.ten = "";
        this.IDTacGia = "";
        this.IDTheLoai = "";
        this.ngayXuatBan = null;
        this.soLuong = -1;
        this.soTrang = 0;
        this.giaTien = 0;
        this.ngayNhanSach = null;
        this.hinhAnh = "";
        this.ghiChu = "";
    }

    public Book(String ID, String ten, String IDTacGia, int soLuong, LocalDate ngayXuatBan, LocalDate ngayNhanSach, int soTrang, String ghiChu, String IDTheLoai, float giaTien) {
        this.ID = ID;
        this.ten = ten;
        this.IDTacGia = IDTacGia;
        this.IDTheLoai = IDTheLoai;
        this.ngayXuatBan = ngayXuatBan;
        this.soLuong = soLuong;
        this.soTrang = soTrang;
        this.giaTien = giaTien;
        this.ngayNhanSach = ngayNhanSach;
        this.ghiChu = ghiChu;
        this.hinhAnh = "";
    }

    public Book(String ID, String ten, String IDTacGia, int soLuong, LocalDate ngayXuatBan, LocalDate ngayNhanSach, int soTrang, String ghiChu, String IDTheLoai, String hinhAnh, float giaTien) {
        this.ID = ID;
        this.ten = ten;
        this.IDTacGia = IDTacGia;
        this.IDTheLoai = IDTheLoai;
        this.ngayXuatBan = ngayXuatBan;
        this.soTrang = soTrang;
        this.hinhAnh = hinhAnh;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
        this.ngayNhanSach = ngayNhanSach;
        this.ghiChu = ghiChu;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public LocalDate getNgayXuatBan() {
        return ngayXuatBan;
    }

    public void setNgayXuatBan(LocalDate ngayXuatBan) {
        this.ngayXuatBan = ngayXuatBan;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getSoTrang() {
        return soTrang;
    }

    public void setSoTrang(int soTrang) {
        this.soTrang = soTrang;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
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

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(float giaTien) {
        this.giaTien = giaTien;
    }

    public LocalDate getNgayNhanSach() {
        return ngayNhanSach;
    }

    public void setNgayNhanSach(LocalDate ngayNhanSach) {
        this.ngayNhanSach = ngayNhanSach;
    }
}
