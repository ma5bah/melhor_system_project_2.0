package BackEnd.database_schema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

import BackEnd.CommonTask;
import BackEnd.db;

public class Order {
  private ArrayList<Long> product_ids;
  private long id;
  private String type;
  private String coupen;
  private String status;
  private long inventoryId;
  public Order() {
  }
  public void add_product_in_order(long _id){
    product_ids.add(_id);
  }
//   [Nest] 14132  - 01/18/2023, 11:01:11 PM   DEBUG [PARAM] [1]
// [Nest] 14132  - 01/18/2023, 11:01:11 PM     LOG [QUERY] INSERT INTO `test`.`Order` (`id`,`type`,`coupen`,`status`,`inventory_id`) VALUES (?,?,?,?,?)
// [Nest] 14132  - 01/18/2023, 11:01:11 PM   DEBUG [PARAM] [null,"pending1"," ","pending2",1]
// [Nest] 14132  - 01/18/2023, 11:01:11 PM     LOG [QUERY] INSERT INTO `test`.`ProductAndOrder` (`order_id`,`product_id`,`product_quantity`) VALUES (?,?,?), (?,?,?)     
// [Nest] 14132  - 01/18/2023, 11:01:11 PM   DEBUG [PARAM] [2,3,10.00000000000000,2,2,10.00000000000000]
// [Nest] 14132  - 01/18/2023, 11:01:11 PM     LOG [QUERY] SELECT `test`.`Order`.`id`, `test`.`Order`.`type`, `test`.`Order`.`coupen`, `test`.`Order`.`status`, `test`.`Order`.`inventory_id` FROM `test`.`Order` WHERE `test`.`Order`.`id` = ? LIMIT ? OFFSET ?
// [Nest] 14132  - 01/18/2023, 11:01:11 PM   DEBUG [PARAM] [2,1,0]
// [Nest] 14132  - 01/18/2023, 11:01:11 PM     LOG [QUERY] COMMIT
// [Nest] 14132  - 01/18/2023, 11:01:11 PM   DEBUG [PARAM] []
  
  public static boolean create_order(
      String type,
      String coupen) {
    try {
      Connection source = db.makeConnections();
      PreparedStatement st = source.prepareStatement(
          "INSERT INTO `Order` (`type`,`category`,`quantity`,`price`,`need_space`,`expiry_date`,`storage_id`) VALUES (?,?,?,?,?,?,?)");

      return st.execute();
    } catch (SQLException ex) {
      CommonTask.log(Level.SEVERE, ex, ex.getMessage());
    }
    return false;
  }

 
  public Order(ResultSet rs) {
    try {
      this.setId(rs.getLong("id"));
      this.setCoupen(rs.getString("coupen"));
      this.setInventoryId(rs.getInt("inventory_id"));
      this.setStatus(rs.getString("status"));
      this.setType(rs.getString("type"));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
  public static ArrayList<Order> get_all_order(){
    try {
      Connection source = db.makeConnections();
      PreparedStatement st = source.prepareStatement(
          "SELECT * FROM `Order`");
      ResultSet rs = st.executeQuery();
      ArrayList<Order> list=new ArrayList<Order>();
      
      while (rs.next()) {
        list.add(new Order(rs));
      }
      return list;
    } catch (SQLException ex) {
      CommonTask.log(Level.SEVERE, ex, ex.getMessage());
    }
    return null;
  }
  public static int count_all_order(){
    try {
      Connection source = db.makeConnections();
      PreparedStatement st = source.prepareStatement(
          "SELECT count(*) as count FROM `Order`");
      ResultSet rs = st.executeQuery();
      rs.next();
      return rs.getInt("count");

    } catch (SQLException ex) {
      CommonTask.log(Level.SEVERE, ex, ex.getMessage());
    }
    return 0;
  }
  // public static boolean create_order(
  //     String order_name,
  //     String order_category,
  //     Double order_price,
  //     int order_need_space,
  //     int order_storage) {
  //   try {
  //     Connection source = db.makeConnections();
  //     PreparedStatement st = source.prepareStatement(
  //         "INSERT INTO `Product` (`name`,`category`,`quantity`,`price`,`need_space`,`expiry_date`,`storage_id`) VALUES (?,?,?,?,?,?,?)");
  //     st.setString(1, product_name);
  //     st.setString(2, product_category);
  //     st.setInt(3, product_quantity);
  //     st.setDouble(4, product_price);
  //     st.setInt(5, product_need_space);
  //     st.setDate(6, Date.valueOf(product_expiry_date.toLocalDate()));
  //     st.setInt(7, product_storage);
  //     return st.execute();
  //   } catch (SQLException ex) {
  //     CommonTask.log(Level.SEVERE, ex, ex.getMessage());
  //   }
  //   return false;
  // }
  
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public String getCoupen() {
    return coupen;
  }

  public void setCoupen(String coupen) {
    this.coupen = coupen;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public long getInventoryId() {
    return inventoryId;
  }

  public void setInventoryId(long inventoryId) {
    this.inventoryId = inventoryId;
  }

  @Override
  public String toString() {
    return "Order [id=" + id + ", type=" + type + ", coupen=" + coupen + ", status=" + status + ", inventoryId="
        + inventoryId + "]";
  }

}
