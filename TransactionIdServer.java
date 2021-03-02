import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
public class TransactionIdServer implements Serializable {
  private  int idCounter;
  private static TransactionIdServer server;
  private TransactionIdServer() {
    idCounter = 1;
  }
  public static TransactionIdServer instance() {
    if (server == null) {
      return (server = new TransactionIdServer());
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
  public static void retrieve(ObjectInputStream input) {
    try {
      server = (TransactionIdServer) input.readObject();
    } catch(IOException ioe) {
      ioe.printStackTrace();
    } catch(Exception cnfe) {
      cnfe.printStackTrace();
    }
  }
  private void writeObject(java.io.ObjectOutputStream output) throws IOException {
    try {
      output.defaultWriteObject();
      output.writeObject(server);
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
  }
  private void readObject(java.io.ObjectInputStream input) throws IOException, ClassNotFoundException {
    try {
      input.defaultReadObject();
      if (server == null) {
        server = (TransactionIdServer) input.readObject();
      } else {
        input.readObject();
      }
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
  }
}