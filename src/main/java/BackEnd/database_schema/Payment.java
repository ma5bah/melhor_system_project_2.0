package BackEnd.database_schema;


public class Payment {

  private long id;
  private String transectionId;
  private double totalAmount;
  private String medium;
  private long orderId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getTransectionId() {
    return transectionId;
  }

  public void setTransectionId(String transectionId) {
    this.transectionId = transectionId;
  }


  public double getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(double totalAmount) {
    this.totalAmount = totalAmount;
  }


  public String getMedium() {
    return medium;
  }

  public void setMedium(String medium) {
    this.medium = medium;
  }


  public long getOrderId() {
    return orderId;
  }

  public void setOrderId(long orderId) {
    this.orderId = orderId;
  }

}
