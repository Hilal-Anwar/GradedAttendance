module org.graded_classes.graded_attendance {
    requires javafx.fxml;
    requires telegrambots;
    requires telegrambots.meta;
    requires org.xerial.sqlitejdbc;
    requires org.slf4j.nop;
    requires java.sql;
    requires atlantafx.base;
    requires com.twelvemonkeys.common.image;
    requires org.apache.xmlgraphics.batik.transcoder;
    exports org.graded_classes.graded_attendance;
    opens org.graded_classes.graded_attendance;
    opens org.graded_classes.graded_attendance.controller to javafx.fxml, javafx.graphics, org.xerial.sqlitejdbc, java.sql;
    opens org.graded_classes.graded_attendance.data to java.sql, org.xerial.sqlitejdbc;
    opens org.graded_classes.graded_attendance.messaging;
}