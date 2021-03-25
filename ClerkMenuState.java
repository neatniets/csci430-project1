import java.util.Iterator;

/* TODO: implement class */
public class ClerkMenuState extends WarehouseState {
        private static ClerkMenuState instance = null;

        private static enum Cmds {
                LOGOUT,
                ADD_CLIENT,
                LIST_PRODUCTS,
                LIST_CLIENTS,
                LIST_CLIENTS_WITH_OUTSTANDING,
                BECOME_CLIENT,
                DISPLAY_PRODUCT_WAITLIST,
                RECEIVE_SHIPMENT,
                HELP 
        };
        private static final String[] cmd_strings = {
                " to logout.",
                " to create a new client.",
                " to list the warehouse products.",
                " to list all clients.",
                " to list all clients with an outstanding balance.",
                " to switch to a client view.",
                " to display the waitlist for a product.",
                " to receive a shipment from a supplier.",
                " to get this help text."
        };
  
        private ClerkMenuState() {
                super();
        }
        public static ClerkMenuState instance() {
                if (instance == null) {
                        instance = new ClerkMenuState();
                }
                return instance;
        }
        public WarehouseState run() {
             System.out.println("Welcome, clerk");
                Cmds cmd = Cmds.HELP;
                do {
                        switch (cmd) {
                        case HELP:
                                printHelp(Cmds.values(), cmd_strings);
                                break;
                        case ADD_CLIENT:
                                addClient();
                                break;
                        case LIST_PRODUCTS:
                                listProducts();
                                break;
                        case LIST_CLIENTS:
                                listClients();
                                break;
                        case LIST_CLIENTS_WITH_OUTSTANDING:
                                listClientsWithOutstanding();
                                break;
                        case BECOME_CLIENT:
                                return becomeClient();
                        case DISPLAY_PRODUCT_WAITLIST:
                                displayProductWaitlist();
                                break;
                        case RECEIVE_SHIPMENT:
                                receiveShipment();
                                break;
                        default:
                                System.err.println("Error in switch");
                                System.exit(1);
                        }
                } while ((cmd = getCommand(Cmds.values(), Cmds.HELP.ordinal()))
                         != Cmds.LOGOUT);
                System.out.println("Logging out");

                /* figure out where to return to */
                if (context.getUserType() == WarehouseContext.UserType.CLERK) {
                        return LoginState.instance();
                } else {
                        return ManagerMenuState.instance();
                }
        }

      private void addClient() {
          /* Get client information */
          String name = null;
          name = getToken("Enter client name: ");
         
          String address = null;
          address = getToken("Enter client address: ");
          
          String phone = null;
          phone = getToken("Enter client phone");
          /* create client object */
          Client c = new Client(name, address, phone);
          /* add client */
          warehouse.addClient(name, address, phone);      
        }
        
      
      
      private void listProducts() {
                Iterator<Product> iter = warehouse.getProducts();
                String fmt = "%-10s%-10s%-10s\n";
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
      
      private void listClients() {
                Iterator<Client> iter = warehouse.getClients();
                String fmt = "%-10s%-20s%-20s%-20s%-20s\n";
                System.out.format(fmt, "ID", "Name", "Address", "Phone", "Balance");
                for (int i = 0; i < 90; i++) {
                        System.out.print("-");
                }
                System.out.println();
                while (iter.hasNext()) {
                        Client c = iter.next();
                        System.out.format(fmt, c.getId(), c.getName(),
                                          c.getAddress(), c.getPhone(), c.getBalance().toString());
                }
      }
      
      private void listClientsWithOutstanding() {
           Iterator<Client> iter = warehouse.getClients();
                String fmt = "%-10s%-20s%-20s%-20s%-20s\n";
                System.out.format(fmt, "ID", "Name", "Address", "Phone", "Balance");
                for (int i = 0; i < 90; i++) {
                        System.out.print("-");
                }
                System.out.println();
                while (iter.hasNext()) {
                        Client c = iter.next();
                        if(c.getBalance().toValue()>0) {
                        System.out.format(fmt, c.getId(), c.getName(),
                                          c.getAddress(), c.getPhone(), c.getBalance().toString());
                        }
                }
      }
      
      
      protected WarehouseState becomeClient() {
           String id = null;
                boolean is_valid_id = false;
                /* get a legitimate client ID */
                while (!is_valid_id) {
                        id = getToken("Client ID: ");
                        if (warehouse.getClient(id) == null) {
                                System.out.println("No client with ID '"
                                                   + id + ".'");
                        } else {
                                is_valid_id = true;
                        }
                }
                /* set the client ID in the context */
                context.setClientId(id);
                /* return the next state */
                return ClientMenuState.instance();
      }
      
      private void displayProductWaitlist() {
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
           Iterator<WaitlistEntry> iter = warehouse.getProductWaitlist(pid);
            while (iter.hasNext()) {
                WaitlistEntry entry = iter.next();
                while (iter.hasNext()) {
                        System.out.println(iter.next().toString());
                }
            }
      }
      
      private void receiveShipment() {
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
             int qty = 0;
                boolean is_qty_valid = false;
                while (!is_qty_valid) {
                        /* get qty */
                        qty = getNumber("Enter qty to add: ");
                        if (qty > 0)
                                is_qty_valid = true;
                        }
             Iterator<WaitlistEntry> iter = warehouse.getProductWaitlist(pid);
                while (iter.hasNext()) {
                    WaitlistEntry entry = iter.next();
                    System.out.println(iter.next().toString());
                    boolean is_valid_token = false;
                    while (!is_valid_token) {
                        String choice = getToken("Enter FILL to complete this order or SKIP to skip it:");
                        if(choice.compareTo("FILL") == 0 && entry.getQuantity() <= qty) {
                            qty = qty - entry.getQuantity();
                            warehouse.fillOrder(entry.getClientId(), pid, entry.getQuantity());
                            is_valid_token = true;
                        }
                        else if (choice.compareTo("FILL") == 0 && entry.getQuantity() < qty) {
                            System.out.println("Not enough quantity.");
                            is_valid_token = true;
                        }
                        else if (choice.compareTo("SKIP") != 0 && choice.compareTo("FILL") != 0) {
                            System.out.println("Not a valid entry. Please enter SKIP or FILL.");
                        }
                        else {
                            is_valid_token = true;
                        }
                       
                    }
                }
      }
}
      


/* Get pid, get quantity, check both
   Get prod waitlist for pid
   fill orders until qty is gone, delete each waitlist entry after filling*/
