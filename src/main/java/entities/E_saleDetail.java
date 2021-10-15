/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author diaz1
 */
public class E_saleDetail {
    
    /***** Atributos *****/
    private int idSaleDetail;
    private int soldUnits;
    private double subtotal;
    private E_sale idSale;
    private E_product idProduct;

    
    /***** Metodos Getter and Setter *****/
    public int getIdSaleDetail() {
        return idSaleDetail;
    }

    public void setIdSaleDetail(int idSaleDetail) {
        this.idSaleDetail = idSaleDetail;
    }

    public int getSoldUnits() {
        return soldUnits;
    }

    public void setSoldUnits(int soldUnits) {
        this.soldUnits = soldUnits;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public E_sale getIdSale() {
        return idSale;
    }

    public void setIdSale(E_sale idSale) {
        this.idSale = idSale;
    }

    public E_product getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(E_product idProduct) {
        this.idProduct = idProduct;
    }

    
    
    /***** Metodo Constructor *****/
    public E_saleDetail() {
    }

    public E_saleDetail(int soldUnits, double subtotal, E_sale idSale, E_product idProduct) {
        this.soldUnits = soldUnits;
        this.subtotal = subtotal;
        this.idSale = idSale;
        this.idProduct = idProduct;
    }

    public E_saleDetail(int idSaleDetail, int soldUnits, double subtotal, E_sale idSale, E_product idProduct) {
        this.idSaleDetail = idSaleDetail;
        this.soldUnits = soldUnits;
        this.subtotal = subtotal;
        this.idSale = idSale;
        this.idProduct = idProduct;
    }

    
    
    /*****  Metodo toString *****/
    @Override
    public String toString() {
        return "E_saleDetail{" + "idSaleDetail=" + idSaleDetail + ", soldUnits=" +
                soldUnits + ", subtotal=" + subtotal + ", idSale=" + idSale + 
                ", idProduct=" + idProduct + '}';
    }
}
