
public class Supplier {
  
  private String name;
  private String address;
  private int phone;
  private int id;
  private int quantity;  
  private String product;
  private double price;
  public  Supplier (String name, String address, int phone, int id, String product, int quantity, double price) {
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.id = id;
    this.product=product;
    this.quantity=quantity;
    this.price=price;
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
  public String getProduct(){
      return product;
  }
  public int getQuantity() {
    return quantity;
  }
  public double getPrice(){
    return price;
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
// to change the product
    public boolean setProduct(String newProduct){
        product = newProduct;
        return true;
    }
 // to change the quantity
    public boolean setQuantity(int newQuantity){
      if (newQuantity>=0){  
      quantity = newQuantity;
      return true;
      }
      else{
      return false;}
    }
    public boolean setPrice(double newPrice){
      if (newPrice>=0 && newPrice!=price){
        price = newPrice;
        return true;
      }
      else{
        return false;
      }
    }
  
 

  public String toString() {
    return "ID:" + getId() + "\nSupplier name: " + getName() + "\naddress " + getAddress()+ "\nphone " + getPhone() + "\nproduct name" + getProduct() + "\n product quantity" + getQuantity() + "\n price" + getPrice() ;
   
  }
}
