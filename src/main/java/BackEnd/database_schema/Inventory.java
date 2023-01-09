package BackEnd.database_schema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import BackEnd.CommonTask;
import BackEnd.db;

public class Inventory {

  private long id;
  private String name;

  public Inventory(ResultSet rs) {
    try {

      this.setId(rs.getLong("id"));
      this.setName(rs.getString("name"));

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public static Inventory find_inventory(String name) {
    try {
      Connection source = db.makeConnections();
      PreparedStatement st = source.prepareStatement("SELECT * FROM `Inventory` WHERE (`Inventory`.`name` = ?)");
      st.setString(1, name);
      ResultSet rs = st.executeQuery();
      while (rs.next()) {
        if (rs.getString("id") != null) {
          return new Inventory(rs);
        }
      }
    } catch (SQLException ex) {
      CommonTask.log(Level.SEVERE, ex, ex.getMessage());
      return null;
    }
    return null;
  }

  public static Inventory create_inventory() {
    try {
      Inventory ret=find_inventory("main");
      if(ret!=null){
        return ret; 
      }
      Connection source = db.makeConnections();
      PreparedStatement st = source.prepareStatement(
          "INSERT INTO `Inventory` (`name`) VALUES ('main')");
      st.execute();
      return find_inventory("main");

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

}
