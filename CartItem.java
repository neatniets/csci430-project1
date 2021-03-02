import java.util.*;
import java.io.*;
  public class CartItem {
  private String quantity;
  private Product product;
  public CartItem(Product product, String quantity) {
    this.product = product;
    this.quantity = quantity;
  }
  public String getQuantity() {
    return quantity;
  }

  public Product getProduct() {
    return product;
  }

  public void setQuantity(String newquantity) {
    quantity = newquantity;
  }
  public void setproductid(Product new_product) {
          product = new_product;
  }
}
