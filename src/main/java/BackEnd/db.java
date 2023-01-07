package BackEnd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class db {

    public static final String URL = "jdbc:postgresql://192.168.0.107:5432/";
    public static final String DB_NAME = "test";
    public static final String USERNAME = "user";
    public static final String PASSWORD = "password";
    
    public static Connection source = null;

    db(){
        getConnections();
    }
    public void finalize(){
        closeConnections();
    }
    
    public static void main(String[] args) {
        getConnections();
        
        
        
        
        login("masbah@gmail.com","123456");
    }
    
    
     /*
     * INSERT INTO "public"."Inventory" ("id","name") VALUES ($1,$2) RETURNING
     * "public"."Inventory"."id"
     * 
     * INSERT INTO "public"."Employee"
     * ("email","password","contact","role","inventory_id") VALUES ($1,$2,$3,$4,$5)
     * RETURNING "public"."Employee"."id"
     * ["masbah@gmail.com","123456","01311807889","manager",0]
     * 
     * 
     * SELECT "public"."Employee"."id", "public"."Employee"."email",
     * "public"."Employee"."password", "public"."Employee"."contact",
     * "public"."Employee"."role", "public"."Employee"."inventory_id" FROM
     * "public"."Employee" WHERE ("public"."Employee"."email" = $1 AND
     * "public"."Employee"."password" = $2) LIMIT $3 OFFSET $4
     * ["masbah@gmail.com","123456",1,0]
     */
    public static boolean login(String email, String password) {
        try {
            PreparedStatement st = source.prepareStatement(
                    "SELECT \"public\".\"Employee\".\"id\", \"public\".\"Employee\".\"email\", \"public\".\"Employee\".\"role\" "
                            +
                            " FROM \"public\".\"Employee\" WHERE (\"public\".\"Employee\".\"email\" = ? AND \"public\".\"Employee\".\"password\" = ?)");

            st.setString(1, "masbah@gmail.com");
            st.setString(2, "123456");
            ResultSet rs = st.executeQuery();
            // while (rs.next()) {
            //     System.out.print("Column 1 returned ");
            //     System.out.println(rs.getString(1));
            // }
            // while(rs.next()){
            //     System.out.println(rs.getString(1));
            // }
            System.out.println(rs.getRow());
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(db.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    
    
    
    
    public static Connection getConnections() {
        if (source != null) {
            return source;
        }
        try {
            // Class.forName("com.mysql.cj.jdbc.Driver");
            // source = DriverManager.getConnection(URL + DB_NAME, USERNAME, PASSWORD);
            // return source;

            Properties props = new Properties();
            props.setProperty("user", USERNAME);
            props.setProperty("password", PASSWORD);
//            props.setProperty("ssl", "true");
            return source = DriverManager.getConnection(URL + DB_NAME, props);
        } catch (SQLException e) {
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
            if (source == null) {
                getConnections();
            }
            if (source == null) {
                return;
            }
            Statement s = source.createStatement();
            ResultSet res = s.executeQuery("SELECT * FROM `product`");
            System.out.println(res.getRow());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

   

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
