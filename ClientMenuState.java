/* TODO: implement class */
public class ClientMenuState extends WarehouseState {
        private static ClientMenuState instance = null;
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
                System.out.println("ClientMenuState");
                return null;
        }
}
