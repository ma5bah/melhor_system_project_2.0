package BackEnd.database_schema;


public class Storage {

  private long id;
  private String name;
  private String address;
  private long capacity;
  private String category;
  private long inventory_id;


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
