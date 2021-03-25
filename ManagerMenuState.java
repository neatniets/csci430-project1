/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sirish Puri
 */

import java.util.*;
import java.text.*;
import java.io.*;

public class ManagerMenuState extends WarehouseState {
    // work to be done
    // Become salesclerk
    // Print Suppliers
    // Print suppliers for a product
    //print products for a supplier
    // remove supplier from product
    // add supplier to product
    //modify purchase price for a produt


    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Warehouse warehouse;
    private static ManagerMenuState instance = null;

    //manager specific calls
    private static final int EXIT = IOHelper.EXIT;
    private static final int ADD_PRODUCT = 1;
    private static final int ADD_SUPPLIER = 2;
    private static final int SHOW_SUPPLIER = 3;
    private static final int SHOW_SUPPLIERS_FOR_PRODUCT = 4;
    private static final int SHOW_PRODUCTS_FOR_SUPPLIER = 5;
    private static final int ADD_SUPPLIER_FOR_PRODUCT = 6;
    private static final int MODIFY_PURCHASE_PRICE = 7;
    private static final int SALES_MENU = 8;
    private static final int HELP = IOHelper.HELP;

    private ManagerMenuState() {
        super();
    }

    public static ManagerMenuState instance() {
        if (instance == null) {
            instance = new ManagerMenuState();
        }
        return instance;
    }

    public void addProduct() {
        Product result;
        do {
          String name =IOHelper.getToken("Enter product's name:");
          Money price
            = new Money(Double.parseDouble(IOHelper.getToken("Enter product Price:")));
          int quantity = Integer.parseInt(IOHelper.getToken("Enter Product Quantity:"));
          result = warehouse.addProduct(name, price, quantity);
          if (result != null) {
            System.out.println(result);
          } else {
            System.out.println("Product could not be added.");
          }

          if (!IOHelper.yesOrNo("Add more products?")) {
            break;
          }

        } while (true);

      }

    private void addSupplierforaProduct() {

        do {
            String pId = IOHelper.getToken("Enter product id");
            Product p = warehouse.getProduct(pId);

            if (p != null) {
                String mId = IOHelper.getToken("Enter manufacturer id");
                Supplier m;
                m = warehouse.getSupplier(mId);
                if (m != null) {
                    int price = getNumber("Enter price");
                    warehouse.addSupply(p.getId(), m.getId(), price);
                    System.out.println("Supplier added.");
                    break;
                } else {
                    System.out.println("Could not find manufacturer.");
                }
            } else {
                System.out.println("Product not found.");
            }

            if (!IOHelper.yesOrNo("Try again?")) {
                break;
            }

        } while (true);

    }

    private void addnewSupplier() {
        String name = IOHelper.getToken("Enter manufacturer name");
        String address = IOHelper.getToken("Enter address");
        String phone = IOHelper.getToken("Enter phone");
        Supplier result;
        result = warehouse.addSupplier(name, address, phone);
        if (result == null) {
            System.out.println("Could not add Supplier");
        }
        System.out.println(result);
    }

    public void PrintSuppliersOfProduct() {
        String id = IOHelper.getToken("Product ID: ");
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

    private void showSuppliers() {
        Iterator allSupp = warehouse.getSuppliers();
        while (allSupp.hasNext()) {
        Supplier supplier = (Supplier) (allSupp.next());
            System.out.println(supplier.toString());
        }
    }

    public void PrintSuppliersProducts() {
        String id = IOHelper.getToken("Supplier ID: ");
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

      public void UpdateSuppliersPriceForAProduct() {
        String sid = IOHelper.getToken("Supplier ID: ");
        String pid = IOHelper.getToken("Product ID: ");
        double price = Double.parseDouble(IOHelper.getToken("New price: "));
        Supply s = warehouse.updateSupplyPrice(sid, pid, price);
        if (s == null) {
          System.out.println("The price failed to update.");
        } else {
          System.out.println(s);
        }
      }

    private WarehouseState salesMenu()
    {
       return ClerkMenuState.instance();//go to sales state
    }

    private void help() {
        IOHelper.Println("Enter a number between " + EXIT + " and " + HELP + " as explained below:");
        IOHelper.Println(EXIT + " to Exit\n");
        IOHelper.Println(ADD_PRODUCT + " to add a product");
        IOHelper.Println(ADD_SUPPLIER + " to add Supplier");
        IOHelper.Println(SHOW_SUPPLIER + " to list all supplier");
        IOHelper.Println(SHOW_SUPPLIERS_FOR_PRODUCT + " to  display all supplier for a product");
        IOHelper.Println(SHOW_PRODUCTS_FOR_SUPPLIER + " to  display all products for a supplier");
        IOHelper.Println(ADD_SUPPLIER_FOR_PRODUCT + "to add a supplier for a product");
        IOHelper.Println(MODIFY_PURCHASE_PRICE + "to change the suppliers price for a product");
        IOHelper.Println(SALES_MENU + " to  switch to the Sales Person menu");
        IOHelper.Println(HELP + " for help");
    }

    public WarehouseState run() {
        int command;
        help();
        while ((command = IOHelper.GetCmd()) != EXIT) {
            switch (command) {
                case ADD_PRODUCT: addProduct();
                                break;
                case ADD_SUPPLIER: addnewSupplier();
                                break;
                case SHOW_SUPPLIER: showSuppliers();
                                break;
                case SHOW_SUPPLIERS_FOR_PRODUCT: PrintSuppliersOfProduct();
                                break;
                case SHOW_PRODUCTS_FOR_SUPPLIER: PrintSuppliersProducts();
                                break;
                case ADD_SUPPLIER_FOR_PRODUCT: addSupplierforaProduct();
                                break;
                case MODIFY_PURCHASE_PRICE: UpdateSuppliersPriceForAProduct();
                                break;
                case SALES_MENU: return salesMenu();
                case HELP: help();
                                break;
                default:
                    System.err.println("Error in switch");
                    System.exit(1);
            }
        }
        System.out.println("logging out");
        if (context.getUserType()
                    == WarehouseContext.UserType.MANAGER) {
                        return LoginState.instance();
                } else {
                        return ManagerMenuState.instance();
                }
    }


}
