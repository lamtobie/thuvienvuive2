package thuvienvuive.Login;

import thuvienvuive.Database.Connection;
import thuvienvuive.Member.MemberDTO;
import thuvienvuive.User.NhanVienDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaiKhoanDAO {
    Connection db = new Connection("localhost", "sa", "sa", "thuvienvuive");

    //Login
    public int login(TaiKhoanDTO account) throws Exception{
        //0:lỗi;1:user;2:admin
        int kq=0;
        String query="select nv.IDPhanQuyen from NhanVien as nv,TaiKhoan as tk where nv.IDNhanVien=tk.IDNhanVien and tk.TenTaiKhoan='"+
                account.getTenTaiKhoan()+"' and tk.MatKhau='"+account.getMatKhau()+"'";
        try{
            ResultSet resultSet=db.excutedQuery(query);
            while(resultSet.next()){
                if(resultSet.getString(1).equals("IDPQ1")){
                    kq=2;
                }
                if(resultSet.getString(1).equals("IDPQ2")){
                    kq=1;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return kq;
    }

    //lấy user
    public NhanVienDTO accountInfo(TaiKhoanDTO account) throws Exception{
        NhanVienDTO nhanVienDTO=new NhanVienDTO();
        String query="select nv.IDNhanVien, nv.Ho,nv.Ten from NhanVien as nv, TaiKhoan as tk where nv.IDNhanVien=tk.IDNhanVien and tk.TenTaiKhoan='"+account.getTenTaiKhoan()+"' and tk.MatKhau='"+account.getMatKhau()+"'";
        try{
            ResultSet resultSet=db.excutedQuery(query);
            while (resultSet.next()){
                nhanVienDTO.setIDNhanVien(resultSet.getString(1));
                nhanVienDTO.setHo(resultSet.getString(2));
                nhanVienDTO.setTen(resultSet.getString(3));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return nhanVienDTO;
    }
}
