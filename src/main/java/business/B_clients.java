package business;
import entities.E_clients;
import data.D_clients;
import java.util.ArrayList;

/**
 *
 * @author LilJade
 */
public class B_clients {
    D_clients data = new D_clients();
    
    public ArrayList<E_clients> B_listClients() {
        ArrayList<E_clients> list = data.listClients();
        
        if (list == null) {
            return null;
        }
        
        return list;
    }
    
    public boolean B_insertClient(E_clients client) {
        if (data.insertClient(client) == false) {
            return false;
        }
        
        return true;
    }
    
    public boolean B_updateClient(E_clients client) {
        if (data.updateClient(client) == false) {
            return false;
        }
        
        return true;
    }
    
    public boolean B_deleteClient(E_clients client) {
        if (data.deleteClient(client) == false) {
            return false;
        }
        
        return true;
    }
}
