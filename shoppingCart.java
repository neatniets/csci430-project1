import java.util.*;
import java.lang.*;
import java.io.*;
public class shoppingCart implements Serializable {
  private static final long serialVersionUID = 1L;
  private List products = new LinkedList();
  public shoppingCart() {
  }
  public boolean insertProduct(Product product) {
    products.add(product);
    return true;
  }

  public boolean removeProduct(Product product) {
    products.remove(product);
    return true;
  }

  public Iterator getProducts() {
    return products.iterator();
  }

  public void emptyContents() {
    /* dummy */
  }

  private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      //output.writeObject(cart);
    } catch(IOException ioe) {
      System.out.println(ioe);
    }
  }
  private void readObject(java.io.ObjectInputStream input) {
    /*try {
      if (cart != null) {
        return;
      } else {
        input.defaultReadObject();
        if (cart == null) {
          cart = (shoppingCart) input.readObject();
        } else {
          input.readObject();
        }
      }
    } catch(IOException ioe) {
      System.out.println("in shoppingCart readObject \n" + ioe);
    } catch(ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
    }*/
  }
  public String toString() {
    return products.toString();
  }
}
