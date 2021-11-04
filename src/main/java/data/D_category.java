/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import config.db_connection;
import entities.E_category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Alexis
 */
public class D_category {
    private db_connection db=new db_connection();
    
     Connection con =db.connectDB();
    
    public ArrayList<E_category> showCategory(){
         ArrayList<E_category> category=new ArrayList<>();
       
         try {
            Statement st=con.createStatement();
            String sql = "select * from category";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                E_category c=new E_category();
                c.setIdCategory(Integer.parseInt(rs.getString("idCategory")));
                c.setNameC(rs.getString("nameC"));
                
                c.setDescriptionC(rs.getString("descriptionC"));
                category.add(c); 
            }
            
        } catch (Exception e) {
             System.out.println(e);
        }
         
        return category;
    }
    
    public void insertarCategoria(E_category category){
        try {
            
            String sql="insert into category (nameC,descriptionC) values(?,?)";
            PreparedStatement ps =con.prepareStatement(sql);
            ps.setString(1, category.getNameC());
            ps.setString(2, category.getDescriptionC());
         
            ps.executeUpdate();
           
        } catch (SQLException e) {
            System.out.println("error"+e);
        }
        
        
      
        
    }public void updateCategoty(E_category category){
            try {
                String sql=" update category set nameC = ?, descriptionC = ? where idCategory = ?";
                 PreparedStatement ps =con.prepareStatement(sql);
                 ps.setString(1, category.getNameC());
                 ps.setString(2, category.getDescriptionC());
                 ps.setInt(3, category.getIdCategory());
                 ps.execute();
            } catch (SQLException e) {
                System.out.println("Error"+e);
            }
    }
    
    
    public void deletecategoria(E_category category){
        try {
            String sql="delete from category  where idCategory = ?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, category.getIdCategory());
            ps.execute();
        } catch (Exception e) {
            System.out.println("error macro"+e);
        }
    }
}
