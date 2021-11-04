package data;

import entities.E_users;
import java.sql.Connection;
import config.db_connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author LilJade
 */
public class D_users {
    db_connection db = new db_connection();
    
    public E_users loginUsers(E_users user) {
        
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
    
    public ArrayList<E_users> listUsers() {
        Connection con = db.connectDB();
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<E_users> list = new ArrayList();
        E_users user;
        
        try {
            ps = con.prepareStatement("select * from users");
            rs = ps.executeQuery();
            
            while (rs.next()) {
                user = new E_users();
                user.setIdUser(Integer.parseInt(rs.getString("idUser")));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setEmail(rs.getString("email"));
                user.setPass(rs.getString("pass"));
                
                list.add(user);
            }
            
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al consultar la lista de usuarios: " + e.getMessage());
        }
        
        return list;
    }
    
    public boolean insertUser(E_users user) {
        Connection con = db.connectDB();
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            ps = con.prepareStatement("insert into users(firstName, lastName, email, pass) values(?, ?, ?, ?)");
            
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPass());
            
            ps.executeUpdate();
            
            System.out.println("User add...");            
        } catch (SQLException e) {
            System.out.println("Error al intentar registrar un nuevo usuario: " + e.getMessage());
        }
        
        return true;
    }
    
    public boolean updateUser(E_users user) {
        Connection con = db.connectDB();
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            ps = con.prepareStatement("update users set firstName=?, lastName=?, email=?, pass=? where idUser=?");
            
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPass());
            ps.setInt(5, user.getIdUser());
            
            ps.executeUpdate();
            
            System.out.println("User update...");            
        } catch (SQLException e) {
            System.out.println("Error al intentar actualizar el registro del usuario: " + e.getMessage());
        }
        
        return true;
    }
    
    public boolean deleteUser(E_users user) {
        Connection con = db.connectDB();
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            ps = con.prepareStatement("delete from users where idUser=?");
                
            ps.setInt(1, user.getIdUser());
                
            int rowsDeleted = ps.executeUpdate();
                
            if (rowsDeleted > 0) {
                return true;
            }
                
            System.out.println("User delete...");            
        } catch (SQLException e) {
            System.out.println("Error al intentar actualizar el registro del usuario: " + e.getMessage());
        }
        
        return false;
    }
}


