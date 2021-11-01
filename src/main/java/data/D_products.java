/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import config.db_connection;
import entities.E_category;
import entities.E_product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author diaz1
 */
public class D_products {
    
    
     db_connection db = new db_connection();
    
    public ArrayList<E_product> listProducts() {
        Connection con = db.connectDB();
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<E_product> list = new ArrayList();
        E_product product;
        
        try {
            ps = con.prepareStatement("select * from product");
            rs = ps.executeQuery();
            
            while (rs.next()) {
                product = new E_product();
                product.setIdProduct(Integer.parseInt(rs.getString("idProduct")));
                product.setProductName(rs.getString("productName"));
                product.setQuantityPerPackage(rs.getInt("quantityPerPackage"));
                product.setInitialPrice(rs.getDouble("initialPrice"));
                product.setSalePrice(rs.getDouble("salePrice"));
                product.setStock(rs.getInt("stock"));
                product.setImage(rs.getString("image"));
                E_category idCategory = new E_category();
                idCategory.setIdCategory(rs.getInt("idCategory"));
                product.setIdCategory(idCategory);
                
                list.add(product);
            }
            
            System.out.println("Sending data prods...");
            
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al consultar la lista de productos: " + e.getMessage());
        }
        
        return list;
    }
    
}
