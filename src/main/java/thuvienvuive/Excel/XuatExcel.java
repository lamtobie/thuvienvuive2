
package thuvienvuive.Excel;

import java.awt.FileDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javafx.collections.ObservableList;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import thuvienvuive.ThongKe.ThongKeSachHongDTO;
import thuvienvuive.ThongKe.ThongKeSachMuonBUS;
import thuvienvuive.ThongKe.ThongKeSachMuonDTO;

/**
 *
 * @author Admin
 */
public class XuatExcel {

    FileDialog fd = new FileDialog(new JFrame(), "Xuất excel", FileDialog.SAVE);

    private String getFile() {
        fd.setFile("untitled.xls");
        fd.setVisible(true);
        String url = fd.getDirectory() + fd.getFile();
        if (url.equals("nullnull")) {
            return null;
        }
        return url;
    }
        // Xuất file Excel Món Ăn   
    public void xuatFileExcelThongKeSachHong(LocalDate NgayBatDau,LocalDate NgayKetThuc) {
        fd.setTitle("Xuất thống kê sách hỏng ra excel"); //Set tên
        String url = getFile(); //Kiểm tra getfile()
        if (url == null) {
            return;
        }
        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();//Đọc và ghi file định dạng Microsoft Excel (XLS – định dạng hỗ trợ của Excel 2003) - Workbook: file
            HSSFSheet sheet = workbook.createSheet("Thống kê sách hỏng");//Tạo bảng tính Món Ăn
            ThongKeSachMuonBUS thongkeBUS = new ThongKeSachMuonBUS(); //Tạo biến monanBUS
            ObservableList<ThongKeSachHongDTO> list =  thongkeBUS.getSachHong(NgayBatDau,NgayKetThuc);  // tạo danh sách lấy từ DTO thông qua BUS

            int rownum = 0; //cột thứ 0
            Row row = sheet.createRow(rownum); //tạo biến row (hàng) trong sheet
//createCell(int cột, CellType."kiểu dữ liệu") row.createCell (hàng row, tạo cột) 
            row.createCell(0, CellType.STRING).setCellValue("IDPhieuMuon"); //Hàng 0. cột 0- kiểu String, giá trị ID
            row.createCell(1, CellType.STRING).setCellValue("IDTheLoai"); //Hàng 0. cột 1- kiểu String, giá trị Tên món
            row.createCell(2, CellType.STRING).setCellValue("Tên Sách");//Hàng 0. cột 2- kiểu String, giá trị Đơn vị tính
            row.createCell(3, CellType.STRING).setCellValue("IDMember");//Hàng 0. cột 3- kiểu String, giá trị Giá
            row.createCell(4, CellType.STRING).setCellValue("IDTacGia");//Hàng 0. cột 4- kiểu String, giá trị Hình Ảnh
            row.createCell(5, CellType.STRING).setCellValue("Ngày lập");//Hàng 0. cột 5- kiểu String, giá trị Loại
//Tạo vòng lập for chạy hết giá trị của list
            for (ThongKeSachHongDTO ma : list) {
                rownum++; //rownum (tăng lên giá trị, lúc nãy là 0 giờ là 1 - hàng thứ 1)
                row = sheet.createRow(rownum);
                row.createCell(0, CellType.STRING).setCellValue(ma.getIDPhieuMuon());
                row.createCell(1, CellType.STRING).setCellValue(ma.getIDTheLoai());
                row.createCell(2, CellType.STRING).setCellValue(ma.getTenSach());
                row.createCell(3, CellType.STRING).setCellValue(ma.getIDMember()); // Cột 3- kiểu NUMERIC, giá trị chuyển sang String
                row.createCell(4, CellType.STRING).setCellValue(ma.getIDTacGia());
                row.createCell(5, CellType.STRING).setCellValue(ma.getNgayLap().toString());
            }
//Tạo vòng lập từ 0 tới rownum để set lại kích thước cột cho gọn
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }
//Tiến hành tạo file và ghi file
            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void xuatFileExcelThongKeSachMuonNhieu(LocalDate NgayBatDau,LocalDate NgayKetThuc) {
        fd.setTitle("Xuất thống kê sách hỏng ra excel"); //Set tên
        String url = getFile(); //Kiểm tra getfile()
        if (url == null) {
            return;
        }
        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();//Đọc và ghi file định dạng Microsoft Excel (XLS – định dạng hỗ trợ của Excel 2003) - Workbook: file
            HSSFSheet sheet = workbook.createSheet("Thống kê sách mượn nhiều");//Tạo bảng tính Món Ăn
            ThongKeSachMuonBUS thongkeBUS = new ThongKeSachMuonBUS(); //Tạo biến monanBUS
            ObservableList<ThongKeSachMuonDTO> list =  thongkeBUS.getSachMuonNhieu(NgayBatDau,NgayKetThuc);  // tạo danh sách lấy từ DTO thông qua BUS

            int rownum = 0; //cột thứ 0
            Row row = sheet.createRow(rownum); //tạo biến row (hàng) trong sheet
//createCell(int cột, CellType."kiểu dữ liệu") row.createCell (hàng row, tạo cột)
            row.createCell(0, CellType.STRING).setCellValue("IDSach"); //Hàng 0. cột 0- kiểu String, giá trị ID
            row.createCell(1, CellType.STRING).setCellValue("Tên Sách"); //Hàng 0. cột 1- kiểu String, giá trị Tên món
            row.createCell(2, CellType.STRING).setCellValue("ID tác giả");//Hàng 0. cột 2- kiểu String, giá trị Đơn vị tính
            row.createCell(3, CellType.STRING).setCellValue("ID thể loại");//Hàng 0. cột 3- kiểu String, giá trị Giá
            row.createCell(4, CellType.NUMERIC).setCellValue("Số lượng tồn");//Hàng 0. cột 4- kiểu String, giá trị Hình Ảnh
            row.createCell(5, CellType.NUMERIC).setCellValue("Số lần mượn");//Hàng 0. cột 5- kiểu String, giá trị Loại
//Tạo vòng lập for chạy hết giá trị của list
            for (ThongKeSachMuonDTO ma : list) {
                rownum++; //rownum (tăng lên giá trị, lúc nãy là 0 giờ là 1 - hàng thứ 1)
                row = sheet.createRow(rownum);
                row.createCell(0, CellType.STRING).setCellValue(ma.getIDSach());
                row.createCell(1, CellType.STRING).setCellValue(ma.getTenSach());
                row.createCell(2, CellType.STRING).setCellValue(ma.getIDTacGia());
                row.createCell(3, CellType.STRING).setCellValue(ma.getIDTheLoai()); // Cột 3- kiểu NUMERIC, giá trị chuyển sang String
                row.createCell(4, CellType.NUMERIC).setCellValue(ma.getSoLuongTon());
                row.createCell(5, CellType.NUMERIC).setCellValue(ma.getSoLanMuon());
            }
//Tạo vòng lập từ 0 tới rownum để set lại kích thước cột cho gọn
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }
//Tiến hành tạo file và ghi file
            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    public void xuatFileExcelThongKeSachMuonIt(LocalDate NgayBatDau,LocalDate NgayKetThuc) {
        fd.setTitle("Xuất thống kê sách hỏng ra excel"); //Set tên
        String url = getFile(); //Kiểm tra getfile()
        if (url == null) {
            return;
        }
        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();//Đọc và ghi file định dạng Microsoft Excel (XLS – định dạng hỗ trợ của Excel 2003) - Workbook: file
            HSSFSheet sheet = workbook.createSheet("Thống kê sách mượn ít");//Tạo bảng tính Món Ăn
            ThongKeSachMuonBUS thongkeBUS = new ThongKeSachMuonBUS(); //Tạo biến monanBUS
            ObservableList<ThongKeSachMuonDTO> list =  thongkeBUS.getSachMuonIt(NgayBatDau,NgayKetThuc);  // tạo danh sách lấy từ DTO thông qua BUS

            int rownum = 0; //cột thứ 0
            Row row = sheet.createRow(rownum); //tạo biến row (hàng) trong sheet
//createCell(int cột, CellType."kiểu dữ liệu") row.createCell (hàng row, tạo cột)
            row.createCell(0, CellType.STRING).setCellValue("IDSach"); //Hàng 0. cột 0- kiểu String, giá trị ID
            row.createCell(1, CellType.STRING).setCellValue("Tên Sách"); //Hàng 0. cột 1- kiểu String, giá trị Tên món
            row.createCell(2, CellType.STRING).setCellValue("ID tác giả");//Hàng 0. cột 2- kiểu String, giá trị Đơn vị tính
            row.createCell(3, CellType.STRING).setCellValue("ID thể loại");//Hàng 0. cột 3- kiểu String, giá trị Giá
            row.createCell(4, CellType.NUMERIC).setCellValue("Số lượng tồn");//Hàng 0. cột 4- kiểu String, giá trị Hình Ảnh
            row.createCell(5, CellType.NUMERIC).setCellValue("Số lần mượn");//Hàng 0. cột 5- kiểu String, giá trị Loại
//Tạo vòng lập for chạy hết giá trị của list
            for (ThongKeSachMuonDTO ma : list) {
                rownum++; //rownum (tăng lên giá trị, lúc nãy là 0 giờ là 1 - hàng thứ 1)
                row = sheet.createRow(rownum);
                row.createCell(0, CellType.STRING).setCellValue(ma.getIDSach());
                row.createCell(1, CellType.STRING).setCellValue(ma.getTenSach());
                row.createCell(2, CellType.STRING).setCellValue(ma.getIDTacGia());
                row.createCell(3, CellType.STRING).setCellValue(ma.getIDTheLoai()); // Cột 3- kiểu NUMERIC, giá trị chuyển sang String
                row.createCell(4, CellType.NUMERIC).setCellValue(ma.getSoLuongTon());
                row.createCell(5, CellType.NUMERIC).setCellValue(ma.getSoLanMuon());
            }
//Tạo vòng lập từ 0 tới rownum để set lại kích thước cột cho gọn
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }
//Tiến hành tạo file và ghi file
            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private String getTime() {
        return new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
    }
}



