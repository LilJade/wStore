/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import config.db_connection;
import entities.E_sale;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author diaz1
 */
public class D_sales {
    
    
    
    db_connection db = new db_connection();
    
    public boolean insertDirectSale() {
        Connection con = db.connectDB();
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            ps = con.prepareStatement("insert into sale(saleDate, totalNeto, idClient) values(?, ?, ?)");
            
            ps.setString(1, new java.sql.Date(System.currentTimeMillis()).toString());
            ps.setString(2, "0");
            ps.setInt(3, 1);
            
            ps.executeUpdate();
            
            System.out.println("Sale add...");            

        } catch (SQLException e) {
            System.out.println("Error al intentar registrar una nueva venta: " + e.getMessage());
        }
        
        return true;
    }
    
    public E_sale lastIdSale() {
        Connection con = db.connectDB();
        PreparedStatement ps;
        ResultSet rs;
        E_sale sale = new E_sale();
        
        try {
            ps = con.prepareStatement("select * from sale order by idSale desc limit 1");
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                sale.setIdSale(rs.getInt("idSale"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar ultima venta: " + e.getMessage());
        }
        
        return sale;
    }
    
    public boolean completeSale(E_sale sale) {
        Connection con = db.connectDB();
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            ps = con.prepareStatement("update sale set saleDate=?, totalNeto=?, idClient=? where idSale=?");
            
            ps.setString(1, new java.sql.Date(System.currentTimeMillis()).toString());
            ps.setDouble(2, sale.getTotalNeto());
            ps.setInt(3, sale.getIdClient().getIdClient());
            ps.setInt(4, sale.getIdSale());
            
            ps.executeUpdate();
            
            System.out.println("Sale updated...");   
            JOptionPane.showMessageDialog(null, "Venta realizada con exito weiii XD");
        } catch (SQLException e) {
            System.out.println("Error al intentar actualizar el registro del usuario: " + e.getMessage());
        }
        
        return true;
    }
    
}
