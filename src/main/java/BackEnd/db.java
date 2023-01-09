package BackEnd;

import BackEnd.database_schema.Employee;
import BackEnd.database_schema.Inventory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class db {

    public static final String DB_URL = "jdbc:mysql://localhost:3306/";
    public static final String DB_NAME = "test";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";

    public static Connection source = null;

    private static Employee employee = null;
    private static Inventory inventory = null;

    private static boolean isEmployeeAvailable() {
        return employee != null;
    }

    public static Employee getEmployee() {
        return employee;
    }

    public static Inventory getInventory() {
        return inventory;
    }

    // db() {
    // makeConnections();
    // }

    public static void main(String[] args) {
        makeConnections();
        // System.out.println(signup("masbahuddin64@gmail.com", "123456",
        // "01311807889"));
        // login("masbahuddin64@gmail.com", "123456");
        closeConnections();
    }

    /*
     * INSERT INTO `test`.`Storage`
     * (`id`,`name`,`address`,`capacity`,`category`,`inventory_id`) VALUES
     * (?,?,?,?,?,?)
     * 
     * INSERT INTO `test`.`Product`
     * (`id`,`name`,`category`,`quantity`,`price`,`need_space`,`expiry_date`,`
     * storage_id`) VALUES (?,?,?,?,?,?,?,?)
     * [null,"don","general",100,12.00000000000000,1.
     * 000000000000000,"2023-08-04 02:16:32.716 UTC",1]
     */

    public static boolean login(String email, String password) {
        employee = Employee.login( email, password);
        if (employee == null) {
            return false;
        }
        return true;
    }

    public static boolean signup(String email, String password, String contact,String name) {
        employee = Employee.signup( email, password, contact,name);
        if (employee == null) {
            return false;
        }
        return true;
    }

    public static void init_db() {
        makeConnections();
        inventory = Inventory.create_inventory();
    }

    public static Connection makeConnections() {
        if (isConnectionAvailable()) {
            return source;
        }
        try {
            // Properties props = new Properties();
            // props.setProperty("user", USERNAME);
            //// props.setProperty("password", PASSWORD);
            // props.setProperty("tcpKeepAlive","true");
            // props.setProperty("ssl", "true");
            Class.forName("com.mysql.cj.jdbc.Driver");

            // source=DriverManager.getConnection("postgresql://192.168.0.107:5432/test");
            source = DriverManager.getConnection("jdbc:mysql://root@localhost:3306/test");
            System.out.printf("Database is %s \n", !source.isClosed() ? "connected" : "not ok");
            System.out.printf("%s/%s \n", source.getCatalog(), source.getSchema());
            return source;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isConnectionAvailable() {
        return source != null;
    }

    public static void closeConnections() {
        if (source != null) {
            try {
                source.close();
            } catch (SQLException error) {
                error.printStackTrace();
            }
        }
    }

}
