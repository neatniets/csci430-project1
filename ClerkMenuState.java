/* TODO: implement class */
public class ClerkMenuState extends WarehouseState {
        private static ClerkMenuState instance = null;
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
                System.out.println("ClerkMenuState");
                return null;
        }
}
