module com.example.thuvienvuive {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;
    requires java.desktop;
    requires poi;
    requires itextpdf;

    opens thuvienvuive.Login to javafx.fxml;
    exports thuvienvuive.Login;
    opens thuvienvuive to javafx.fxml;
    exports thuvienvuive;
    opens thuvienvuive.Author to javafx.fxml;
    exports thuvienvuive.Author;
    opens thuvienvuive.Book to javafx.fxml;
    exports thuvienvuive.Book;
    opens thuvienvuive.User to javafx.fxml;
    exports thuvienvuive.User;
    opens thuvienvuive.Dashboard.Admin to javafx.fxml;
    exports thuvienvuive.Dashboard.Admin;
    opens thuvienvuive.Dashboard.User to javafx.fxml;
    exports thuvienvuive.Dashboard.User;
    opens thuvienvuive.Genre to javafx.fxml;
    exports thuvienvuive.Genre;
    opens thuvienvuive.Member to javafx.fxml;
    exports thuvienvuive.Member;
    opens thuvienvuive.ThongKe to javafx.fxml;
    exports thuvienvuive.ThongKe;
}