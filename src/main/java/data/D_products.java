package data;

import config.db_connection;
import entities.E_category;
import entities.E_product;
import java.sql.CallableStatement;
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
                product.setQuantityPerProduct(rs.getInt("quantityPerPackage"));
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

    public E_product searchProductById(E_product product) {
        Connection con = db.connectDB();
        try {
            CallableStatement st = con.prepareCall("SELECT * FROM product WHERE idProduct='" + product.getIdProduct() + "'");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                product.setIdProduct(rs.getInt("idProduct"));
                product.setProductName(rs.getString("productName"));
                product.setQuantityPerProduct(rs.getInt("quantityPerPackage"));
                product.setInitialPrice(rs.getDouble("initialPrice"));
                product.setSalePrice(rs.getDouble("salePrice"));
                product.setStock(rs.getInt("stock"));

                return product;
            }

        } catch (Exception e) {
            System.out.println("Error al buscar producto por ID" + e);
        }
        return null;
    }

    public boolean subtractStock(E_product product, int amount) {
        Connection con = db.connectDB();
        PreparedStatement ps;
        product = searchProductById(product);

        int newStock = product.getStock() - amount;
        System.out.println("Stock: " + product.getStock() + "\nVendido: " + amount + "\nNuevo Stock: " + newStock);
        
        try {
            ps = con.prepareStatement("update product set stock=? WHERE idProduct=?");
            ps.setInt(1, newStock);
            ps.setInt(2, product.getIdProduct());
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Error al restar la cantidad del stock del producto: " + e.getMessage());
        }
        return false;
    }
}
