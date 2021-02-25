/** Class representing a waitlist entry for a client waiting for some number of
 * a product */
public class WaitlistEntry {
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
}
