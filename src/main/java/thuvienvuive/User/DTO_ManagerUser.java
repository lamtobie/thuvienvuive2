package thuvienvuive.User;

public class DTO_ManagerUser {
	protected String IDNhanVien;
	protected String Ho;
	protected String Ten;
	protected String TaiKhoan;
	protected String MatKhau;
	protected String ChucVu;
	protected String Quyen;
	
	public DTO_ManagerUser(String iDNhanVien, String ho, String ten, String taiKhoan, String matKhau, String chucVu,
			String quyen) {
		IDNhanVien = iDNhanVien;
		Ho = ho;
		Ten = ten;
		TaiKhoan = taiKhoan;
		MatKhau = matKhau;
		ChucVu = chucVu;
		Quyen = quyen;
	}

	public String getIDNhanVien() {
		return IDNhanVien;
	}

	public void setIDNhanVien(String iDNhanVien) {
		IDNhanVien = iDNhanVien;
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

	public String getTaiKhoan() {
		return TaiKhoan;
	}

	public void setTaiKhoan(String taiKhoan) {
		TaiKhoan = taiKhoan;
	}

	public String getMatKhau() {
		return MatKhau;
	}

	public void setMatKhau(String matKhau) {
		MatKhau = matKhau;
	}

	public String getChucVu() {
		return ChucVu;
	}

	public void setChucVu(String chucVu) {
		ChucVu = chucVu;
	}

	public String getQuyen() {
		return Quyen;
	}

	public void setQuyen(String quyen) {
		Quyen = quyen;
	}
}
