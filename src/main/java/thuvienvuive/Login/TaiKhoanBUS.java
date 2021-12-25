package thuvienvuive.Login;

import thuvienvuive.Member.MemberDTO;
import thuvienvuive.User.NhanVienDTO;

public class TaiKhoanBUS {
    public TaiKhoanBUS(){

    }
    public int login(TaiKhoanDTO account) throws Exception{
        TaiKhoanDAO accountDAO=new TaiKhoanDAO();
        int kq=accountDAO.login(account);
        return kq;
    }
    public NhanVienDTO accountInfo(TaiKhoanDTO account) throws Exception{
        TaiKhoanDAO accountDAO=new TaiKhoanDAO();
        NhanVienDTO nhanVienDTO=accountDAO.accountInfo(account);
        return nhanVienDTO;
    }
}
