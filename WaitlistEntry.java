import java.io.Serializable;
/** Class representing a waitlist entry for a client waiting for some number of
 * a product */
public class WaitlistEntry implements Serializable {
        private static final long serialVersionUID = 1L;
        String pid;     //!< Product this is for
        String cid;     //!< Client this is for
        int qty;        //!< Quantity of item being waited on

        /** Constructor */
        public WaitlistEntry(String product_id,
                             String client_id,
                             int quantity) {
                pid = product_id;
                cid = client_id;
                qty = quantity;
        }

        /** Product ID getter */
        public String getProductId() {
                return pid;
        }

        /** Client ID getter */
        public String getClientId() {
                return cid;
        }

        /** Quantity getter */
        public int getQuantity() {
                return qty;
        }

        /** Quantity setter */
        public boolean setQuantity(int qty) {
          if (qty < 0) {
            return false;
          } else {
            this.qty = qty;
            return true;
          }
        }

        /** Increase the quantity of this waitlist entry.
         * @param[in]   quantity        qty to increase by
         * @post        object unchanged if this returns false
         * @return      true if quantity > 0; false otherwise */
        public boolean increaseQuantity(int quantity) {
                if (quantity <= 0) {
                        return false;
                } else {
                        qty += quantity;
                        return true;
                }
        }

        /* toString for WaitlistEntry */
        public String toString() {
                String string = "Product ID " + pid + " Client ID " + cid + " Quantity " + qty;
                return string;
        }
}
