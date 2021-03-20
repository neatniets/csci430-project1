import java.util.Iterator;
public class ClientMenuState extends WarehouseState {
        private static ClientMenuState instance = null;

        private static enum Cmds {
                LOGOUT,
                SHOW_DETAILS,
                LIST_PRODUCTS,
                LIST_TRANSACTIONS,
                EDIT_CART,
                SHOW_WAITLIST,
                HELP
        };
        private static final String[] cmd_strings = {
                " to logout.",
                " to show your details.",
                " to list the warehouse products.",
                " to list your transactions.",
                " to edit your shopping cart.",
                " to show your waitlist.",
                " to get this help text."
        };

        private ClientMenuState() {
                super();
        }
        public static ClientMenuState instance() {
                if (instance == null) {
                        instance = new ClientMenuState();
                }
                return instance;
        }
        public WarehouseState run() {
                System.out.println("Welcome, client " + context.getClientId());
                Cmds cmd = Cmds.HELP;
                do {
                        switch (cmd) {
                        case HELP:
                                printHelp(Cmds.values(), cmd_strings);
                                break;
                        case SHOW_DETAILS:
                                showDetails();
                                break;
                        case LIST_PRODUCTS:
                                listProducts();
                                break;
                        case LIST_TRANSACTIONS:
                                listTransactions();
                                break;
                        case EDIT_CART:
                                editCart();
                                break;
                        case SHOW_WAITLIST:
                                showWaitlist();
                                break;
                        default:
                                System.err.println("Error in switch");
                                System.exit(1);
                        }
                } while ((cmd = getCommand(Cmds.values(), Cmds.HELP.ordinal()))
                         != Cmds.LOGOUT);
                System.out.println("Logging out");

                /* figure out where to return to */
                if (context.getUserType()
                    == WarehouseContext.UserType.CLIENT) {
                        return LoginState.instance();
                } else {
                        return ClerkMenuState.instance();
                }
        }

        private void showDetails() {
                Client c = warehouse.getClient(context.getClientId());
                System.out.println(c);
        }

        private void listProducts() {
                Iterator<Product> iter = warehouse.getProducts();
                String fmt = "%-10s%-10s%10s\n";
                System.out.format(fmt, "ID", "Name", "Price");
                for (int i = 0; i < 30; i++) {
                        System.out.print("-");
                }
                System.out.println();
                while (iter.hasNext()) {
                        Product p = iter.next();
                        System.out.format(fmt, p.getId(), p.getName(),
                                          p.getPrice().toString());
                }
        }

        private void listTransactions() {
                Iterator<Transaction> iter
                        = warehouse.getTransactions(context.getClientId());
                while (iter.hasNext()) {
                        System.out.println(iter.next().toString());
                }
        }

        private void printCart() {
                Money total = new Money();
                Iterator<CartItem> iter
                        = warehouse.getCartContents(context.getClientId());
                /* print header */
                String fmt = "%-10s%-10s%10s%10s%10s\n";
                System.out.format(fmt, "ID", "Name", "Quantity",
                                  "Price", "Total");
                String hrule = "";
                for (int i = 0; i < 50; i++) {
                        hrule += '-';
                }
                System.out.println(hrule);
                /* print each cart item */
                while (iter.hasNext()) {
                        CartItem item = iter.next();
                        if (item.getQuantity() == 0) { // ignore 0 qty
                                continue;
                        }
                        Product p = item.getProduct();
                        Money price = p.getPrice();
                        Money price4all = new Money(price.toValue()
                                                    * item.getQuantity());
                        System.out.format(fmt, p.getId(), p.getName(),
                                          item.getQuantity(), price,
                                          price4all);
                        total.add(price4all);
                }
                System.out.println(hrule);
                /* print total of all items in cart */
                System.out.format(fmt, "Total", "", "", "", total.toString());
        }

        private void editCart() {
                printCart();
        }

        private void showWaitlist() {
                /* get iterator */
                Iterator<WaitlistEntry> iter
                        = warehouse.getClientWaitlist(context.getClientId());
                /* print header */
                String fmt = "%-10s%10s\n";
                System.out.format(fmt, "ID", "Quantity");
                for (int i = 0; i < 20; i++) {
                        System.out.print('-');
                }
                System.out.println();

                /* print non-zero entries */
                while (iter.hasNext()) {
                        WaitlistEntry we = iter.next();
                        if (we.getQuantity() > 0) {
                                System.out.format(fmt, we.getProductId(),
                                                  we.getQuantity());
                        }
                }
        }
}
