import java.util.*;
import java.io.*;
import java.util.TreeMap;
import java.util.Iterator;

public class Client implements Serializable {
  private static final long serialVersionUID = 1L;
  private String name;
  private String address;
  private String phone;
  private String id;
  private double balance;
  private static final String Client_STRING = "M";
  private shoppingCart cart = new shoppingCart();
  private List transactionList;

  public  Client (String name, String address, String phone) {
    this.name = name;
    this.address = address;
    this.phone = phone;
    id = Client_STRING + (ClientIdServer.instance()).getId();
    balance = 0.0;
	List transactionList = new LinkedList<Transaction>();
  }

  public String getName() {
    return name;
  }
  public String getPhone() {
    return phone;
  }
  public String getAddress() {
    return address;
  }
  public String getId() {
    return id;
  }
  public shoppingCart getShoppingCart() {
    return cart;
  }
  public void setName(String newName) {
    name = newName;
  }
  public void setAddress(String newAddress) {
    address = newAddress;
  }
  public void setPhone(String newPhone) {
    phone = newPhone;
  }
  public Transaction processOrder() {
    /* Initialize a Transaction for an order */
    Transaction tran = new Transaction(new Date().toString(), "", 0.00);
    /* get cart contents */
    Iterator<CartItem> iter = cart.getProducts();
    while (iter.hasNext()) {
      /* get the item from cart */
      CartItem item = iter.next();
      Product prod = item.getProduct();
      int order_qty = item.getQuantity();
      /* place an order for the item */
      if (order_qty > 0) {
        int shipped_qty = prod.orderProduct(this.id, order_qty);
        /* add shipped order to transaction */
        tran.addItemOrder(prod.getId(), shipped_qty, prod.getPrice());
      }
    }
    /* only go through with transaction if something was actually ordered */
    double total = tran.getTotal();
    if (total > 0.0) { // proper order
      /* charge to client account */
      charge2Account(total);
      /* add transaction to list */
      addTransaction(tran);
      /* empty cart contents */
      cart.emptyContents();
      return tran;
    } else { // bad order
      return null;
    }
  }
  private void charge2Account(double amt) {
    this.balance += amt;
  }
  private boolean addTransaction(Transaction t) {
	transactionList.add(t); 
	return true;
  }

  public Iterator getTransactionList() {
    return transactionList.iterator();
  }
  
  public boolean equals(String id) {
    return this.id.equals(id);
  }
  public String toString() {
    String string = "Client name " + name + " address " + address + " id " + id + " phone " + phone;
    return string;
  }
}
