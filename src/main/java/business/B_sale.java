/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    
}
