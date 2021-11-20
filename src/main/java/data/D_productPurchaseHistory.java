package data;

import config.db_connection;
import entities.E_product;
import entities.E_productPurchaseHistory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author LilJade
 */
public class D_productPurchaseHistory {
    
    db_connection db = new db_connection();
    
    public ArrayList<E_productPurchaseHistory> productPurchasesMonth(int month, int year, E_product product) {
        Connection con = db.connectDB();
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<E_productPurchaseHistory> list = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM productpurchasehistory WHERE MONTH(datePurchase) = ? AND YEAR(datePurchase) = ? AND idProduct = ? ORDER BY datePurchase DESC");
            ps.setInt(1, month);
            ps.setInt(2, year);
            ps.setInt(3, product.getIdProduct());

            rs = ps.executeQuery();
            
            while (rs.next()) {
                E_productPurchaseHistory pph = new E_productPurchaseHistory();
                pph.setIdProductPH(rs.getInt("idProductPH"));
                pph.setOld_Stock(rs.getInt("old_Stock"));
                pph.setNew_Stock(rs.getInt("new_Stock"));
                pph.setPrice(rs.getDouble("price"));
                pph.setDatePurchase(rs.getTimestamp("datePurchase"));

                E_product p = new E_product();
                p.setIdProduct(rs.getInt("idProduct"));

                pph.setIdProduct(p);
                
                list.add(pph);
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar lista de compras de producto por mes: " + e.getMessage());
        }
        
        return list;
    }
    
    public ArrayList<E_productPurchaseHistory> productPurchasesYear(int year, E_product product) {
        Connection con = db.connectDB();
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<E_productPurchaseHistory> list = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM productpurchasehistory WHERE YEAR(datePurchase) = ? AND idProduct = ? ORDER BY datePurchase DESC");
            ps.setInt(1, year);
            ps.setInt(2, product.getIdProduct());

            rs = ps.executeQuery();
            
            while (rs.next()) {
                E_productPurchaseHistory pph = new E_productPurchaseHistory();
                pph.setIdProductPH(rs.getInt("idProductPH"));
                pph.setOld_Stock(rs.getInt("old_Stock"));
                pph.setNew_Stock(rs.getInt("new_Stock"));
                pph.setPrice(rs.getDouble("price"));
                pph.setDatePurchase(rs.getTimestamp("datePurchase"));

                E_product p = new E_product();
                p.setIdProduct(rs.getInt("idProduct"));

                pph.setIdProduct(p);
                
                list.add(pph);
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar lista de compras de producto por año: " + e.getMessage());
        }
        
        return list;
    }
    
}
