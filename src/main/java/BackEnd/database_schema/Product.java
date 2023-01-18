package BackEnd.database_schema;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale.Category;
import java.util.logging.Level;

import BackEnd.CommonTask;
import BackEnd.db;

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

  public static boolean create_product(
      String product_name,
      String product_category,
      Double product_price,
      int product_need_space,
      int product_quantity,
      LocalDateTime product_expiry_date,
      int product_storage) {
    try {
      Connection source = db.makeConnections();
      PreparedStatement st = source.prepareStatement(
          "INSERT INTO `Product` (`name`,`category`,`quantity`,`price`,`need_space`,`expiry_date`,`storage_id`) VALUES (?,?,?,?,?,?,?)");
      st.setString(1, product_name);
      st.setString(2, product_category);
      st.setInt(3, product_quantity);
      st.setDouble(4, product_price);
      st.setInt(5, product_need_space);
      st.setDate(6, Date.valueOf(product_expiry_date.toLocalDate()));
      st.setInt(7, product_storage);
      return st.execute();
    } catch (SQLException ex) {
      CommonTask.log(Level.SEVERE, ex, ex.getMessage());
    }
    return false;
  }

  public static ArrayList<Product> get_all_product() {
    try {
      Connection source = db.makeConnections();
      PreparedStatement st = source.prepareStatement(
          "SELECT * FROM `Product`");
      ResultSet rs = st.executeQuery();
      ArrayList<Product> list = new ArrayList<Product>();

      while (rs.next()) {
        list.add(new Product(rs));
      }
      return list;
    } catch (SQLException ex) {
      CommonTask.log(Level.SEVERE, ex, ex.getMessage());
    }
    return null;
  }

  public static ArrayList<Product> get_all_product_by_category(String _category) {
    try {
      Connection source = db.makeConnections();
      PreparedStatement st = source.prepareStatement(
          "SELECT * FROM `Product` WHERE `category`=?");
      st.setString(1, _category);
      ResultSet rs = st.executeQuery();
      ArrayList<Product> list = new ArrayList<Product>();

      while (rs.next()) {
        list.add(new Product(rs));
      }
      return list;
    } catch (SQLException ex) {
      CommonTask.log(Level.SEVERE, ex, ex.getMessage());
    }
    return null;
  }
  public static ArrayList<Product> get_all_product_by_name(String _name) {
    try {
      Connection source = db.makeConnections();
      PreparedStatement st = source.prepareStatement(
          "SELECT * FROM `Product` WHERE `name` LIKE '%"+_name+"%';");
      // st.setString(1, _name);
      // System.out.println()
      ResultSet rs = st.executeQuery();
      // System.out.println(rs.getStatement());
      ArrayList<Product> list = new ArrayList<Product>();

      while (rs.next()) {
        // System.out.println(rs.getString("name"));
        list.add(new Product(rs));
      }
      return list;
    } catch (SQLException ex) {
      CommonTask.log(Level.SEVERE, ex, ex.getMessage());
    }
    return null;
  }

  public static ArrayList<String> get_category() {
    ArrayList<String> list = new ArrayList<String>();
    list.add("main");
    list.add("book");
    list.add("python");
    list.add("c++");
    list.add("java");
    list.add("ml");
    list.add("ds");
    return list;
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

  @Override
  public String toString() {
    return "Product [id=" + id + ", name=" + name + ", category=" + category + ", quantity=" + quantity + ", price="
        + price + ", expiry_date=" + expiry_date + ", storage_id=" + storage_id + ", need_space=" + need_space + "]";
  }

}
