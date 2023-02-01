package BackEnd.database_schema;


import BackEnd.CommonTask;
import BackEnd.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

public class Supplier {

  private long id;
  private String username;
  private String companyName;
  private String contact;
  private String address;

  public static int count_supplier() {
    try {
      Connection source = db.makeConnections();
      PreparedStatement st = source.prepareStatement(
              "SELECT count(*) as count FROM `Supplier`");
      ResultSet rs = st.executeQuery();
      rs.next();
      return rs.getInt("count");

    } catch (SQLException ex) {
      CommonTask.log(Level.SEVERE, ex, ex.getMessage());
    }
    return 0;
  }
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }


  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

}
