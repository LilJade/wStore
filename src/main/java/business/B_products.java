package business;

import data.D_products;
import entities.E_product;
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
    
    public E_product B_productSearchById(E_product product) {
        product = data.searchProductById(product);
        if (product != null) {
            return product;
        }

        return null;
    }
    
}
