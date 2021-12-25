package thuvienvuive.ThongKe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ThongKeSachMuonBUS {
    public ObservableList<ThongKeSachMuonDTO> getSachMuonIt(LocalDate NgayBatDau, LocalDate NgayKetThuc) throws Exception {
        ObservableList<ThongKeSachMuonDTO> ListSachMuonIt = FXCollections.observableArrayList();
        ThongKeDAO ThongKe= new ThongKeDAO();
        ListSachMuonIt=ThongKe.SachMuonItDAO(NgayBatDau,NgayKetThuc);
        return ListSachMuonIt;
    }
    public ObservableList<ThongKeSachMuonDTO> getSachMuonNhieu(LocalDate NgayBatDau, LocalDate NgayKetThuc) throws Exception {
        ObservableList<ThongKeSachMuonDTO> ListSachMuonNhieu = FXCollections.observableArrayList();
        ThongKeDAO ThongKe= new ThongKeDAO();
        ListSachMuonNhieu=ThongKe.SachMuonNhieuDAO(NgayBatDau,NgayKetThuc);
        return ListSachMuonNhieu;
    }
    public ObservableList<ThongKeSachHongDTO> getSachHong(LocalDate NgayBatDau, LocalDate NgayKetThuc) throws Exception {
        ObservableList<ThongKeSachHongDTO> ListSachHong = FXCollections.observableArrayList();
        ThongKeDAO ThongKe= new ThongKeDAO();
        ListSachHong=ThongKe.SachHongDAO(NgayBatDau,NgayKetThuc);
        return ListSachHong;
    }
}
