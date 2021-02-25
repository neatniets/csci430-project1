/** Represents a Product that the warehouse stocks. */
public class Product {
        private int id;         //!< unique identifier
        private String name;    //!< name of Product
        private double price;   //!< warehouse sale price
        private int quantity;   //!< quantity in stock in the warehouse

        /** Construct a new Product.
         * @param[in]   id      unique identifier for this Product
         * @pre         id MUST be unique
         * @param[in]   name    name of Product
         * @post        price and quantity are set to 0 */
        public Product(int id,
                       String name) {
                this(id, name, 0.0, 0);
        }
        /** Construct a new Product.
         * @param[in]   id              unique identifier for this Product
         * @pre         id MUST be unique
         * @param[in]   name            name of Product
         * @param[in]   price           price of product
         * @param[in]   quantity        quantity of product */
        public Product(int id,
                       String name,
                       double price,
                       int quantity) {
                this.id = id;
                this.name = name;
                this.price = price;
                this.quantity = quantity;
        }

        /** Obtain the unique identifier describing this Product .
         * @return      unique identifier of Product */
        public int get_id() {
                return id;
        }

        /** Obtain the name of the Product.
         * @return      Product name string */
        public String get_name() {
                return name;
        }

        /** Obtain the warehouse sale price of the Product.
         * @return      Product sale price */
        public double get_price() {
                return price;
        }

        /** Obtain the quantity of this Product in stock in the warehouse.
         * @return      quantity in stock */
        public int get_quantity() {
                return quantity;
        }

        /** Modify the name of the Product.
         * @param[in]   new_name        string of new name for Product
         * @return      always returns true */
        public boolean set_name(String new_name) {
                name = new_name;
                return true;
        }

        /** Modify the price of the Product.
         * @param[in]   new_price       the price to change the Product to
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

        /** Create a string representing the Product object.
         * @return      the string */
        public String toString() {
                return "ID: " + get_id() + "\nName: " + get_name()
                       + "\nPrice: " + get_price() + "\nQuantity: "
                       + get_quantity() + "\n";
        }
}
