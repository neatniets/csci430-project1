import java.io.*;
public class SupplierIdServer implements Serializable {
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

  //Function to retrieve the saved SupplierIdServer data after the user's latest interactions.
  public static void retrieve(ObjectInputStream input) {
    try {
      server = (SupplierIdServer) input.readObject();
    } catch(IOException ioe) {
      ioe.printStackTrace();
    } catch(Exception cnfe) {
      cnfe.printStackTrace();
    }

  }

  //Function to write an object to the MemberIdServer.
  private void writeObject(java.io.ObjectOutputStream output) throws IOException {
    try {
      output.defaultWriteObject();
      output.writeObject(server);
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }

  }

  //Function to read an object from the MemberIdServer.
  private void readObject(java.io.ObjectInputStream input) throws IOException, ClassNotFoundException {
    try {
      input.defaultReadObject();
      if (server == null) {
        server = (SupplierIdServer) input.readObject();
      } else {
        input.readObject();
      }

    } catch(IOException ioe) {
      ioe.printStackTrace();
    }

  }

}