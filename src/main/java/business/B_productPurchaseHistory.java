package business;

import data.D_productPurchaseHistory;
import entities.E_product;
import entities.E_productPurchaseHistory;
import java.util.ArrayList;

/**
 *
 * @author LilJade
 */
public class B_productPurchaseHistory {
    
    D_productPurchaseHistory data = new D_productPurchaseHistory();
    
    public boolean B_insertPPH(E_productPurchaseHistory pph) {
        return data.insertPPH(pph);
    }
    
    public ArrayList<E_productPurchaseHistory> listProductPurchasesMonth(int month, int year, E_product product) {
        ArrayList<E_productPurchaseHistory> list = data.productPurchasesMonth(month, year, product);
        
        if (list == null) {
            System.out.println("Error al cargar la lista de ventas de productos mensual!");
            return null;
        }
        
        return list;
    }
    
    public ArrayList<E_productPurchaseHistory> listProductPurchasesYear(int year, E_product product) {
        ArrayList<E_productPurchaseHistory> list = data.productPurchasesYear(year, product);
        
        if (list == null) {
            System.out.println("Error al cargar la lista de ventas de productos anual!");
            return null;
        }
        
        return list;
    }
}
