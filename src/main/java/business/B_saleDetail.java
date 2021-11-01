/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import data.D_saleDetail;
import entities.E_saleDetail;

/**
 *
 * @author diaz1
 */
public class B_saleDetail {
    
      D_saleDetail data = new D_saleDetail();
    
    public boolean B_insertSaleDetail(E_saleDetail saleDetail) {
        if (data.insertSaleDetail(saleDetail) == false) {
            return false;
        }
        
        return true;
    }
    
}
