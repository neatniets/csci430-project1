import java.io.*;
public class ProductIdServer implements Serializable {
        private static final long serialVersionUID = 1L;
        private int idCounter;
        private static ProductIdServer server;
        private ProductIdServer() {
                idCounter = 1;
        }
        public static ProductIdServer instance() {
                if (server == null) {
                        return (server = new ProductIdServer());
                } else {
                        return server;
                }
        }
        public int getId() {
                return idCounter++;
        }
        public String toString() {
                return ("IdServer" + idCounter);
        }
}
