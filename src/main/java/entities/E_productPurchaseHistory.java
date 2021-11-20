package entities;

import java.sql.Timestamp;

/**
 *
 * @author LilJade
 */
public class E_productPurchaseHistory {
    private int idProductPH;
    private int old_Stock;
    private int new_Stock;
    private double price;
    private Timestamp datePurchase;
    private E_product idProduct;

    public int getIdProductPH() {
        return idProductPH;
    }

    public void setIdProductPH(int idProductPH) {
        this.idProductPH = idProductPH;
    }

    public int getOld_Stock() {
        return old_Stock;
    }

    public void setOld_Stock(int old_Stock) {
        this.old_Stock = old_Stock;
    }

    public int getNew_Stock() {
        return new_Stock;
    }

    public void setNew_Stock(int new_Stock) {
        this.new_Stock = new_Stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Timestamp getDatePurchase() {
        return datePurchase;
    }

    public void setDatePurchase(Timestamp datePurchase) {
        this.datePurchase = datePurchase;
    }

    public E_product getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(E_product idProduct) {
        this.idProduct = idProduct;
    }

    public E_productPurchaseHistory() {
    }

    public E_productPurchaseHistory(int old_Stock, int new_Stock, double price, Timestamp datePurchase, E_product idProduct) {
        this.old_Stock = old_Stock;
        this.new_Stock = new_Stock;
        this.price = price;
        this.datePurchase = datePurchase;
        this.idProduct = idProduct;
    }

    public E_productPurchaseHistory(int idProductPH, int old_Stock, int new_Stock, double price, Timestamp datePurchase, E_product idProduct) {
        this.idProductPH = idProductPH;
        this.old_Stock = old_Stock;
        this.new_Stock = new_Stock;
        this.price = price;
        this.datePurchase = datePurchase;
        this.idProduct = idProduct;
    }

    @Override
    public String toString() {
        return "D_productPurchaseHistory{" + "idProductPH=" + idProductPH + 
                ", old_Stock=" + old_Stock + ", new_Stock=" + new_Stock + 
                ", price=" + price + ", datePurchase=" + datePurchase + 
                ", idProduct=" + idProduct + '}';
    }
}
