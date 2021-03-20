import java.io.*;
public class SupplierIdServer implements Serializable {
  private static final long serialVersionUID = 1L;
  private  int idCounter;
  private static SupplierIdServer server;

  //Constructor.
  private SupplierIdServer() {
    idCounter = 1;
  }

  //UserInterface instance prepare the required instance for the SupplierIDServer to function.
  public static SupplierIdServer instance() {
    if (server == null) {
      return (server = new SupplierIdServer());
    } else {
      return server;
    }
  }

  //Function to get a new ID.
  public int getId() {
    return idCounter++;
  }

  //Function to return a string of the current idCounter.
  public String toString() {
    return ("IdServer" + idCounter);
  }

}
