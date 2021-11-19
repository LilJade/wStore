package data;

import config.db_connection;
import entities.E_sale;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author diaz1
 */
public class D_sales {

    db_connection db = new db_connection();

    public boolean insertDirectSale() {
        Connection con = db.connectDB();
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("insert into sale(totalNeto) values(?)");

            ps.setString(1, "0");

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

        try {
            ps = con.prepareStatement("update sale set totalNeto=? where idSale=?");

            ps.setDouble(1, sale.getTotalNeto());
            ps.setInt(2, sale.getIdSale());

            ps.executeUpdate();

            System.out.println("Sale updated...");
        } catch (SQLException e) {
            System.out.println("Error al intentar completar el registro de la venta: " + e.getMessage());
        }

        return true;
    }

    public E_sale confirmPreviousSale() {
        Connection con = db.connectDB();
        PreparedStatement ps;
        ResultSet rs;
        E_sale previousSale = lastIdSale();
        
        if (previousSale == null) {
            return null;
        }

        try {
            ps = con.prepareStatement("select * from sale where idSale=? and totalNeto=0");
            ps.setInt(1, previousSale.getIdSale());

            rs = ps.executeQuery();

            if (rs.next()) {
                previousSale.setIdSale(rs.getInt("idSale"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar ultima venta: " + e.getMessage());
        }

        return previousSale;
    }
}
