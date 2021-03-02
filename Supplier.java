import java.util.LinkedList;
import java.util.Iterator;
public class Supplier {
  
  private String name;
  private String address;
  private int phone;
  private int id;
  private LinkedList productsSold = new LinkedList();

  public boolean insertProductSold(Supply supply) {
    productsSold.add(supply);
    return true;
  }
  public Iterator getProductsSold(){
     return productsSold.iterator();
  }

  public Supplier (String name, String address, int phone, int id) {
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.id = id;
  }

  public String getName() {
    return name;
  }
  public int getPhone() {
    return phone;
  }
  public String getAddress() {
    return address;
  }
  public int getId() {
    return id;
  }
 
//to change the name of the supplier
  public boolean setName(String newName) {
    name = newName;
    return true;
  }
//to change the address of the supplier    
  public boolean setAddress(String newAddress) {
    address = newAddress;
    return true;
  }
//to change the phone number of supplier
  public boolean setPhone(int newPhone) {
    phone = newPhone;
    return true;
  }
 

  public String toString() {
    return "ID:" + getId() + "\nSupplier name: " + getName() + "\naddress " + getAddress()+ "\nphone " + getPhone();
   
  }
}
