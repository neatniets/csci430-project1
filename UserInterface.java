import java.util.*;
import java.text.*;
import java.io.*;
public class UserInterface {
  private static UserInterface userInterface;
  private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private static Warehouse warehouse;
  private static final int EXIT = 0;
  private static final int ADD_CLIENT = 1;
  private static final int ADD_PRODUCT = 2;
  private static final int ADD_SUPPLIER = 3;
  private static final int LIST_ALL_PRODUCTS = 4;
  private static final int LIST_ALL_SUPPLIERS= 5;
  private static final int LIST_ALL_CLIENTS = 6;
  private static final int PRINT_SUPPLIER_PERSONAL_INFORMATION = 7;
  private static final int PRINT_SPECIFIC_CLIENTS_PERSONAL_INFORMATION = 8;
  private static final int PRINT_INFORMATION_ON_SPECIFIC_PRODUCT = 9;
  private static final int UPTADE_CLIENTS_PERSONAL_INFORMATION = 10;
  private static final int UPDTE_SUPPLIERS_PERSONAL_INFORMATION = 11;
  private static final int UPDATE_PRODUCTS_PRICE = 12;
  private static final int ADD_ITEM_TO_THE_SHOPPING_CART = 13;
  private static final int PRINT_A_CLIENTS_SHOPPING_CART = 14;
  private static final int PROCESS_ORDER = 15;
  private static final int LIST_PRODUCTS_W_WAITLIST = 16;
  private static final int PRINT_WAITLIST = 17;
  private static final int PRINT_SUPPLIERS_OF_PRODUCT = 18;
  private static final int PRINT_A_SUPPLIERS_PRODUCTS = 19;
  private static final int ADD_PRODUCT_TO_SUPPLIER = 20;
  private static final int List_All_Transactions_For_A_Client = 21;
  private static final int List_All_Clients_With_Outstanding_Balance = 22;
  private static final int Update_Suppliers_Price_For_product = 23;
  private static final int HELP = 24;
  
  //Constructor
  private UserInterface() {
      warehouse = Warehouse.instance();
    }

  //UserInterface instance provides window for user interactions.
  public static UserInterface instance() {
    if (userInterface == null) {
      return userInterface = new UserInterface();
    } else {
      return userInterface;
    }

  }

  //Function to get user's input.
  public String getToken(String prompt) {
    do {
      try {
        System.out.println(prompt);
        String line = reader.readLine();
        StringTokenizer tokenizer = new StringTokenizer(line,"\n\r\f");
        if (tokenizer.hasMoreTokens()) {
          return tokenizer.nextToken();
        }

      } catch (IOException ioe) {
        System.exit(0);
      }

    } while (true);

  }

  //Function to get user's input for yes or no questions.
  private boolean yesOrNo(String prompt) {
    String more = getToken(prompt + " (Y|y)[es] or anything else for no");
    if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
      return false;
    }

