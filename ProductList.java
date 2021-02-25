import java.util.TreeMap;
import java.util.Iterator;
/** Container which holds Products stocked by the warehouse. */
public class ProductList {
        private TreeMap<String, Product> map; //!< container for Products

        /** Construct a new Product list. */
        public ProductList() {
                map = new TreeMap<String, Product>();
        }

        /** Find a Product by its ID.
         * @param[in]   prod_id         ID of Product being searched for
         * @return      Product with that ID or NULL if none exists */
        public Product find(String prod_id) {
                return map.get(prod_id);
        }

        /** Add a Product to the list.
         * @param[in]   prod    Product to be added
         * @pre         prod must not already be in the list
         * @post        list will be unchanged if this returns false
         * @return      false if preconditions are failed, true otherwise */
        public boolean add(Product prod) {
                if (map.containsKey(prod.get_id())) {
                        return false;
                } else {
                        map.put(prod.get_id(), prod);
                        return true;
                }
        }

        /** Obtain an iterator for all Products.
         * @return      an iterator for all Products in the list */
        public Iterator<Product> iterator() {
                /* map.values() returns a collection of all the Products, then
                 * collection.iterator() obtains the iterator */
                return map.values().iterator();
        }

        /** Create a string representing the Product list object.
         * @return      the string */
        public String toString() {
                Iterator<Product> iter = iterator();
                String str = new String();
                /* loop through each Product and append it to string */
                while (iter.hasNext()) {
                        str += iter.next();
                }
                return str;
        }
}
