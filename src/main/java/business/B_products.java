/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import data.D_products;
import java.util.ArrayList;

/**
 *
 * @author diaz1
 */
public class B_products {
    
    
      D_products data = new D_products();
    
    public ArrayList B_listProducts() {
        ArrayList list = data.listProducts();
        
        System.out.println("Size list: " + list.size());
        
        if (list == null) {
            System.out.println("Error al cargar la lista de usuarios!");
            return null;
        }
        
        return list;
    }
    
}
