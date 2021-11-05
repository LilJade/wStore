package data;

import config.db_connection;
import entities.E_product;
import entities.E_saleDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author diaz1
 */
public class D_saleDetail {

    D_products dataProducts = new D_products();
    E_product product = new E_product();
    db_connection db = new db_connection();

    public boolean insertSaleDetail(E_saleDetail saleDetail) {
        Connection con = db.connectDB();
        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = con.prepareStatement("insert into saleDetail(soldUnits, subTotal, idSale, idProduct) values(?, ?, ?, ?)");

            ps.setString(1, String.valueOf(saleDetail.getSoldUnits()));
            ps.setString(2, String.valueOf(saleDetail.getSubtotal()));

            ps.setString(3, String.valueOf(saleDetail.getIdSale().getIdSale()));
            ps.setString(4, String.valueOf(saleDetail.getIdProduct().getIdProduct()));
            System.out.println("" + saleDetail.getIdProduct());

            product = new E_product();
            product = saleDetail.getIdProduct();

            dataProducts.subtractStock(product, saleDetail.getSoldUnits());

            ps.executeUpdate();

            System.out.println("Detail Sale added!..");
        } catch (SQLException e) {
            System.out.println("Error al intentar registrar un nuevo Detalle venta..." + e.getMessage());
        }

        return true;
    }

}
