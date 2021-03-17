import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;
public abstract class WarehouseState {
        protected static WarehouseContext context;
        private static BufferedReader reader
                = new BufferedReader(new InputStreamReader(System.in));

        protected WarehouseState() {
                context = WarehouseContext.instance();
        }

        //Function to get user's input.
        protected static String getToken(String prompt) {
                do {
                        try {
                                System.out.println(prompt);
                                String line = reader.readLine();
                                StringTokenizer tokenizer
                                        = new StringTokenizer(line,"\n\r\f");
                                if (tokenizer.hasMoreTokens()) {
                                        return tokenizer.nextToken();
                                }

                        } catch (IOException ioe) {
                                System.exit(0);
                        }
                } while (true);
        }

        //Function to get user's input for yes or no questions.
        protected static boolean yesOrNo(String prompt) {
                String more = getToken(prompt
                                       + " (Y|y)[es] or anything else for no");
                if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
                        return false;
                }
                return true;
        }

        //Function to get user's input when the input is a number.
        protected static int getNumber(String prompt) {
                do {
                        try {
                                String item = getToken(prompt);
                                Integer num = Integer.valueOf(item);
                                return num.intValue();
                        } catch (NumberFormatException nfe) {
                                System.out.println("Please input a number ");
                        }
                } while (true);
        }

        protected static
        <T extends Enum<T>>
        void printHelp(T[] enum_vals,
                  String[] cmd_strings) {
                for (int i = 0; i < cmd_strings.length; i++) {
                        System.out.println(enum_vals[i].ordinal()
                                           + cmd_strings[i]);
                }
        }

        protected static
        <T extends Enum<T>>
        T getCommand(T[] enum_vals) {
                int len = enum_vals.length;
                int cmd;
                do {
                        try {
                                cmd = Integer
                                      .parseInt(getToken("Enter command:"));
                                if ((cmd >= 0) && (cmd < len)) {
                                        return enum_vals[cmd];
                                }
                        } catch (NumberFormatException nfe) {
                                System.out.println("Enter a number");
                        }
                } while (true);
        }

        /** Run the state.
         * This method returns the state to change to, removing this function
         * from the function stack. If there instead was a method in the
         * context called "changeState(State)" which called the "run()" method
         * on the new state, the function stack would grow infinitely.
         *
         * A minimized example of the undesirable function stack growth:
         * State1 is running. State1 calls on the context
         * to change state to State2. State2 is now running. State2 calls on
         * the context to change state to State1. State1 is now running. The
         * function stack for this process is now "State1.run()
         * -> Context.changeState(State2) -> State2.run()
         * -> Context.changeState(State1) -> State1.run()".
         * @return      the state to be transitioned to, or null to proceed
         *              with context-dependent exit operations */
        public abstract WarehouseState run();
}
