package entities;

import java.sql.Date;

/**
 *
 * @author diaz1
 */
public class E_sale {
  
    /***** Atributos *****/
    private int idSale;
    private Date saleDate;
    private double totalNeto;
    private E_clients idClient;

    
    
    /***** Metodos Getter and Setter *****/
    public int getIdSale() {
        return idSale;
    }

    public void setIdSale(int idSale) {
        this.idSale = idSale;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public double getTotalNeto() {
        return totalNeto;
    }

    public void setTotalNeto(double totalNeto) {
        this.totalNeto = totalNeto;
    }

    public E_clients getIdClient() {
        return idClient;
    }

    public void setIdClient(E_clients idClient) {
        this.idClient = idClient;
    }
    
    /***** Metodo Constructor *****/
    public E_sale() {
    }

    public E_sale(Date saleDate, double totalNeto, E_clients idClient, E_saleDetail idSaleDetail) {
        this.saleDate = saleDate;
        this.totalNeto = totalNeto;
        this.idClient = idClient;
    }

    public E_sale(int idSale, Date saleDate, double totalNeto, E_clients idClient, E_saleDetail idSaleDetail) {
        this.idSale = idSale;
        this.saleDate = saleDate;
        this.totalNeto = totalNeto;
        this.idClient = idClient;
    }

    
    /***** Metodo toString *****/
    @Override
    public String toString() {
        return "E_sale{" + "idSale=" + idSale + ", saleDate=" + saleDate + 
                ", totalNeto=" + totalNeto + ", idClient=" + idClient + '}';
    }
}
