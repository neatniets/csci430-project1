public class LoginState extends WarehouseState {
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
        }

        public static WarehouseState instance() {
                if (instance == null) {
                        instance = new LoginState();
                }
                return instance;
        }

        public WarehouseState run() {
                Cmds cmd = Cmds.HELP;
                do {
                        switch (cmd) {
                        case CLIENT_LOGIN:
                                return ClientMenuState.instance();
                        case CLERK_LOGIN:
                                return ClerkMenuState.instance();
                        case MANAGER_LOGIN:
                                return ManagerMenuState.instance();
                        case HELP:
                                printHelp(Cmds.values(), cmd_strings);
                                break;
                        default:
                                System.err.println("Error in switch");
                                System.exit(1);
                        }
                } while ((cmd = getCommand(Cmds.values())) != Cmds.EXIT);
                return null;
        }
}
