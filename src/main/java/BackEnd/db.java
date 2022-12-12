package BackEnd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

public class db {

    public static final String DB_NAME = "test";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";

    public static Connection source = null;

    public static Connection getConnections() {
        if (source != null) {
            return source;
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            source = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DB_NAME, USERNAME, PASSWORD);
            return source;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void set_product_data() {
        try {
            PreparedStatement psmt = source.prepareStatement(
                    "INSERT INTO product (id, name, order_date, delivery_date, status, owner_id, description) VALUES (?,?,?,?,?,?,?)");
            psmt.setInt(1, 1);
            psmt.setString(2, "book");
            long time = new SimpleDateFormat("yyyy-MM-dd").parse("2001-10-04").getTime();
            psmt.setDate(3, new java.sql.Date(time));
            psmt.setDate(4, new java.sql.Date(time));
            psmt.setString(5, "pending");
            psmt.setInt(6, 10);
            psmt.setString(7, "good");
            psmt.execute();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void get_product_data() {
        try {
            if (source == null)
                getConnections();
            Statement s = source.createStatement();
            ResultSet res = s.executeQuery("SELECT * FROM `product`");
            System.out.println(res.getRow());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // public static Connection getConnections() {
    // if (source != null) {
    // return source;
    // }
    // try {
    // Class.forName("com.mysql.cj.jdbc.Driver");
    // source = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DB_NAME,
    // USERNAME, PASSWORD);
    // return source;
    // } catch (ClassNotFoundException | SQLException e) {
    // e.printStackTrace();
    // }
    // return null;
    // }
    public boolean checkConnections() {
        return source != null;
    }

    public static void closeConnections() {
        if (source != null) {
            try {
                source.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
