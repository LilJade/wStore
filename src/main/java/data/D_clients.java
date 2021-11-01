package data;
import config.db_connection;
import entities.E_clients;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author LilJade
 */
public class D_clients {
    db_connection db = new db_connection();
    
    public ArrayList<E_clients> listClients() {
        Connection con = db.connectDB();
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<E_clients> list = new ArrayList();
        E_clients client;
        
        try {
            ps = con.prepareStatement("select * from clients");
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                client = new E_clients();
                client.setIdClient(Integer.parseInt(rs.getString("idClient")));
                client.setFirstName(rs.getString("firstName"));
                client.setLastName(rs.getString("lastName"));
                client.setNumberphone(rs.getString("numberphone"));
                
                list.add(client);
                System.out.println("Add data to list...");
            }
            System.out.println("Data added!..");
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al consultar la lista de clientes registrados: " + e.getMessage());
        }
        
        return list;
    }
    
    public boolean insertClient(E_clients client) {
        Connection con = db.connectDB();
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            ps = con.prepareStatement("insert into clients(firstName, lastName, numberphone) values (?, ?, ?)");
            
            ps.setString(1, client.getFirstName());
            ps.setString(2, client.getLastName());
            ps.setString(3, client.getNumberphone());
            
            ps.executeUpdate();
            
            System.out.println("Client add!...");
            
        } catch (SQLException e) {
            System.out.println("Error al intentar registrar un nuevo usuario: " + e.getMessage());
        }
        
        return true;
    }
    
    public boolean updateClient(E_clients client) {
        Connection con = db.connectDB();
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            ps = con.prepareStatement("update clients set firstName=?, lastName=?, numberphone=? where idClient=?");
            
            ps.setString(1, client.getFirstName());
            ps.setString(2, client.getLastName());
            ps.setString(3, client.getNumberphone());
            ps.setInt(4, client.getIdClient());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al intentar actualizar el registro: " + e.getMessage());
        }
        
        return true;
    }
    
    public boolean deleteClient(E_clients client) {
        Connection con = db.connectDB();
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            ps = con.prepareStatement("delete from clients where idClient=?");
            
            ps.setInt(1, client.getIdClient());
            
            int rowsDeleted = ps.executeUpdate();

            if (rowsDeleted > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al intentar eliminar el registro: " + e.getMessage());
        }
        return false;
    }
    
     public boolean insertGenericClient() {
        Connection con = db.connectDB();
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            ps = con.prepareStatement("insert into clients(firstName, lastName, numberphone) values (?, ?, ?)");
            
            ps.setString(1, "");
            ps.setString(2, "");
            ps.setString(3, "");
            
            ps.executeUpdate();
            
            System.out.println("Generic Client add!...");
            
        } catch (SQLException e) {
            System.out.println("Error al intentar registrar un cliente generico: " + e.getMessage());
        }
        
        return true;
    }
    
    public E_clients lastIdClient() {
        Connection con = db.connectDB();
        PreparedStatement ps;
        ResultSet rs;
        E_clients client = new E_clients();
        
        try {
            ps = con.prepareStatement("select * from clients order by idClient desc limit 1");
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                client.setIdClient(rs.getInt("idClient"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar ultimo cliente: " + e.getMessage());
        }
        
        return client;
    }
}
