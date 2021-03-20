import java.io.*;
public class ClientIdServer implements Serializable {
  private static final long serialVersionUID = 1L;
  private int idCounter;
  private static ClientIdServer server;
  private ClientIdServer() {
    idCounter = 1;
  }
  public static ClientIdServer instance() {
    if (server == null) {
      return (server = new ClientIdServer());
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
