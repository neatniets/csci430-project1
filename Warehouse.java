import java.util.*;
import java.io.*;
public class Warehouse implements Serializable {
        private static final long serialVersionUID = 1L;

        /** List of all products stocked by warehouse. */
        private ProductList plist;
        /** List of all clients tracked by warehouse. */
        private ClientList clist;
        /** List of all active suppliers for the warehouse. */
        private SupplierList slist;
        private static Warehouse warehouse = null;
        private Warehouse() {
                plist = new ProductList();
                clist = ClientList.instance();
                slist = new SupplierList();
        }
        public static Warehouse instance() {
                /*if (warehouse == null) {
                        MemberIdServer.instance(); // instantiate all singletons
                        return (warehouse = new Warehouse());
                } else {
                        return warehouse;
                }*/
                if (warehouse == null) {
                        warehouse = new Warehouse();
                }
                return warehouse;
        }

        /** Add a product to warehouse.
         * @param[in]   product_id      unique identifier for product
         * @post        will fail if product_id is not unique
         * @param[in]   name            name of product
         * @param[in]   price           price of product
         * @param[in]   quantity        quantity of product
         * @return      reference to Product object on success,
         *              null on failure */
        public Product addProduct(String name,
                                  double price,
                                  int quantity) {
                /* create product */
                Product p = new Product(name, price, quantity);
                /* add product */
                if (plist.add(p)) { // success
                        return p;
                } else { // fail
                        return null;
                }
        }
        /** Get an iterator for all products.
         * @return      the iterator */
        public Iterator<Product> getProducts() {
                return plist.iterator();
        }
        /** Get a specific product.
         * @param       pid     ID of product
         * @return      product object with that ID on success,
         *              null if the product doesn't exist */
        public Product getProduct(String pid) {
                return plist.find(pid);
        }
        /** Update the price of a product.
         * @param       pid     ID of product to update
         * @param       price   price to change product to
         * @post        returned product object should be checked to see if
         *              price successfully changed
         * @return      product object found,
         *              null if product could not be found */
        public Product updateProductPrice(String pid,
                                          double price) {
                /* try to find product */
                Product p = plist.find(pid);
                if (p == null) { // no product found
                        return null;
                }
                p.setPrice(price);
                return p;
        }

        public Iterator<Supply> getSuppliersForProduct(String pid) {
                Product product = plist.find(pid);
                return product.getSuppliersOfProduct();
        }

        public Iterator<Supply> getSuppliersProducts(String sid) {
                Supplier supplier = slist.find(sid);
                return supplier.getProductsSold();
        }

        public void addSupply(String pid, String sid, double price) {
                Product product = plist.find(pid);
                Supplier supplier = slist.find(sid);
                Supply supply = new Supply(sid, pid, price);
                supplier.insertProductSold(supply);
                product.addSupplytoList(supply);
        }

        public Supplier addSupplier(String name,
                                    String address,
                                    String phone) {
                /* create supplier */
                Supplier s = new Supplier(name, address, phone);
                /* add supplier */
                if (slist.add(s)) { // success
                        return s;
                } else { // fail
                        return null;
                }
        }
        public Iterator<Supplier> getSuppliers() {
                return slist.iterator();
        }
        public Supplier getSupplier(String sid) {
                return slist.find(sid);
        }
        public Supplier updateSupplierInfo(String sid,
                                           String name,
                                           String address,
                                           String phone) {
                /* try to find supplier */
                Supplier s = slist.find(sid);
                if (s == null) { // no supplier found
                        return null;
                }
                s.setName(name);
                s.setAddress(address);
                s.setPhone(phone);
                return s;
        }

        public Transaction processOrder(String client_id) {
          Client c = clist.getClient(client_id);
          /* make sure client exists */
          if (c != null ) { // exists
            /* process order */
            return c.processOrder();
          } else { // does not exist
            return null;
          }
        }

        public Iterator<WaitlistEntry> getWaitlist(String prod_id) {
          Product p = plist.find(prod_id);
          if (p == null) {
            return null;
          } else {
            return p.getWaitlist();
          }
        }

        /* Matt got a working ID generator */
        public Client addClient(String name,
                                String address,
                                String phone) {
                /* create client */
                Client c = new Client(name, address, phone);
                /* add client */
                if (clist.insertClient(c)) { // success
                        return c;
                } else { // fail
                        return null;
                }
        }
        public Iterator<Client> getClients() {
                return clist.getClients();
        }
        public Client getClient(String client_id) {
                return clist.getClient(client_id);
        }
        public Client updateClientInfo(String client_id,
                                       String name,
                                       String address,
                                       String phone) {
                /* try to find client */
                Client c = clist.getClient(client_id);
                if (c == null) { // no client found
                        return null;
                }
                c.setName(name);
                c.setAddress(address);
                c.setPhone(phone);
                return c;
        }

        public boolean add2ShoppingCart(String client_id,
                                        String product_id,
                                        int quantity) {
                /* find client */
                Client c = clist.getClient(client_id);
                if (c == null) { // client not found
                        return false;
                }
                /* find product */
                Product p = plist.find(product_id);
                if (p == null) { // product not found
                        return false;
                }
                /* add product to cart */
                return c.getShoppingCart().insertProduct(p, quantity);
        }
        public Iterator<CartItem> getCartContents(String client_id) {
                /* try to find client */
                Client c = clist.getClient(client_id);
                if (c == null) { // client not found
                        return null;
                }
                /* return cart's iterator */
                return c.getShoppingCart().getProducts();
        }

        public static Warehouse retrieve() {
                try {
                        FileInputStream file = new FileInputStream("WarehouseData");
                        ObjectInputStream input = new ObjectInputStream(file);
                        input.readObject();
                        //MemberIdServer.retrieve(input);
                        return warehouse;
                } catch(IOException ioe) {
                        ioe.printStackTrace();
                        return null;
                } catch(ClassNotFoundException cnfe) {
                        cnfe.printStackTrace();
                        return null;
                }
        }
        public static  boolean save() {
                try {
                        FileOutputStream file = new FileOutputStream("WarehouseData");
                        ObjectOutputStream output = new ObjectOutputStream(file);
                        output.writeObject(warehouse);
                        //output.writeObject(MemberIdServer.instance());
                        return true;
                } catch(IOException ioe) {
                        ioe.printStackTrace();
                        return false;
                }
        }
        private void writeObject(java.io.ObjectOutputStream output) {
                try {
                        output.defaultWriteObject();
                        output.writeObject(warehouse);
                } catch(IOException ioe) {
                        System.out.println(ioe);
                }
        }
        private void readObject(java.io.ObjectInputStream input) {
                try {
                        input.defaultReadObject();
                        if (warehouse == null) {
                                warehouse = (Warehouse) input.readObject();
                        } else {
                                input.readObject();
                        }
                } catch(IOException ioe) {
                        ioe.printStackTrace();
                } catch(Exception e) {
                        e.printStackTrace();
                }
        }
        public String toString() {
                return plist + "\n" + clist + "\n" + slist + "\n";
        }
}
