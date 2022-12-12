package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import com.mysql.cj.jdbc.Driver;

public class db {
    private static Connection source = null;
    // public db() {
    // try {
    // // This will load the MySQL driver, each DB has its own driver
    // Class.forName("com.mysql.cj.jdbc.Driver");
    // // Setup the source with the DB

    // source = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?" +
    // "user=user&password=password");
    // System.out.println(source.getMetaData());
    // // preparedStatement= source.prepareStatement("describe user");
    // // writeMetaData(preparedStatement.executeQuery());
    // } catch (Exception e) {
    // System.out.println(e);
    // }
    // }
    public static Connection get_connection() {
        try {
            if (source != null)
                return source;
            Class.forName("com.mysql.jdbc.Driver");
            source = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?", "user", "password");
            return source;
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("ConnectionUtil : " + ex.getMessage());
            return null;
        }
    }
}
