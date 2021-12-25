package thuvienvuive.Genre;

public class DTO_Genre {
	protected String IDTheLoai;
	protected String TenTheLoai;
	
	public DTO_Genre(String iDTheLoai, String tenTheLoai) {
		IDTheLoai = iDTheLoai;
		TenTheLoai = tenTheLoai;
	}

	public String getIDTheLoai() {
		return IDTheLoai;
	}

	public void setIDTheLoai(String iDTheLoai) {
		IDTheLoai = iDTheLoai;
	}

	public String getTenTheLoai() {
		return TenTheLoai;
	}

	public void setTenTheLoai(String tenTheLoai) {
		TenTheLoai = tenTheLoai;
	}	
}
