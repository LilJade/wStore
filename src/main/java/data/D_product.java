/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import config.db_connection;
import entities.E_category;
import entities.E_product;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Alexis
 */
public class D_product {
    
    private db_connection db =new db_connection();
    E_category Ec=new E_category();
    Connection con= db.connectDB();

    //metodo llenar combobox
    public ArrayList<E_category> llenarcbx(){
        ArrayList<E_category> listaC =new ArrayList();
        
        try {
            Statement st=con.createStatement();
            String sql = "select  * from category;";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                E_category c=new E_category();
                c.setIdCategory(rs.getInt("IdCategory"));
                c.setNameC(rs.getString("NameC"));
                
                listaC.add(c);
                
            }
        } catch (Exception e) {
             System.out.println(e);
        }
        
        return listaC;
    }
    
    public  ArrayList<E_product> showProducts(){
        
        ArrayList<E_product> product =new ArrayList<>();
        try {
            Statement st=con.createStatement();
            String sql="select * from product";
            ResultSet rs=st.executeQuery(sql);
            
            while(rs.next()){
            E_product p= new E_product();
            p.setIdProduct(rs.getInt("idProduct"));
            p.setProductName(rs.getString("productName"));
            p.setQuantityPerPackage(rs.getInt("quantityPerPackage"));
            p.setInitialPrice(rs.getDouble("initialPrice"));
            p.setSalePrice(rs.getDouble("salePrice"));
            p.setStock(rs.getInt("stock"));
            
             
           product.add(p);
           
              
        }
            
            
            
        } catch (Exception e) {
            System.out.println(""+e);
        }
        
         return product;
    }
    
    public  ArrayList<E_product> showProductsbycategory(String nameC){
         
        E_category Ecategory=new E_category();
        ArrayList<E_product> product =new ArrayList<>();
         E_product p= new E_product();
        try {
           
            String filto="" + nameC+ "_%";
            CallableStatement st=con.prepareCall("select idProduct, productName, quantityPerPackage, initialPrice,salePrice,stock FROM product where idCategory  in(select idCategory from Category where nameC like " +'"'+ filto +'"'+')'   ) ;
            ResultSet rs=st.executeQuery();
            while(rs.next()){
           p.setIdProduct(rs.getInt("idProduct"));
            p.setProductName(rs.getString("productName"));
            p.setQuantityPerPackage(rs.getInt("quantityPerPackage"));
            p.setInitialPrice(rs.getDouble("initialPrice"));
            p.setSalePrice(rs.getDouble("salePrice"));
            p.setStock(rs.getInt("stock"));
            product.add(p);
               System.out.println("todo"+p);
            
            }
        } catch (Exception e) {
            System.out.println(""+e);
        }
        
        
        
         return product;
         
        
    }
    
     public  ArrayList<E_product> sherchProduct(String nameC){ 
        ArrayList<E_product> product =new ArrayList<>();
         try {
              E_product p= new E_product();
                     String filto="" + nameC+ "_%";
            CallableStatement st=con.prepareCall("SELECT idProduct, productName, quantityPerPackage, initialPrice,salePrice,stock FROM product WHERE productName LIKE " + '"' + filto + '"');
            ResultSet rs=st.executeQuery();
            while(rs.next()){
            p.setIdProduct(rs.getInt("idProduct"));
            p.setProductName(rs.getString("productName"));
            p.setQuantityPerPackage(rs.getInt("quantityPerPackage"));
            p.setInitialPrice(rs.getDouble("initialPrice"));
            p.setSalePrice(rs.getDouble("salePrice"));
            p.setStock(rs.getInt("stock"));
            product.add(p);
                System.out.println(""+p);
            }
          
            
         } catch (Exception e) {
             System.out.println(""+e);
         }
         return product;
     }
    
        public void deleteproduct(E_product products){
         try {
             String sql="delete from product  where idProduct = ?";
            PreparedStatement ps=con.prepareStatement(sql);
             ps.setInt(1, products.getIdProduct());
            ps.execute();
         } catch (Exception e) {
             System.out.println("erro"+e);
         }
     }
        
        
        public void insertproduct(E_product products){
            try {
             String sql=("insert into product (productName,quantityPerPackage,initialPrice, salePrice,stock,idCategory) values(?,?,?,?,?,?)");
             PreparedStatement ps=con.prepareStatement(sql);
             ps.setString(1,products.getProductName());
             ps.setInt(2, products.getQuantityPerPackage());
             ps.setDouble(3, products.getInitialPrice());
             ps.setDouble(4, products.getSalePrice());
             ps.setInt(5, products.getStock());
             ps.setInt(6, products.getIdCategory().getIdCategory());
              ps.execute();
                System.out.println(""+ps);
         } catch (Exception e) {
             System.out.println("erro"+e);
         }
        }
        
        public void updateproduct(E_product products){
            try {
             String sql=("update product set productName =? , quantityPerPackage=?, initialPrice=?, salePrice=?, stock=?, idCategory=? WHERE idProduct=?");
             PreparedStatement ps=con.prepareStatement(sql);
             ps.setString(1,products.getProductName());
             ps.setInt(2, products.getQuantityPerPackage());
             ps.setDouble(3, products.getInitialPrice());
             ps.setDouble(4, products.getSalePrice());
             ps.setInt(5, products.getStock());
             ps.setInt(6, products.getIdCategory().getIdCategory());
             ps.setInt(7, products.getIdProduct());
              ps.execute();
                System.out.println(""+ps);
            } catch (Exception e) {
            }
        }
    
    
    
    
}
