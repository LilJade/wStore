/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;
import entities.E_users;
import data.D_users;

/**
 *
 * @author LilJade
 */
public class B_users {
    
    D_users data = new D_users();
    
    public String B_loginUsers(E_users user) {
        user = data.LoginUsers(user);
        
        if (user == null) {
            return "Login Fail";
        }
        return "Login Success: User: " + user.getFirstName() + " " + user.getLastName() ;
    }
    
    public E_users B_loginUsers2(E_users user) {
        user = data.LoginUsers(user);
        
        if (user == null) {
            return null;
        }
        return user;
    }
    
}
