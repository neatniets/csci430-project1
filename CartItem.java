import java.util.*;
import java.io.*;
  public class CartItem {
  private String quantity;
  private String Productid;
  public CartItem(String Productid, String quantity) {
    this.Productid= Productid;
    this.quantity = quantity;
  }
  public String getquantity() {
    return quantity;
  }

  public String getProductId() {
    return Productid;
  }

  public void setquantity(String newquantity) {
    quantity = newquantity;
  }
  public void setproductid(String newProductid) {
    Productid = newProductid;
  }
}
