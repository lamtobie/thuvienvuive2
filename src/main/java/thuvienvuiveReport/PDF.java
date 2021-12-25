/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thuvienvuiveReport;


import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import thuvienvuive.ThongKe.ThongKeSachHongDTO;
import thuvienvuive.ThongKe.ThongKeSachMuonBUS;
import thuvienvuive.ThongKe.ThongKeSachMuonDTO;
import thuvienvuive.Book.*;
import java.awt.FileDialog;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class PDF {
    Document document;
    FileOutputStream file;
    Font fontData;
    Font fontTitle;
    Font fontHeader;

    FileDialog fd = new FileDialog(new JFrame(), "Xuất PDF", FileDialog.SAVE);

    public PDF() {
        try {
            //Set font theo Arial trong Windows và các kích cỡ, định dạng thường
            fontData = new Font(BaseFont.createFont("C:\\Windows\\Fonts\\Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 11, Font.NORMAL);
            fontTitle = new Font(BaseFont.createFont("C:\\Windows\\Fonts\\Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 25, Font.NORMAL);
            fontHeader = new Font(BaseFont.createFont("C:\\Windows\\Fonts\\Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 13, Font.NORMAL);
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void chooseURL(String url) {
        try {
            document.close();
            document = new Document();
            file = new FileOutputStream(url);
            PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Khong tim thay duong dan file " + url);
        } catch (DocumentException ex) {
            JOptionPane.showMessageDialog(null, "Khong goi duoc document !");
        }
    }

    public void setTitle(String title) {
        try {
            Paragraph pdfTitle = new Paragraph(new Phrase(title, fontTitle));
            pdfTitle.setAlignment(Element.ALIGN_CENTER);
            document.add(pdfTitle);
            document.add(Chunk.NEWLINE);
        } catch (DocumentException ex) {
//            JOptionPane.showMessageDialog(null, "Khong goi duoc document !");
            ex.printStackTrace();
        }
    }

    public void writeObject(String[] data) {
        Paragraph pdfData = new Paragraph();
        for (int i = 0; i < data.length; i++) {
            pdfData.add(data[i] + "  ");
        }
        try {
            document.add(pdfData);
        } catch (DocumentException ex) {
            Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void writeTable(JTable _table) {
        PdfPTable pdfTable = new PdfPTable(_table.getColumnCount());
        for (int i = 0; i < _table.getRowCount(); i++) {
            for (int j = 0; j < _table.getColumnCount(); j++) {
                String data = String.valueOf(_table.getValueAt(i, j));
                PdfPCell cell = new PdfPCell(new Phrase(data, fontData));
                pdfTable.addCell(cell);
            }
        }
        try {
            document.add(pdfTable);
        } catch (DocumentException ex) {
            JOptionPane.showMessageDialog(null, "Khong goi duoc document !");
        }
    }

    private String getFile() {
        fd.setFile("untitled.pdf");
        fd.setVisible(true);
        String url = fd.getDirectory() + fd.getFile();
        if (url.equals("nullnull")) {
            return null;
        }
        return url;
    }
    
    
    public void writeThongKeSachHong(LocalDate NgayBatDau, LocalDate NgayKetThuc) {
        String url = "";
        try {
            fd.setTitle("Thống kê sách mượn nhiều");
            url = getFile();
            if (url == null) {
                return;
            }
            file = new FileOutputStream(url);
            document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();
            setTitle("Thống kê sách mượn nhiều");
            //Hien thong tin cua hoa don hien tai
            ThongKeSachMuonBUS SachBUS = new ThongKeSachMuonBUS();
            Chunk glue = new Chunk(new VerticalPositionMark());// Khoang trong giua hang
            Paragraph paraMaHoaDon = new Paragraph(new Phrase("Thống kê từ ngày: " + NgayBatDau.toString()+" đến ngày "+ NgayKetThuc.toString(), fontData));
            Paragraph para1 = new Paragraph();
            para1.setFont(fontData);
            para1.add("Ngày lập thống kê: " + getTime());
            para1.add(glue);

            document.add(paraMaHoaDon);
            document.add(para1);
            document.add(Chunk.NEWLINE);//add hang trong de tao khoang cach
            //Tao table cho cac chi tiet cua hoa don
            PdfPTable pdfTable = new PdfPTable(6);

            //Set headers cho table chi tiet
            pdfTable.addCell(new PdfPCell(new Phrase("Mã phiếu mượn", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Mã thể loại", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Tên sách", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Mã đọc giả", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Mã tác giả", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Ngày Lập", fontHeader)));
            for (int i = 0; i < 6; i++) {
                pdfTable.addCell(new PdfPCell(new Phrase("")));
            }

            //Truyen thong tin tung chi tiet vao table
            for (ThongKeSachHongDTO cthd : SachBUS.getSachHong(NgayBatDau,NgayKetThuc)) {
                String IDPhieuMuon = cthd.getIDPhieuMuon();
                String IDTheLoai = cthd.getIDTheLoai();
                String TenSach = cthd.getTenSach();
                String IDMember = cthd.getIDMember();
                String IDTacGia =cthd.getIDTacGia();
                String NgayLap =cthd.getNgayLap().toString();

                pdfTable.addCell(new PdfPCell(new Phrase(IDPhieuMuon, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(IDTheLoai, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(TenSach, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(IDMember, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(IDTacGia, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(NgayLap, fontData)));
            }

            document.add(pdfTable);
            document.add(Chunk.NEWLINE);
            document.close();
            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + url);

        } catch (DocumentException | FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi ghi file " + url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void writeThongKeSachMuonNhieu(LocalDate NgayBatDau, LocalDate NgayKetThuc) {
        String url = "";
        try {
            fd.setTitle("Thống kê sách mượn nhiều");
            url = getFile();
            if (url == null) {
                return;
            }
            file = new FileOutputStream(url);
            document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();
            setTitle("Thống kê sách mượn nhiều");
            //Hien thong tin cua hoa don hien tai
            ThongKeSachMuonBUS SachBUS = new ThongKeSachMuonBUS();
            Chunk glue = new Chunk(new VerticalPositionMark());// Khoang trong giua hang
            Paragraph paraMaHoaDon = new Paragraph(new Phrase("Thống kê từ ngày: " + NgayBatDau.toString()+" đến ngày "+ NgayKetThuc.toString(), fontData));
            Paragraph para1 = new Paragraph();
            para1.setFont(fontData);
            para1.add("Ngày lập thống kê: " + getTime());
            para1.add(glue);

            document.add(paraMaHoaDon);
            document.add(para1);
            document.add(Chunk.NEWLINE);//add hang trong de tao khoang cach
            //Tao table cho cac chi tiet cua hoa don
            PdfPTable pdfTable = new PdfPTable(6);

            //Set headers cho table chi tiet
            pdfTable.addCell(new PdfPCell(new Phrase("Mã sách", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Tên sách", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Mã tác giả", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Mã thể loại", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Số lượng tồn", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Số lương mượn", fontHeader)));
            for (int i = 0; i < 6; i++) {
                pdfTable.addCell(new PdfPCell(new Phrase("")));
            }

            //Truyen thong tin tung chi tiet vao table
            for (ThongKeSachMuonDTO cthd : SachBUS.getSachMuonNhieu(NgayBatDau,NgayKetThuc)) {
                String IDSach = cthd.getIDSach();
                String TenSach = cthd.getTenSach();
                String IDTacGia = cthd.getIDTacGia();
                String IDTheLoai = cthd.getIDTheLoai();
                String SoLuongTon =String.valueOf(cthd.getSoLuongTon());
                String SoLanMuon =String.valueOf(cthd.getSoLanMuon());

                pdfTable.addCell(new PdfPCell(new Phrase(IDSach, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(TenSach, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(IDTacGia, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(IDTheLoai, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(SoLuongTon, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(SoLanMuon, fontData)));
            }

            document.add(pdfTable);
            document.add(Chunk.NEWLINE);
            document.close();
            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + url);

        } catch (DocumentException | FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi ghi file " + url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void writeThongKeSachMuonIt(LocalDate NgayBatDau, LocalDate NgayKetThuc) {
        String url = "";
        try {
            fd.setTitle("Thống kê sách mượn Ít");
            url = getFile();
            if (url == null) {
                return;
            }
            file = new FileOutputStream(url);
            document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();
            setTitle("Thống kê sách mượn Ít");
            //Hien thong tin cua hoa don hien tai
            ThongKeSachMuonBUS SachBUS = new ThongKeSachMuonBUS();
            Chunk glue = new Chunk(new VerticalPositionMark());// Khoang trong giua hang
            Paragraph paraMaHoaDon = new Paragraph(new Phrase("Thống kê từ ngày: " + NgayBatDau.toString()+" đến ngày "+ NgayKetThuc.toString(), fontData));
            Paragraph para1 = new Paragraph();
            para1.setFont(fontData);
            para1.add("Ngày lập thống kê: " + getTime());
            para1.add(glue);

            document.add(paraMaHoaDon);
            document.add(para1);
            document.add(Chunk.NEWLINE);//add hang trong de tao khoang cach
            //Tao table cho cac chi tiet cua hoa don
            PdfPTable pdfTable = new PdfPTable(6);

            //Set headers cho table chi tiet
            pdfTable.addCell(new PdfPCell(new Phrase("Mã sách", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Tên sách", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Mã tác giả", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Mã thể loại", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Số lượng tồn", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Số lương mượn", fontHeader)));
            for (int i = 0; i < 6; i++) {
                pdfTable.addCell(new PdfPCell(new Phrase("")));
            }

            //Truyen thong tin tung chi tiet vao table
            for (ThongKeSachMuonDTO cthd : SachBUS.getSachMuonIt(NgayBatDau,NgayKetThuc)) {
                String IDSach = cthd.getIDSach();
                String TenSach = cthd.getTenSach();
                String IDTacGia = cthd.getIDTacGia();
                String IDTheLoai = cthd.getIDTheLoai();
                String SoLuongTon =String.valueOf(cthd.getSoLuongTon());
                String SoLanMuon =String.valueOf(cthd.getSoLanMuon());

                pdfTable.addCell(new PdfPCell(new Phrase(IDSach, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(TenSach, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(IDTacGia, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(IDTheLoai, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(SoLuongTon, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(SoLanMuon, fontData)));
            }

            document.add(pdfTable);
            document.add(Chunk.NEWLINE);
            document.close();
            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + url);

        } catch (DocumentException | FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi ghi file " + url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void writeXuatPhieuMuon(bookIssueDTO phieuMuon) {
        String url = "";
        try {
            fd.setTitle("Phiếu mượn");
            url = getFile();
            if (url == null) {
                return;
            }
            file = new FileOutputStream(url);
            document = new Document();
            bookIssueDAO bookDAO= new bookIssueDAO();
            Book book= new Book();
            PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();
            setTitle("Phiếu mượn");
            //Hien thong tin cua hoa don hien tai
            ThongKeSachMuonBUS SachBUS = new ThongKeSachMuonBUS();
            Chunk glue = new Chunk(new VerticalPositionMark());// Khoang trong giua hang
            Paragraph paraMaHoaDon = new Paragraph(new Phrase("Mã hoá đơn: "+phieuMuon.getIDPhieuMuon(), fontData));
            Paragraph para1 = new Paragraph();
            para1.setFont(fontData);
            para1.add("Ngày lập Phiếu: " + getTime());
            para1.add(glue);

            Paragraph para2 = new Paragraph();
            para2.setFont(fontData);
            para2.add("Mã Nhân viên: " + phieuMuon.getIDNhanVien());
            para2.add(Chunk.NEWLINE);//add hang trong de tao khoang cach
            para2.add("Mã Độc giả: " + phieuMuon.getIDThanhVien());
            para2.add(glue);
            document.add(paraMaHoaDon);
            document.add(para1);
            document.add(para2);
            document.add(Chunk.NEWLINE);//add hang trong de tao khoang cach
            //Tao table cho cac chi tiet cua hoa don
            PdfPTable pdfTable = new PdfPTable(4);

            //Set headers cho table chi tiet
            pdfTable.addCell(new PdfPCell(new Phrase("Mã sách", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Ngày mượn", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Ngày Trả", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Ghi chú", fontHeader)));
            for (int i = 0; i < 4; i++) {
                pdfTable.addCell(new PdfPCell(new Phrase("")));
            }

            //Truyen thong tin tung chi tiet vao table

                pdfTable.addCell(new PdfPCell(new Phrase(phieuMuon.getIDSach(), fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(phieuMuon.getNgayMuon().toString(), fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(phieuMuon.getNgayTra().toString(), fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(phieuMuon.getGhiChu(), fontData)));
            document.add(pdfTable);
            document.add(Chunk.NEWLINE);
            document.close();
            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + url);

        } catch (DocumentException | FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi ghi file " + url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
    }
    public void closeFile() {
        document.close();
    } 
}
