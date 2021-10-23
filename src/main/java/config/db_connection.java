/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author diaz1
 */
public class db_connection {
    
    private Connection cn;
    
    private String host = "localhost";
    private String user = "root";
    private String password = "liljade09";
    private String db = "w_Store";
    private String url = "jdbc:mysql://" + host + "/" + db;
    
    public Connection connectDB() {
        try {
            this.cn = DriverManager.getConnection(this.url, this.user, this.password);
        } catch (Exception e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
        return cn;
    }
}
