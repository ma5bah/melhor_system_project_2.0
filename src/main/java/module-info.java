module com.lab.melhor {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
//    requires melhor.system.project;
//    requires org.postgresql.jdbc;
    requires mysql.connector.j;
    requires java.sql;
    requires java.base;
    // requires timingframework;
    // requires miglayout.swing;
   
    opens com.lab.melhor to javafx.fxml;
    exports com.lab.melhor;
}
