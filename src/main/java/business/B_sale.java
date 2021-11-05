package business;

import data.D_sales;
import entities.E_sale;

/**
 *
 * @author diaz1
 */
public class B_sale {
    
    
     D_sales data = new D_sales();
    
    public boolean B_insertDirectSale() {
        if (data.insertDirectSale() == false) {
            return false;
        }
        
        return true;
    }
    
    public E_sale B_lastIdSale() {
        return data.lastIdSale();
    }
    
    public boolean B_completeSale(E_sale sale) {
        if (data.completeSale(sale) == false) {
            return false;
        }
        
        return true;
    }
    
    public E_sale B_confirmPreviousSale() {
        E_sale sale = data.confirmPreviousSale();
        if(sale != null) {
            return sale;
        }
        
        return null;
    }
    
}
