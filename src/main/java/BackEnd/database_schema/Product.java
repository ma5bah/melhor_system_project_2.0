package BackEnd.database_schema;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.logging.Level;

import BackEnd.CommonTask;
import database.db;

public class Product {

  private long id;
  private String name;
  private String category;
  private long quantity;
  private double price;
  private Timestamp expiry_date;
  private long storage_id;
  private double need_space;

  public Product(ResultSet rs) {
    try {
      this.setId(rs.getLong("id"));
      this.setCategory(rs.getString("category"));
      this.setName(rs.getString("name"));
      this.setPrice(rs.getDouble("price"));
      this.setStorageId(rs.getLong("storage_id"));
      this.setNeedSpace(rs.getDouble("need_space"));
      this.setQuantity(rs.getLong("quantity"));
      this.setExpiryDate(rs.getTimestamp("expiry_date"));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public static Product create_product(
      String product_name,
      String product_category,
      Double product_price,
      int product_need_space,
      int product_quatity,
      Timestamp product_expiry_date,
      int product_storage) {
    try {
      Connection source = db.get_connection();
      PreparedStatement st = source.prepareStatement(
          "INSERT INTO `Product` (`name`,`category`,`quantity`,`price`,`need_space`,`expiry_date`,`storage_id`) VALUES (?,?,?,?,?,?,?)");
      st.setString(1, product_name);
      st.setString(2, product_category);
      st.setInt(3, product_quatity);
      st.setDouble(4, product_price);
      st.setInt(5, product_need_space);
      st.setDate(6, new Date(product_expiry_date.getTime()));
      st.setInt(7, product_storage);
      ResultSet rs = st.executeQuery();
      rs.next();
      return new Product(rs);
    } catch (SQLException ex) {
      CommonTask.log(Level.SEVERE, ex, ex.getMessage());
    }
    return null;
  }

  public static Product get_all_product() {
    try {
      Connection source = db.get_connection();
      PreparedStatement st = source.prepareStatement(
          "SELECT * FROM `Product`");
      ResultSet rs = st.executeQuery();
      while (rs.next()) {
        System.out.println(new Product(rs).toString());
      }
    } catch (SQLException ex) {
      CommonTask.log(Level.SEVERE, ex, ex.getMessage());
    }
    return null;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public long getQuantity() {
    return quantity;
  }

  public void setQuantity(long quantity) {
    this.quantity = quantity;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public java.sql.Timestamp getExpiryDate() {
    return expiry_date;
  }

  public void setExpiryDate(java.sql.Timestamp expiry_date) {
    this.expiry_date = expiry_date;
  }

  public long getStorageId() {
    return storage_id;
  }

  public void setStorageId(long storage_id) {
    this.storage_id = storage_id;
  }

  public double getNeedSpace() {
    return need_space;
  }

  public void setNeedSpace(double need_space) {
    this.need_space = need_space;
  }

}
