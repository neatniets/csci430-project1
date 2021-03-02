import java.util.*;
import java.io.*;
  public class CartItem {
  private int quantity;
  private Product product;
  public CartItem(Product product, int quantity) {
    this.product = product;
    this.quantity = quantity;
  }
  public int getQuantity() {
    return quantity;
  }

  public Product getProduct() {
    return product;
  }

  public void setQuantity(int newquantity) {
    quantity = newquantity;
  }
  public void setproductid(Product new_product) {
          product = new_product;
  }
}
