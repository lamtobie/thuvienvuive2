package thuvienvuive.User;

import thuvienvuive.Member.MemberListDAO;

public class NhanVienBUS {
    public NhanVienBUS(){

    }
    //lâý tổng thành viên
    public int countNhanVien() throws Exception{
        NhanVienDAO nhanVienDAO=new NhanVienDAO();
        int count=nhanVienDAO.countNhanVien();
        return count;
    }
}
