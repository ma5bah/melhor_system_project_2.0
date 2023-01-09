package BackEnd.database_schema;

import javafx.scene.image.Image;
import javafx.util.StringConverter;

import java.io.File;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lab.melhor.App;

import BackEnd.CommonTask;
import BackEnd.db;

public class Employee extends StringConverter {

    private long id;
    private String email;
    private String password;
    private String contact;
    private String role;
    private long inventory_id;
    private String name;
    private String dp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDp() {
        return dp;
    }

    public void setDp(String dp) {
        this.dp = dp;
        
    }

    public Employee(ResultSet rs) {
        try {
            // ResultSetMetaData metaData = rs.getMetaData();
            // int total_rows = metaData.getColumnCount();
            // for (int i = 1; i <= total_rows; i++) {
            // String columnLabel = metaData.getColumnLabel(i);
            // }
            this.setId(rs.getLong("id"));
            this.setRole(rs.getString("role"));
            this.setInventoryId(rs.getLong("inventory_id"));
            this.setEmail(rs.getString("email"));
            this.setPassword(rs.getString("password"));
            this.setContact(rs.getString("contact"));
            this.setDp(rs.getString("dp"));
            this.setName(rs.getString("name"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", contact='" + contact + '\'' +
                ", role='" + role + '\'' +
                ", inventory_id=" + inventory_id +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getInventoryId() {
        return inventory_id;
    }

    public void setInventoryId(long inventory_id) {
        this.inventory_id = inventory_id;
    }

    @Override
    public String toString(Object object) {
        return null;
    }

    @Override
    public Object fromString(String string) {
        return null;
    }

    public static Employee login(String email, String password) {
        try {

            java.sql.Connection source = db.makeConnections();
            PreparedStatement st = source.prepareStatement(
                    "SELECT * FROM `Employee` WHERE (`Employee`.`email` = ? AND `Employee`.`password` = ?)");
            st.setString(1, email);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            // System.out.println(rs.getStatement());

            while (rs.next()) {
                return new Employee(rs);
            }
            return null;

            // rs.close();
            // st.close();
        } catch (Exception ex) {
            CommonTask.log(Level.SEVERE, ex, null);
        }
        return null;
    }

    public static Employee signup(String email, String password, String contact, String name) {
        try {
            java.sql.Connection source = db.makeConnections();
            PreparedStatement st = source.prepareStatement(
                    "INSERT INTO `Employee` (`email`,`password`,`contact`,`name`,`role`,`dp`,`inventory_id`) VALUES (?,?,?,?,'manager','src/main/resources/assets/picture/default.png',?)");
            st.setString(1, email);
            st.setString(2, password);
            st.setString(3, contact);
            st.setString(4, name);
            st.setLong(5, db.getInventory().getId());
            st.execute();
            st.close();
            return login(email, password);

        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) {

                CommonTask.log(Level.SEVERE, ex, ex.getMessage());
            } else {
                CommonTask.log(Level.SEVERE, ex, ex.getMessage());
            }
            return null;
        }
    }

}

// switch (metaData.getColumnLabel(i)) {
// case "id": {
// this.setId(rs.getLong(i));
// }
// case "role": {
// this.setRole(rs.getString(i));
// }
// case "inventory_id": {
// this.setInventoryId(rs.getLong(i));
// }
// case "email": {
// this.setEmail(rs.getString(i));
// }
// case "password": {
// this.setPassword(rs.getString(i));
// }
// case "contact": {
// this.setContact(rs.getString(i));
// }
// default: {
// System.err.println(metaData.getColumnLabel(i) + " column not found");
// }
