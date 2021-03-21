import java.util.*;
import java.lang.*;
import java.io.*;
public class shoppingCart implements Serializable {
  private static final long serialVersionUID = 1L;
  private LinkedList<CartItem> contents = new LinkedList<CartItem>();
  public shoppingCart() {
  }
  public boolean insertProduct(Product product, int quantity) {
    /* verify that quantity is non-negative */
    if (quantity <= 0) {
      return false;
    }
    CartItem item = findProduct(product.getId());
    /* create new item if it doesn't exist, else increase the quantity */
    if (item == null) {
      item = new CartItem(product, quantity);
      contents.add(item);
    } else {
      item.setQuantity(item.getQuantity() + quantity);
    }
    return true;
  }

  public boolean setProductQty(Product p, int qty) {
          /* Figure out if item is in cart */
          CartItem item = findProduct(p.getId());
          if (item == null) { // item not in cart; insert instead
                  return insertProduct(p, qty);
          } else { // modify qty
                  /* verify qty non-negative */
                  if (qty < 0) {
                          return false;
                  }
                  /* set qty */
                  item.setQuantity(qty);
                  /* return success */
                  return true;
          }
  }

  public boolean removeProduct(Product product, int quantity) {
    if (quantity <= 0) {
      return false;
    }

    CartItem item = findProduct(product.getId());
    if (item == null) { // product doesn't exist
      return false;
    }

    int cur_qty = item.getQuantity();
    if (quantity > cur_qty) {
      return false;
    }

    if (quantity == cur_qty) {
      contents.remove(item);
    } else {
      item.setQuantity(cur_qty - quantity);
    }
    return true;
  }

  public Iterator<CartItem> getProducts() {
    return contents.iterator();
  }

  public CartItem findProduct(String product_id) {
    Iterator<CartItem> iter = getProducts();
    while (iter.hasNext()) {
      CartItem item = iter.next();
      if (item.getProduct().getId().compareTo(product_id) == 0) {
        return item;
      }
    }
    return null;
  }

  public void emptyContents() {
    contents.clear();
  }

  public String toString() {
    return contents.toString();
  }
}
