/** Represents a product that the warehouse stocks. */
public class product {
        private int id;         //!< unique identifier
        private String name;    //!< name of product
        private String desc;    //!< optional extra details
        private double price;   //!< warehouse sale price
        private int quantity;   //!< quantity in stock in the warehouse

        /** Construct a new product.
         * @param[in]   id      unique identifier for this product
         * @pre         id MUST be unique
         * @param[in]   name    name of product
         * @param[in]   desc    extra details to describe product
         * @post        price and quantity are set to 0 */
        public product(int id,
                       String name,
                       String desc) {
                this.id = id;
                this.name = name;
                this.desc = desc;
                this.price = 0.0;
                this.quantity = 0;
        }

        /** Obtain the unique identifier describing this product .
         * @return      unique identifier of product */
        public int get_id() {
                return id;
        }

        /** Obtain the name of the product.
         * @return      product name string */
        public String get_name() {
                return name;
        }

        /** Obtain the description of the product.
         * @return      product description string */
        public String get_desc() {
                return desc;
        }

        /** Obtain the warehouse sale price of the product.
         * @return      product sale price */
        public double get_price() {
                return price;
        }

        /** Obtain the quantity of this product in stock in the warehouse.
         * @return      quantity in stock */
        public int get_quantity() {
                return quantity;
        }

        /** Modify the name of the product.
         * @param[in]   new_name        string of new name for product
         * @return      always returns true */
        public boolean set_name(String new_name) {
                name = new_name;
                return true;
        }

        /** Modify the description of the product.
         * @param[in]   new_desc        string of new description for product
         * @return      always returns true */
        public boolean set_desc(String new_desc) {
                desc = new_desc;
                return true;
        }

        /** Modify the price of the product.
         * @param[in]   new_price       the price to change the product to
         * @pre         new_price must be >=0.0
         * @post        price is not modified if this returns false
         * @return      false if price is below 0, true otherwise */
        public boolean set_price(double new_price) {
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
        public boolean increase_quantity(int quant2inc) {
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
        public boolean decrease_quantity(int quant2dec) {
                if ((quant2dec > 0) && (quant2dec <= quantity)) {
                        quantity -= quant2dec;
                        return true;
                } else {
                        return false;
                }
        }

        /** Create a string representing the product object.
         * @return      the string */
        public String toString() {
                return "ID: " + get_id() + "\nName: " + get_name()
                       + "\nDescription: " + get_desc() + "\nPrice: "
                       + get_price() + "\nQuantity: " + get_quantity() + "\n";
        }
}
