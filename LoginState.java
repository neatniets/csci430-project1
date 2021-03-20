public class LoginState extends WarehouseState {
        private Warehouse warehouse = null;
        private static LoginState instance = null;
        private static enum Cmds {
                EXIT,
                CLIENT_LOGIN,
                CLERK_LOGIN,
                MANAGER_LOGIN,
                HELP
        };
        private static final String[] cmd_strings = {
                " to exit.",
                " to login as client.",
                " to login as clerk.",
                " to login as manager.",
                " to get this help text."
        };

        private LoginState() {
                super();
                warehouse = Warehouse.instance();
        }

        public static WarehouseState instance() {
                if (instance == null) {
                        instance = new LoginState();
                }
                return instance;
        }

        protected WarehouseState clientLogin() {
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

        public WarehouseState run() {
                System.out.println("How would you like to log in?");
                Cmds cmd = Cmds.HELP;
                do {
                        switch (cmd) {
                        case CLIENT_LOGIN:
                                context.setUserType(WarehouseContext
                                                    .UserType.CLIENT);
                                return clientLogin();
                        case CLERK_LOGIN:
                                context.setUserType(WarehouseContext
                                                    .UserType.CLERK);
                                return ClerkMenuState.instance();
                        case MANAGER_LOGIN:
                                context.setUserType(WarehouseContext
                                                    .UserType.MANAGER);
                                return ManagerMenuState.instance();
                        case HELP:
                                printHelp(Cmds.values(), cmd_strings);
                                break;
                        default:
                                System.err.println("Error in switch");
                                System.exit(1);
                        }
                } while ((cmd = getCommand(Cmds.values(), Cmds.HELP.ordinal()))
                         != Cmds.EXIT);
                return null;
        }
}
