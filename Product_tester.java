import java.util.Iterator;
public class Product_tester {
        public static void main(String[] args) {
                Product milk1 = new Product(3, "milk", "skim");
                Product milk2 = new Product(1, "milk", "2%");
                Product chocolate = new Product(2, "chocolate", "dark");
                /* intentionally uses the same ID as milk1 to show error */
                Product eggs = new Product(3, "eggs", "1 dozen");

                System.out.println("Printing all Products.");
                System.out.print("milk1:\n" + milk1
                                 + "milk2:\n" + milk2
                                 + "chocolate:\n" + chocolate
                                 + "eggs:\n" + eggs);
                System.out.println();

                System.out.println("Adding all Products to list");
                ProductList list = new ProductList();
                System.out.println("Attempting to add milk1 to list returned: "
                                   + list.add(milk1) + " (should be true)");
                System.out.println("Attempting to add milk2 to list returned: "
                                   + list.add(milk2) + " (should be true)");
                System.out.println("Attempting to add chocolate to list "
                                   + "returned: " + list.add(chocolate)
                                   + " (should be true)");
                System.out.println("Attempting to add eggs to list returned: "
                                   + list.add(eggs) + " (should be false)");
                System.out.println();

                System.out.println("Iterating over whole list");
                Iterator<Product> iter = list.iterator();
                while (iter.hasNext()) {
                        System.out.print(iter.next());
                }
                System.out.println();

                System.out.println("Try finding Products");
                int id1 = 1,
                    id2 = 4;
                System.out.print("Searching for " + id1 + " returned:\n"
                                 + list.find(id1)
                                 + "(should find something)\n");
                System.out.print("Searching for " + id2 + " returned:\n"
                                 + list.find(id2) + "\n(should find null)\n");
                System.out.println();

                System.out.println("Changing prices");
                int id3 = 2;
                double price1 = 27.35,
                       price2 = -3.6;
                System.out.println("Changing the price of ID " + id3 + " to "
                                   + price1 + " returned: "
                                   + list.find(id3).set_price(price1)
                                   + " (should be true)");
                System.out.print(list.find(id3));
                System.out.println("Changing the price of ID " + id3 + " to "
                                   + price2 + " returned: "
                                   + list.find(id3).set_price(price2)
                                   + " (should be false)");
                System.out.print(list.find(id3));
                System.out.println();

                System.out.println("Increasing quantity");
                int id4 = 3;
                int[] inc = {12, -3, 0};
                for (int i = 0; i < inc.length; i++) {
                        System.out.println("Attempting to increase quantity "
                                           + "of ID " + id4 + " by " + inc[i]
                                           + " returned: "
                                           + list.find(id4)
                                             .increase_quantity(inc[i])
                                           + " (should be " + (inc[i] > 0)
                                           + ")");
                        System.out.print(list.find(id4));
                }
                System.out.println();

                System.out.println("Decreasing quantity");
                int[] dec = {24, -8, 0, 3, 5};
                for (int i = 0; i < dec.length; i++) {
                        int tmp = list.find(id4).get_quantity();
                        System.out.println("Attempting to decrease quantity "
                                           + "of ID " + id4 + " by " + dec[i]
                                           + " returned: "
                                           + list.find(id4)
                                             .decrease_quantity(dec[i])
                                           + " (should be "
                                           + ((dec[i] > 0) && (dec[i] <= tmp))
                                           + ")");
                        System.out.print(list.find(id4));
                }
                System.out.println();
        }
}
