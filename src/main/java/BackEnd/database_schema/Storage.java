package BackEnd.database_schema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

import BackEnd.CommonTask;
import BackEnd.db;

public class Storage {

  private long id;
  private String name;
  private String address;
  private long capacity;
  private String category;
  private long inventory_id;

  
    public Storage(ResultSet rs) {
      try {
  
        this.setId(rs.getLong("id"));
        this.setName(rs.getString("name"));
        this.setAddress(rs.getString("address"));
        this.setCapacity(rs.getInt("capacity"));
        this.setInventory_id(rs.getInt("inventory_id"));
  
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
  public static Storage find_storage(String name) {
    try {
      Connection source = db.makeConnections();
      PreparedStatement st = source.prepareStatement("SELECT * FROM `Storage` WHERE (`Storage`.`name` = ?)");
      st.setString(1, name);
      ResultSet rs = st.executeQuery();
      while (rs.next()) {
        if (rs.getString("id") != null) {
          return new Storage(rs);
        }
      }
    } catch (SQLException ex) {
      CommonTask.log(Level.SEVERE, ex, ex.getMessage());
      return null;
    }
    return null;
  }
  public static int count_total_space() {
    try {
      Connection source = db.makeConnections();
      PreparedStatement st = source.prepareStatement(
              "SELECT sum(`Storage`.`capacity`-(SELECT sum(`Product`.`need_space`) FROM `Product` WHERE `Product`.`storage_id`=`Storage`.`id`)) as count FROM `Storage`");
      ResultSet rs = st.executeQuery();
      rs.next();
      return rs.getInt("count");

    } catch (SQLException ex) {
      CommonTask.log(Level.SEVERE, ex, ex.getMessage());
    }
    return 0;
  }
  public static Storage create_storage() {
    try {
      Storage ret=find_storage("main");
      if(ret!=null){
        return ret; 
      }
      Connection source = db.makeConnections();
      PreparedStatement st = source.prepareStatement(
          "INSERT INTO `Storage` (`name`,`address`,`capacity`,`category`,`inventory_id`) VALUES ('main','main',10000000,'grocery',1)");
      st.execute();
      return find_storage("main");

    } catch (SQLException ex) {
      throw new RuntimeException(ex);
    }
  }
  public static ArrayList<Storage> get_all_storage() {
    try {
      Connection source = db.makeConnections();
      PreparedStatement st = source.prepareStatement(
              "SELECT * FROM `Storage`");
      ResultSet rs=st.executeQuery();
      ArrayList<Storage>list=new ArrayList<Storage>();
      while (rs.next()){
        list.add(new Storage(rs));
      }
      return list;

    } catch (SQLException ex) {
      throw new RuntimeException(ex);
    }
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


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public long getCapacity() {
    return capacity;
  }

  public void setCapacity(long capacity) {
    this.capacity = capacity;
  }


  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }


  public long getInventory_id() {
    return inventory_id;
  }

  public void setInventory_id(long inventory_id) {
    this.inventory_id = inventory_id;
  }

}
