import java.util.*;
import java.io.*;

public class SupplierList implements Serializable {
private static final long serialVersionUID = 1L;
private TreeMap<String, Supplier> map;
private static SupplierList instance = null;
private SupplierList(){
    map = new TreeMap <String, Supplier>();
}
public static SupplierList instance() {
        if (instance == null) {
                instance = new SupplierList();
        }
        return instance;
}
//find supplier by ID.

public Supplier find(String supp_id){
    return map.get(supp_id);
}
// add a Supplier to the list.
public boolean add(Supplier Supp) {
if (map.containsKey(Supp.getId())){
return false;
}
else {
    map.put(Supp.getId(), Supp);
    return true;
}
}

// obtain an iterator for all suppliers.
public Iterator<Supplier> iterator() {
return map.values().iterator();
}

// to create a print string of the Supplier List.
public String toString() {
        Iterator<Supplier> iter = iterator();
        String str = new String();
        while (iter.hasNext()) {
        str += iter.next();
        }
        return str;
}
}
