package business;
import entities.E_users;
import data.D_users;
import java.util.ArrayList;

/**
 *
 * @author LilJade
 */
public class B_users {
    
    D_users data = new D_users();
    
    public E_users B_loginUsers(E_users user) {
        user = data.loginUsers(user);
        
        if (user == null) {
            return null;
        }
        return user;
    }
    
    public ArrayList B_listUsers() {
        ArrayList list = data.listUsers();
        
        if (list == null) {
            System.out.println("Error al cargar la lista de usuarios!");
            return null;
        }
        
        return list;
    }
    
    public boolean B_insertUser(E_users user) {
        if (data.insertUser(user) == false) {
            return false;
        }
        
        return true;
    }
    
    public boolean B_updateUser(E_users user) {
        if (data.updateUser(user) == false) {
            return false;
        }
        
        return true;
    }
    
    public boolean B_deleteUser(E_users user) {
        if (data.deleteUser(user) == false) {
            return false;
        }
        
        return true;
    }
}
