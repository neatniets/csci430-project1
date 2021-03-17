/* TODO: implement class */
public class ManagerMenuState extends WarehouseState {
        private static ManagerMenuState instance = null;
        private ManagerMenuState() {
                super();
        }
        public static ManagerMenuState instance() {
                if (instance == null) {
                        instance = new ManagerMenuState();
                }
                return instance;
        }
        public WarehouseState run() {
                System.out.println("ManagerMenuState");
                return null;
        }
}
