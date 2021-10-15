/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import entities.E_users;
import java.sql.Connection;
import config.db_connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author LilJade
 */
public class D_users {
    db_connection db = new db_connection();
    
    public E_users LoginUsers(E_users user) {
        
        Connection con = db.connectDB();
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            ps = con.prepareStatement("select * from users where email = ? and pass = ?");
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPass());
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                user.setIdUser(rs.getInt(1));
                user.setFirstName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setPass(rs.getString(5));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error al loguear usuario: " + e.getMessage());
        }
        
        return user;
    }    
}
