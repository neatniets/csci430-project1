public class WarehouseContext {
        private static WarehouseContext instance = null;
        private WarehouseState current_state;
        private static enum user_type {
                CLIENT,
                CLERK,
                MANAGER
        };
        private user_type type;
        private String client_id;

        private WarehouseContext() {
        }

        public static WarehouseContext instance() {
                if (instance == null) {
                        instance = new WarehouseContext();
                }
                return instance;
        }

        /** Process the current state & state transitions until exit condition
         * is met. */
        public void process() {
                /* initial state */
                current_state = LoginState.instance();
                boolean is_exiting = false; // flag to terminate program
                /* keep running states until exit is desirable */
                while (!is_exiting) {
                        /* run next state and grab the return for the next
                         * state */
                        WarehouseState next = current_state.run();
                        if (next == null) { // state is exiting
                                /* TODO: add state-specific exit stuff. e.g.
                                 * when a clerk exits after becoming a client,
                                 * they should return to clerk state but not
                                 * exit */
                                is_exiting = true;
                        } else { // state is transitioning
                                current_state = next;
                        }
                }
        }

        public static void main(String[] args) {
                WarehouseContext.instance().process();
        }
}
