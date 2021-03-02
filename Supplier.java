import java.util.LinkedList;
import java.util.Iterator;
public class Supplier {
  private static final long serialVersionUID = 1L;
  private String name;
  private String address;
  private String phone;
  private String id;
  private static final String SUPPLIER_STRING = "S";
  private LinkedList<Supply> productsSold = new LinkedList<Supply>();

  public boolean insertProductSold(Supply supply) {
    productsSold.add(supply);
    return true;
  }
  public Iterator getProductsSold(){
     return productsSold.iterator();
  }

  public  Supplier (String name, String address, String phone) {
    this.name = name;
    this.address = address;
    this.phone = phone;
    id = SUPPLIER_STRING + (SupplierIdServer.instance()).getId();
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
  public boolean setPhone(String newPhone) {
    phone = newPhone;
    return true;
  }
  public boolean equals(String id){
    return this.id.equals(id);
  }
  public String toString() {
    return "ID:" + getId() + "\nSupplier name: " + getName() + "\naddress " + getAddress()+ "\nphone " + getPhone();

  }
}
