package thuvienvuive.Member;

public class MemberDTO {
    String ho, ten, ID, soDienThoai, email, gioiTinh, hinhAnh;

    public MemberDTO(String ID, String ho, String ten, String soDienThoai, String email, String gioiTinh, String hinhAnh) {
        this.ho = ho;
        this.ten = ten;
        this.ID = ID;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.gioiTinh = gioiTinh;
        this.hinhAnh = hinhAnh;
    }

    public MemberDTO(String ID, String ho, String ten, String soDienThoai, String email, String gioiTinh) {
        this.ho = ho;
        this.ten = ten;
        this.ID = ID;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.gioiTinh = gioiTinh;
        this.hinhAnh = "non_avatar.png";
    }

    public MemberDTO(){

    }


    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    @Override
    public String toString() {
        return "ThanhVien{" +
                "ho='" + ho + '\'' +
                ", ten='" + ten + '\'' +
                ", ID='" + ID + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", email='" + email + '\'' +
                ", gioiTinh='" + gioiTinh + '\'' +
                '}';
    }
}