    return true;
  }

  //Function to get user's input when the input is a number.
  public int getNumber(String prompt) {
    do {
      try {
        String item = getToken(prompt);
        Integer num = Integer.valueOf(item);
        return num.intValue();
      } catch (NumberFormatException nfe) {
        System.out.println("Please input a number ");
      }

    } while (true);

  }


  //Function to get the command a user has chosen from the interactive menu.
  public int getCommand() {
    do {
      try {
        int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
        if (value >= EXIT && value <= HELP) {
          return value;
        }

      } catch (NumberFormatException nfe) {
        System.out.println("Enter a number");
      }

    } while (true);

  }

  //Function to show the interactive menu to the user.
  public void help() {
    System.out.println("Enter a number between 0 and 18 as explained below:");
    System.out.println(EXIT + " to Exit");
    System.out.println(ADD_CLIENT + " to add a client.");
    System.out.println(ADD_PRODUCT + " to add products.");
    System.out.println(ADD_SUPPLIER + " to add Supplier.");
    System.out.println(LIST_ALL_PRODUCTS + " to print a list of all the products.");
    System.out.println(LIST_ALL_SUPPLIERS + " to print a list of all the suppliers.");
    System.out.println(LIST_ALL_CLIENTS + " to print a list of all the clients. ");
    System.out.println(PRINT_SUPPLIER_PERSONAL_INFORMATION + " to print a supplier's personal information.");
    System.out.println(PRINT_SPECIFIC_CLIENTS_PERSONAL_INFORMATION + " to print a client's personal information.");
    System.out.println(PRINT_INFORMATION_ON_SPECIFIC_PRODUCT + " to print a product's information.");
    System.out.println(UPTADE_CLIENTS_PERSONAL_INFORMATION + " to update a client's personal information.");
    System.out.println(UPDTE_SUPPLIERS_PERSONAL_INFORMATION + " to update a supplier's personal information.");
    System.out.println(UPDATE_PRODUCTS_PRICE + " to update price of a product.");
    System.out.println(ADD_ITEM_TO_THE_SHOPPING_CART + " to add an item to the shopping cart.");
    System.out.println(PRINT_A_CLIENTS_SHOPPING_CART + " to print a client's shopping cart");
    System.out.println(PROCESS_ORDER + " to place an order w/ shopping cart.");
    System.out.println(LIST_PRODUCTS_W_WAITLIST + " to list all products that clients are waiting on.");
    System.out.println(PRINT_WAITLIST + " to print the waitlist for a product.");
	System.out.println(PRINT_SUPPLIERS_OF_PRODUCT + "to print all the suppliers of a product");
	System.out.println(PRINT_A_SUPPLIERS_PRODUCTS + "to print all of a supplier's products");
	System.out.println(ADD_PRODUCT_TO_SUPPLIER + "to add a product a supplier sells");
    System.out.println(List_All_Transactions_For_A_Client + " to print a clients shopping cart");
    System.out.println(List_All_Clients_With_Outstanding_Balance + " to print a clients shopping cart");
    System.out.println(Update_Suppliers_Price_For_product + " to print a clients shopping cart");
    System.out.println(HELP + " for help menu.");
  }

  //Function to add a client to the warehouse.
  public void addClient() {
    String name = getToken("Enter client name:");
    String address = getToken("Enter address:");
    String phone = getToken("Enter phone:");
    //String clientid = getToken("Enter clients ID:");
    Client result;
    result = warehouse.addClient(name, address, phone);
    if (result == null) {
      System.out.println("Could not add client.");
    }

    System.out.println(result);
  }

  //Function to add a product to the warehouse.
  public void addProduct() {
	    Product result;
	    do {
	      String name = getToken("Enter product's name:");
	      double price
                      = Double.parseDouble(getToken("Enter product Price:"));
        int quantity = Integer.parseInt(getToken("Enter Product Quantity:"));
	      result = warehouse.addProduct(name, price, quantity);
	      if (result != null) {
	        System.out.println(result);
	      } else {
	        System.out.println("Product could not be added.");
	      }

	      if (!yesOrNo("Add more products?")) {
	        break;
	      }

	    } while (true);

	  }

  //Function to add a Supplier to the warehouse.
  public void addSupplier() {
	  String name = getToken("Enter Supplier's name:");
	  String address = getToken("Enter address:");
	  String phone = getToken("Enter phone:");
	  Supplier result;
	  result = warehouse.addSupplier(name, address, phone);
	  if (result == null) {
	    System.out.println("Could not add Supplier.");
	  }
	  System.out.println(result);
  }

  // print list of products
  public void ListAllProducts() {
    Iterator allProducts = warehouse.getProducts();
      while (allProducts.hasNext()){
	  Product product = (Product)(allProducts.next());
          System.out.println(product.toString());
      }
  }

  //print list of all suppliers
  public void ListAllSuppliers() {
    Iterator allSuppliers = warehouse.getSuppliers();
      while (allSuppliers.hasNext()){
	  Supplier supplier = (Supplier)(allSuppliers.next());
          System.out.println(supplier.toString());
      }
  }

  //print list of all the products
  public void ListAllClients() {
    Iterator allClients = warehouse.getClients();
      while (allClients.hasNext()){
	  Client client = (Client)(allClients.next());
          System.out.println(client.toString());
      }
  }

  //print specific supplier information

  public void PrintSupplierInformation() {
    Supplier result;
      String id = getToken("enter suppliers id:");
      result = warehouse.getSupplier(id);
      if (result != null){
        System.out.println(result);
      }else {
        System.out.println("supplier not found.");
      }

  }

  //print specipic client information.

  public void PrintSpecificClientsInfo(){
    Client result;
    String id = getToken("enter clients id:");
    result = warehouse.getClient(id);
    if (result != null){
      System.out.println(result);
    }
    else{
      System.out.println("client not found");
    }
  }

  //print specific product information.
  public void PrintInfoAboutSpecificProduct(){
    Product result;
    String id = getToken("enter products id:");
    result = warehouse.getProduct(id);
    if (result != null){
      System.out.println(result);
    }
    else{
      System.out.println("product not found");
    }
  }

  public void UpdateClientsInformation() {
    /* request ID */
    String id = getToken("Client ID: ");
    /* find client */
    Client result = warehouse.getClient(id);
    if (result != null){ // success
      System.out.println(result);
    }
    else{ // fail
      System.out.println("client not found");
      return;
    }

    /* ask for what changes are desired */
    String name;
    String address;
    String phone;
    if (yesOrNo("Change name?")) {
      name = getToken("New name: ");
    } else {
      name = result.getName();
    }
    if (yesOrNo("Change address?")) {
      address = getToken("New address: ");
    } else {
      address = result.getAddress();
    }
    if (yesOrNo("Change phone?")) {
      phone = getToken("New phone: ");
    } else {
      phone = result.getPhone();
    }

    warehouse.updateClientInfo(id, name, address, phone);
    System.out.println(result);
  }

  public void UpdateSuppliersInformation() {
    /* request ID */
    String id = getToken("Supplier ID: ");
    /* find supplier */
    Supplier result = warehouse.getSupplier(id);
    if (result != null){ // success
      System.out.println(result);
    }
    else{ // fail
      System.out.println("supplier not found");
      return;
    }

    /* ask for what changes are desired */
    String name;
    String address;
    String phone;
    if (yesOrNo("Change name?")) {
      name = getToken("New name: ");
    } else {
      name = result.getName();
    }
    if (yesOrNo("Change address?")) {
      address = getToken("New address: ");
    } else {
      address = result.getAddress();
    }
    if (yesOrNo("Change phone?")) {
      phone = getToken("New phone: ");
    } else {
      phone = result.getPhone();
    }

    warehouse.updateSupplierInfo(id, name, address, phone);
    System.out.println(result);
  }

  public void UpdateProductsPrice() {
    /* request ID */
    String id = getToken("Product ID: ");
    /* find product */
    Product result = warehouse.getProduct(id);
    if (result != null){ // success
      System.out.println(result);
    }
    else{ // fail
      System.out.println("product not found");
      return;
    }

    /* ask for what changes are desired */
    double price;
    if (yesOrNo("Change price?")) {
      price = Double.parseDouble(getToken("New price: "));
    } else {
      price = result.getPrice();
    }

    warehouse.updateProductPrice(id, price);
    System.out.println(result);
  }

  public void AddItemToShoppingCart() {
    String client_id = getToken("Client ID: ");
    String product_id = getToken("Product ID: ");
    int quantity = Integer.parseInt(getToken("Quantity: "));
    if (warehouse.add2ShoppingCart(client_id, product_id, quantity)) {
      System.out.println("Successfully added to cart.");
    } else {
      System.out.println("Invalid request.");
    }
  }

  public void PrintClientsShoppingCart() {
    String id = getToken("Client ID: ");
    /* get client's cart contents */
    Iterator<CartItem> iter = warehouse.getCartContents(id);
    if (iter == null) { // client not found
      System.out.println("Client not found");
      return;
    }
    /* print contents */
    System.out.println("ID\t" + "Name\t" + "Quantity");
    while (iter.hasNext()) {
      CartItem item = iter.next();
      Product p = item.getProduct();
      System.out.println(p.getId() + "\t" + p.getName() + "\t"
                         + item.getQuantity());
    }
    System.out.println();
  }

  public void ProcessOrder() {
    String id = getToken("Client ID: ");
    Transaction t = warehouse.processOrder(id);
    if (t == null) {
      System.out.println("Order failed to process.");
    } else {
      System.out.println(t);
    }
  }

  public void ListProductsWithWaitlist() {
    Iterator<Product> iter = warehouse.getProducts();
    while (iter.hasNext()) {
      Product p = iter.next();
      if (p.hasWaitlist()) {
        System.out.println(p.getId());
      }
    }
  }

  public void PrintWaitlist() {
    String id = getToken("Product ID: ");
    Iterator<WaitlistEntry> iter = warehouse.getWaitlist(id);
    while (iter.hasNext()) {
      WaitlistEntry entry = iter.next();
      System.out.print("Client: " + entry.getClientId() + "\n"
                       + "Quantity: " + entry.getQuantity() + "\n");
    }
  }

  public void PrintSuppliersOfProduct() {
	  String id = getToken("Product ID: ");
	  Iterator<Supply> iter = warehouse.getSuppliersForProduct(id);
    if (iter == null) {
      System.out.println("Product not found.");
      return;
    }
	  while (iter.hasNext()) {
		  Supply supply = iter.next();
		  System.out.print("Supplier ID: " + supply.getSupplierID() + "\n"
					+ "Supplier Name: " + warehouse.getSupplier(supply.getSupplierID()).getName() + "\n"
					+ "Supplier's Price: $" + supply.getPrice() + "\n");
	  }
	 }
  
  
  public void PrintSuppliersProducts() {
	  String id = getToken("Supplier ID: ");
	  Iterator<Supply> iter = warehouse.getSuppliersProducts(id);
    if (iter == null) {
      System.out.println("Supplier not found.");
      return;
    }
	  while (iter.hasNext()) {
		Supply supply = iter.next();
                Product product = warehouse.getProduct(supply.getProductID());
		System.out.print("Product ID: " + supply.getProductID() + "\n"
                       + "Name: " + product.getName() + "\n"
		       + "Supplier's Price: $" + supply.getPrice() + "\n");
	  }
	 }

  public void addSupplierProduct() {
	  String sid = getToken("Supplier ID: ");
          String pid = getToken("Product ID: ");
          double price = Double.parseDouble(getToken("Price: "));
	  Supply s = warehouse.addSupply(pid, sid, price);
    if (s == null) {
      System.out.println("Failed to add that product to that supplier.");
    } else {
      System.out.println(s);
    }
	 }

  public void ListAllTransactionsOFAclient() {
  }
  public void ListAllClientsWithOSBalance() {
  }
  public void UpdateSuppliersPriceForAProduct() {
  }

  //Function to invoke the suitable functions according to the user's choice.
  public void process() {
    int command;
    help();
    while ((command = getCommand()) != EXIT) {
      switch (command) {
        case ADD_CLIENT:                                    addClient();
                                break;
        case ADD_PRODUCT:                                   addProduct();
        						break;
        case ADD_SUPPLIER:                                  addSupplier();
                                break;
        case LIST_ALL_PRODUCTS:                             ListAllProducts();
                                break;
        case LIST_ALL_SUPPLIERS:                            ListAllSuppliers();
                                break;
        case LIST_ALL_CLIENTS:                              ListAllClients();
                                break;
        case PRINT_SUPPLIER_PERSONAL_INFORMATION:           PrintSupplierInformation();
                                break;
        case PRINT_SPECIFIC_CLIENTS_PERSONAL_INFORMATION:   PrintSpecificClientsInfo();
                                break;
        case PRINT_INFORMATION_ON_SPECIFIC_PRODUCT:         PrintInfoAboutSpecificProduct();
                                break;
        case UPTADE_CLIENTS_PERSONAL_INFORMATION:           UpdateClientsInformation();
                                break;
        case UPDTE_SUPPLIERS_PERSONAL_INFORMATION:          UpdateSuppliersInformation();
                                break;
        case UPDATE_PRODUCTS_PRICE:                         UpdateProductsPrice();
                                break;
        case ADD_ITEM_TO_THE_SHOPPING_CART:                 AddItemToShoppingCart();
                                break;
        case PRINT_A_CLIENTS_SHOPPING_CART:                 PrintClientsShoppingCart();
                                break;
        case PROCESS_ORDER:									ProcessOrder();
								break;
        case LIST_PRODUCTS_W_WAITLIST:						ListProductsWithWaitlist();
								break;
        case PRINT_WAITLIST:								PrintWaitlist();
								break;
		case PRINT_SUPPLIERS_OF_PRODUCT:					PrintSuppliersOfProduct();
								break;
		case PRINT_A_SUPPLIERS_PRODUCTS:					PrintSuppliersProducts();
								break;
		case ADD_PRODUCT_TO_SUPPLIER:						addSupplierProduct();
								break;
        case List_All_Transactions_For_A_Client:               ListAllTransactionsOFAclient();
                                break;
        case List_All_Clients_With_Outstanding_Balance:       ListAllClientsWithOSBalance();
                                break;
        case Update_Suppliers_Price_For_product:              UpdateSuppliersPriceForAProduct();
                                break;
        case HELP:                                          help();
                                break;

      }

    }

  }

  public static void main(String[] args) {
          UserInterface.instance().process();
  }

}
