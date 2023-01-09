package BackEnd.database_schema;


public class Order {

  private long id;
  private String type;
  private String coupen;
  private String status;
  private long inventoryId;


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

}
