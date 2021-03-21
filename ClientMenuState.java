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

        private void addItem() {
                String pid = null;
                /* get valid product ID */
                boolean is_pid_valid = false;
                while (!is_pid_valid) {
                        /* get token */
                        pid = getToken("Enter product ID: ");
                        /* check token */
                        if (warehouse.getProduct(pid) == null) { // invalid
                                System.out.println("Invalid pid.");
                        } else { // valid
                                is_pid_valid = true;
                        }
                }

                /* get qty & try to add */
                int qty;
                boolean is_qty_valid = false;
                while (!is_qty_valid) {
                        /* get qty */
                        qty = getNumber("Enter qty to add: ");
                        /* try to add */
                        if (!warehouse.add2ShoppingCart(context.getClientId(),
                                                        pid, qty)) { // error
                                System.out.println("Invalid qty.");
                        } else { // success
                                is_qty_valid = true;
                        }
                }
                /* return cuz already added */
        }

        private void modifyItem() {
                String pid = null;
                /* get cart item & product id */
                boolean is_pid_valid = false;
                while (!is_pid_valid) {
                        /* get token */
                        pid = getToken("Enter product ID: ");
                        /* check if cart item exists */
                        if (warehouse.getCartItem(context.getClientId(), pid)
                            == null) { // invalid
                                System.out.println("Invalid pid.");
                        } else { // valid
                                is_pid_valid = true;
                        }
                }

                /* get qty & try to modify */
                int qty;
                boolean is_qty_valid = false;
                while (!is_qty_valid) {
                        /* get qty */
                        qty = getNumber("Enter qty to change to: ");
                        /* try to modify */
                        if (!warehouse.setCartItemQty(context.getClientId(),
                                                      pid, qty)) { // error
                                System.out.println("Invalid qty.");
                        } else { // success
                                is_qty_valid = true;
                        }
                }
                /* return cuz already modified */
        }
        private void removeItem() {
                CartItem item = null;
                String pid = null;
                /* get cart item & product id */
                boolean is_pid_valid = false;
                while (!is_pid_valid) {
                        /* get token */
                        pid = getToken("Enter product ID: ");
                        /* check if cart item exists */
                        item = warehouse.getCartItem(context.getClientId(),
                                                     pid);
                        if (item == null) { // invalid
                                System.out.println("Invalid pid.");
                        } else { // valid
                                is_pid_valid = true;
                        }
                }

                /* remove all from cart */
                warehouse.removeFromCart(context.getClientId(), pid,
                                         item.getQuantity());
        }
        private void placeOrder() {
                Transaction t = warehouse.processOrder(context.getClientId());
                System.out.println(t.toString());
        }

        private enum CartCmds {
                EXIT,
                PRINT_CART,
                ADD_ITEM,
                MOD_ITEM,
                RM_ITEM,
                PLACE_ORDER,
                HELP
        };
        private static String[] cart_cmd_strings = {
                " to exit.",
                " to print the cart.",
                " to add an item.",
                " to modify an item.",
                " to remove an item.",
                " to place an order of this cart.",
                " to see this help."
        };
        private void editCart() {
                CartCmds cmd = CartCmds.HELP;
                do {
                        switch (cmd) {
                        case PRINT_CART:
                                printCart();
                                break;
                        case ADD_ITEM:
                                addItem();
                                break;
                        case MOD_ITEM:
                                modifyItem();
                                break;
                        case RM_ITEM:
                                removeItem();
                                break;
                        case PLACE_ORDER:
                                placeOrder();
                                break;
                        case HELP:
                                printHelp(CartCmds.values(), cart_cmd_strings);
                                break;
                        default:
                                System.err.println("Error in switch");
                                System.exit(1);
                        }
                } while ((cmd = getCommand(CartCmds.values(),
                                           CartCmds.HELP.ordinal()))
                         != CartCmds.EXIT);
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
