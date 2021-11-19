package data;

import config.db_connection;
import entities.E_product;
import entities.E_saleDetail;
import entities.E_sale;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    
    public ArrayList<E_saleDetail> productSalesMonth(int month, int year, E_product product) {
        Connection con = db.connectDB();
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<E_saleDetail> list = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM saledetail WHERE MONTH(dateSaleDetail) = ? AND YEAR(dateSaleDetail) = ? AND idProduct = ? ORDER BY dateSaleDetail DESC");
            ps.setInt(1, month);
            ps.setInt(2, year);
            ps.setInt(3, product.getIdProduct());

            rs = ps.executeQuery();
            
            while (rs.next()) {
                E_saleDetail sd = new E_saleDetail();
                sd.setSoldUnits(rs.getInt("soldUnits"));
                sd.setSubtotal(rs.getDouble("subtotal"));
                sd.setDateSaleDetail(rs.getTimestamp("dateSaleDetail"));
                E_sale sale = new E_sale();
                sale.setIdSale(rs.getInt("idSale"));
                sd.setIdSale(sale);
                sd.setIdProduct(product);
                
                list.add(sd);
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar lista de ventas de producto: " + e.getMessage());
        }
        
        return list;
    }

}
