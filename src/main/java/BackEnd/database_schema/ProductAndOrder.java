package BackEnd.database_schema;


import BackEnd.CommonTask;
import BackEnd.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

public class ProductAndOrder {

  private double productQuantity;
  private long productId;
  private long orderId;
  public ProductAndOrder(ResultSet rs){
    try {
      this.setOrderId(rs.getLong("order_id"));
      this.setProductId(rs.getLong("product_id"));
      this.setProductQuantity(rs.getLong("product_quantity"));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
  public static ArrayList<ProductAndOrder> get_ordered_product(Long _id){
    try {
      Connection source = db.makeConnections();
      PreparedStatement st = source.prepareStatement(
              "SELECT * FROM `ProductAndOrder` WHERE `order_id`=?");
      st.setLong(1, _id);
      ResultSet rs = st.executeQuery();
    ArrayList<ProductAndOrder> list=new ArrayList<ProductAndOrder>();
      while (rs.next()) {
        list.add( new ProductAndOrder(rs));
      }
      return list;
    } catch (SQLException ex) {
      CommonTask.log(Level.SEVERE, ex, ex.getMessage());
    }
    return null;
  }
  public static void create_ordered_product(long order_id,long product_id,long quantity){

    try {
      Connection source = db.makeConnections();
      ResultSet rs=source.prepareStatement("SELECT * FROM `ProductAndOrder` WHERE `order_id`="+ order_id +" AND `product_id`="+ product_id +";").executeQuery();

        if (rs.next()) {
          PreparedStatement tmp_st=source.prepareStatement("UPDATE `ProductAndOrder` SET `product_quantity`=? WHERE `order_id`=? AND `product_id`=?;");
          tmp_st.setLong(1,rs.getLong("product_quantity")+quantity);
          tmp_st.setLong(2,order_id);
          tmp_st.setLong(3,product_id);
          return;
        };




      PreparedStatement st = source.prepareStatement(
              "INSERT INTO `ProductAndOrder`(`order_id`,`product_id`,`product_quantity`) VALUES (?,?,?)");
      st.setLong(1, order_id);
      st.setLong(2,product_id);
      st.setLong(3,quantity);
      st.execute();return;
    } catch (SQLException ex) {
      CommonTask.log(Level.SEVERE, ex, ex.getMessage());
    }
  }
  public double getProductQuantity() {
    return productQuantity;
  }

  public void setProductQuantity(double productQuantity) {
    this.productQuantity = productQuantity;
  }


  public long getProductId() {
    return productId;
  }

  public void setProductId(long productId) {
    this.productId = productId;
  }


  public long getOrderId() {
    return orderId;
  }

  public void setOrderId(long orderId) {
    this.orderId = orderId;
  }

}
