import java.util.TreeMap;
import java.util.Iterator;

/** Represents a Product that the warehouse stocks. */
public class Product {
        private static final String PROD_STRING = "P";

        private String id;      //!< unique identifier
        private String name;    //!< name of Product
        private double price;   //!< warehouse sale price
        private int quantity;   //!< quantity in stock in the warehouse
        /** A list of clients waiting for this product; may contain entries
         * which have a quantity of 0 */
        private TreeMap<String, WaitlistEntry> waitlist;

        /** Construct a new Product.
         * @param[in]   name    name of Product
         * @post        price and quantity are set to 0 */
        public Product(String name) {
                this(name, 0.0, 0);
        }
        /** Construct a new Product.
         * @param[in]   name            name of Product
         * @param[in]   price           price of product
         * @param[in]   quantity        quantity of product */
        public Product(String name,
                       double price,
                       int quantity) {
                this.id = PROD_STRING + (ProductIdServer.instance()).getId();
                this.name = name;
                this.price = price;
                this.quantity = quantity;
                waitlist = new TreeMap<String, WaitlistEntry>();
        }

        /** Obtain the unique identifier describing this Product .
         * @return      unique identifier of Product */
        public String getId() {
                return id;
        }

        /** Obtain the name of the Product.
         * @return      Product name string */
        public String getName() {
                return name;
        }

        /** Obtain the warehouse sale price of the Product.
         * @return      Product sale price */
        public double getPrice() {
                return price;
        }

        /** Obtain the quantity of this Product in stock in the warehouse.
         * @return      quantity in stock */
        public int getQuantity() {
                return quantity;
        }

        /** Modify the name of the Product.
         * @param[in]   new_name        string of new name for Product
         * @return      always returns true */
        public boolean setName(String new_name) {
                name = new_name;
                return true;
        }

        /** Modify the price of the Product.
         * @param[in]   new_price       the price to change the Product to
         * @pre         new_price must be >=0.0
         * @post        price is not modified if this returns false
         * @return      false if price is below 0, true otherwise */
        public boolean setPrice(double new_price) {
                if (new_price >= 0.0) {
                        price = new_price;
                        return true;
                } else {
                        return false;
                }
        }

        /** Increase the quantity in stock.
         * @param[in]   quant2inc       number to increase quantity by
         * @pre         quant2inc must be >0
         * @post        quantity is not modified if this returns false
         * @return      false if quant2inc <=0, true otherwise */
        public boolean increaseQuantity(int quant2inc) {
                if (quant2inc > 0) {
                        quantity += quant2inc;
                        return true;
                } else {
                        return false;
                }
        }

        /** Decrease the quantity in stock.
         * @param[in]   quant2dec       number to decrease quantity by
         * @pre         quant2dec must be >0 and not more than current quantity
         * @post        quantity is not modified if this returns false
         * @return      false if preconditions failed, true otherwise */
        public boolean decreaseQuantity(int quant2dec) {
                if ((quant2dec > 0) && (quant2dec <= quantity)) {
                        quantity -= quant2dec;
                        return true;
                } else {
                        return false;
                }
        }

        /** Get an iterator for the waitlist of this product. */
        public Iterator<WaitlistEntry> getWaitlist() {
                return waitlist.values().iterator();
        }

        /** Get a waitlist entry for a certain client.
         * @param[in]   client_id       ID of the client
         * @return      entry associated with that client if found, otherwise
         *              null if not found */
        public WaitlistEntry getWaitlistEntry(String client_id) {
                return waitlist.get(client_id);
        }

        /** Add to the waitlist.
         * @param[in]   entry   the item to add
         * @post        entry will be added to the internal waitlist */
        public void addWaitlistEntry(WaitlistEntry entry) {
                waitlist.put(entry.getClientId(), entry);
        }

        /** Check if any clients are on the waitlist.
         * @return      true if there are clients waiting on this product; else
         *              false */
        public boolean hasWaitlist() {
                /* get iterator */
                Iterator<WaitlistEntry> iter = getWaitlist();
                /* check each entry for a quantity greater than zero */
                while (iter.hasNext()) {
                        if (iter.next().getQuantity() > 0) {
                                /* a client is waiting for this product */
                                return true;
                        }
                }
                /* no entries with qty > 0 */
                return false;
        }

        /** Create a string representing the Product object.
         * @return      the string */
        public String toString() {
                return "ID: " + getId() + "\nName: " + getName()
                       + "\nPrice: " + getPrice() + "\nQuantity: "
                       + getQuantity() + "\n";
        }
}
