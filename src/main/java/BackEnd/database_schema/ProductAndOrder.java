package BackEnd.database_schema;


public class ProductAndOrder {

  private double productQuantity;
  private long productId;
  private long orderId;


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
