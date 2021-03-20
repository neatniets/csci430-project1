import java.util.*;
import java.io.*;
import java.util.TreeMap;
import java.util.Iterator;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Client implements Serializable {
  private static final long serialVersionUID = 1L;
  private String name;
  private String address;
  private String phone;
  private String id;
  private Money balance;
  private static final String Client_STRING = "M";
  private shoppingCart cart = new shoppingCart();
  private LinkedList<Transaction> transactionList;

  public  Client (String name, String address, String phone) {
    this.name = name;
    this.address = address;
    this.phone = phone;
    id = Client_STRING + (ClientIdServer.instance()).getId();
    balance = new Money(0.0);
	transactionList = new LinkedList<Transaction>();
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
  public Money getBalance() {
          return balance;
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
    Transaction tran = new Transaction(new Date().toString(), "", new Money(0.00));
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
        if (shipped_qty > 0) {
          tran.addItemOrder(prod.getId(), shipped_qty, prod.getPrice());
        }
      }
    }
    /* only go through with transaction if something was actually ordered */
    Money total = tran.getTotal();
    if (total.compareTo(0.0) > 0) { // proper order
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
  private void charge2Account(Money amt) {
    balance.add(amt);
  }
  private boolean addTransaction(Transaction t) {
	transactionList.add(t); 
	return true;
  }
  public int fillOrder(String product_id,
                       int max_qty) {
    /* init transaction */
    Transaction t = new Transaction((new Date()).toString(), "", new Money(0.00));
    /* get waitlist entry for order */
    WaitlistEntry entry = Warehouse.instance()
                          .getWaitlistEntry(id, product_id);
    /* get waited on quantity */
    int wait_qty = entry.getQuantity();
    /* determine quantity to ship */
    int order_qty;
    if (max_qty < wait_qty) {
      order_qty = max_qty;
    } else {
      order_qty = wait_qty;
    }

    /* decrease the waitlist quantity */
    entry.setQuantity(wait_qty - order_qty);

    /* get price of product */
    Product p = Warehouse.instance().getProduct(entry.getProductId());
    Money price = p.getPrice();
    /* add to transaction */
    t.addItemOrder(product_id, order_qty, price);
    /* charge amount */
    charge2Account(t.getTotal());
    /* add to list of transactions */
    addTransaction(t);

    /* return remaining quantity */
    return max_qty - order_qty;
  }

  public boolean OutStandingBalance(){
    if(balance.compareTo(0) > 0){
      return true;
    }
    else{
      return false;
    }

  }

  public Iterator<Transaction> getTransactionList() {
    return transactionList.iterator();
  }

public boolean makePayment(Money payment) {
    if ((balance.compareTo(payment) >= 0) && (payment.compareTo(0.00) > 0)) {
      Transaction tr = new Transaction(new Date().toString(), "Payment of " + payment, payment);
      balance.sub(payment);
      addTransaction(tr);
      return true;
    }
    else {
      return false;
    }
  }
  
  public boolean equals(String id) {
    return this.id.equals(id);
  }
  public String toString() {
    String string = "Client name " + name + " address " + address + " id " + id + " phone " + phone;
    return string;
  }
  private void readObject(java.io.ObjectInputStream in)
    throws IOException, ClassNotFoundException {
    /* read the object in the default way */
    in.defaultReadObject();
    /* increment the ID server */
    ClientIdServer.instance().getId();
  }
}
