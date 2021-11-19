package entities;

/**
 *
 * @author diaz1
 */
public class E_product {
    
    
     /***** Atributos *****/
    private int idProduct;
    private String productName;
    private int quantityPerProduct;
    private double initialPrice;
    private double salePrice;
    private int stock;
    private E_category idCategory;

    
    /***** Metodos Setter and Getter *****/
    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantityPerProduct() {
        return quantityPerProduct;
    }

    public void setQuantityPerProduct(int quantityPerPackage) {
        this.quantityPerProduct = quantityPerPackage;
    }

    public double getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(double initialPrice) {
        this.initialPrice = initialPrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public E_category getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(E_category idCategory) {
        this.idCategory = idCategory;
    }

    
    /***** Metodo Constructor *****/
    public E_product() {
    }

    public E_product(String productName, int quantityPerProduct, double initialPrice, double salePrice, int stock, E_category idCategory) {
        this.productName = productName;
        this.quantityPerProduct = quantityPerProduct;
        this.initialPrice = initialPrice;
        this.salePrice = salePrice;
        this.stock = stock;
        this.idCategory = idCategory;
    }

    public E_product(int idProduct, String productName, int quantityPerProduct, double initialPrice, double salePrice, int stock, E_category idCategory) {
        this.idProduct = idProduct;
        this.productName = productName;
        this.quantityPerProduct = quantityPerProduct;
        this.initialPrice = initialPrice;
        this.salePrice = salePrice;
        this.stock = stock;
        this.idCategory = idCategory;
    }

    
    /***** Metodo toString *****/
    @Override
    public String toString() {
        return "D_product{" + "idProduct=" + idProduct + ", productName=" + 
                productName + ", quantityPerProduct=" + quantityPerProduct +
                ", initialPrice=" + initialPrice + ", salePrice=" + salePrice + 
                ", stock=" + stock + ", idCategory=" + idCategory + '}';
    }
}
