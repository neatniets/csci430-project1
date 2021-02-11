import java.util.TreeMap;
import java.util.Iterator;
/** Container which holds products stocked by the warehouse. */
public class product_list {
        private TreeMap<Integer, product> map; //!< container for products

        /** Construct a new product list. */
        public product_list() {
                map = new TreeMap<Integer, product>();
        }

        /** Find a product by its ID.
         * @param[in]   prod_id         ID of product being searched for
         * @return      product with that ID or NULL if none exists */
        public product find(int prod_id) {
                return map.get(prod_id);
        }

        /** Add a product to the list.
         * @param[in]   prod    product to be added
         * @pre         prod must not already be in the list
         * @post        list will be unchanged if this returns false
         * @return      false if preconditions are failed, true otherwise */
        public boolean add(product prod) {
                if (map.containsKey(prod.get_id())) {
                        return false;
                } else {
                        map.put(prod.get_id(), prod);
                        return true;
                }
        }

        /** Obtain an iterator for all products.
         * @return      an iterator for all products in the list */
        public Iterator<product> iterator() {
                /* map.values() returns a collection of all the products, then
                 * collection.iterator() obtains the iterator */
                return map.values().iterator();
        }

        /** Create a string representing the product list object.
         * @return      the string */
        public String toString() {
                Iterator<product> iter = iterator();
                String str = new String();
                /* loop through each product and append it to string */
                while (iter.hasNext()) {
                        str += iter.next();
                }
                return str;
        }
}
