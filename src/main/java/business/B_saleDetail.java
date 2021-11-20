package business;

import data.D_saleDetail;
import entities.E_product;
import entities.E_saleDetail;
import java.util.ArrayList;

/**
 *
 * @author diaz1
 */
public class B_saleDetail {

    D_saleDetail data = new D_saleDetail();

    public boolean B_insertSaleDetail(E_saleDetail saleDetail) {
        return data.insertSaleDetail(saleDetail);
    }

    public ArrayList<E_saleDetail> listProductSalesMonth(int month, int year, E_product product) {
        ArrayList<E_saleDetail> list = data.productSalesMonth(month, year, product);

        if (list == null) {
            System.out.println("Error al cargar la lista de ventas de productos mensual!");
            return null;
        }

        return list;
    }

    public ArrayList<E_saleDetail> listProductSalesYear(int year, E_product product) {
        ArrayList<E_saleDetail> list = data.productSalesYear(year, product);

        if (list == null) {
            System.out.println("Error al cargar la lista de ventas de productos anual!");
            return null;
        }

        return list;
    }

}
